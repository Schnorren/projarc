package com.projarc.sarc.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.domain.model.Semestre;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.RecursoRepository;
import com.projarc.sarc.domain.repository.TurmaRepository;
import com.projarc.sarc.dto.TurmaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.TurmaMapper;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaService disciplinaService;
    private final ProfessorService professorService;
    private final TurmaMapper turmaMapper;
    private final SemestreService semestreService;
    private final RecursoRepository recursoRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, DisciplinaService disciplinaService,
            ProfessorService professorService, TurmaMapper turmaMapper, SemestreService semestreService,
            RecursoRepository recursoRepository) {
        this.turmaRepository = turmaRepository;
        this.disciplinaService = disciplinaService;
        this.professorService = professorService;
        this.turmaMapper = turmaMapper;
        this.semestreService = semestreService;
        this.recursoRepository = recursoRepository;
    }

    public TurmaDTO findById(Integer codigo) {
        Turma turma = turmaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com código: " + codigo));
        return turmaMapper.toDTO(turma);
    }

    public List<Turma> findAllEntities() {
        return turmaRepository.findAll();
    }

    public Turma findByIdEntity(Integer codigo) {
        return turmaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com código: " + codigo));
    }

    public List<TurmaDTO> findAll() {
        return turmaRepository.findAll()
                .stream()
                .map(turmaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TurmaDTO save(TurmaDTO turmaDTO) {
        // Verificações de integridade
        if (turmaDTO.getHorario() == null || !Arrays.asList(HorarioEnum.values()).contains(turmaDTO.getHorario())) {
            throw new DataIntegrityException("Horário inválido. Deve seguir o sistema de horários da universidade.");
        }

        Disciplina disciplina = disciplinaService.findByIdEntity(turmaDTO.getDisciplinaCodigo());
        Professor professor = professorService.findByIdEntity(turmaDTO.getProfessorId());

        // Verificar disponibilidade do professor
        List<Turma> turmas = turmaRepository.findByProfessorAndDiaSemanaAndHorario(
                professor, turmaDTO.getDiaSemana(), turmaDTO.getHorario());
        if (!turmas.isEmpty()) {
            throw new DataIntegrityException("O professor já está alocado a outra turma no mesmo dia e horário.");
        }

        // Verificação de código único
        if (turmaRepository.existsByCodigoAndDisciplina(turmaDTO.getCodigo(), disciplina)) {
            throw new DataIntegrityException("Já existe uma turma com o código: " + turmaDTO.getCodigo()
                    + " para a disciplina: " + disciplina.getNome());
        }

        // Verificação de datas do semestre
        Semestre semestreAtual = semestreService.getCurrentSemester();
        if (!aulasDentroDoSemestre(semestreAtual, turmaDTO)) {
            throw new DataIntegrityException("As aulas estão fora do período do semestre atual.");
        }

        // Busca de recursos
        List<Recurso> recursos = buscarRecursos(turmaDTO.getRecursosIds());

        // Criação e salvamento da turma
        Turma turma = turmaMapper.toEntity(turmaDTO);
        turma.setRecursos(recursos);
        Turma savedTurma = turmaRepository.save(turma);
        return turmaMapper.toDTO(savedTurma);
    }

    private List<Recurso> buscarRecursos(List<Integer> recursosIds) {
        return recursosIds.stream()
                .map(recursoId -> recursoRepository.findById(recursoId)
                        .orElseThrow(() -> new DataIntegrityException("Recurso não encontrado com id: " + recursoId)))
                .collect(Collectors.toList());
    }

    private boolean aulasDentroDoSemestre(Semestre semestre, TurmaDTO turmaDTO) {
        if (semestre == null) {
            throw new DataIntegrityException("Semestre não configurado.");
        }
        return (turmaDTO.getDataInicio().isEqual(semestre.getDataInicio())
                || turmaDTO.getDataInicio().isAfter(semestre.getDataInicio())) &&
                (turmaDTO.getDataFim().isEqual(semestre.getDataFim())
                        || turmaDTO.getDataFim().isBefore(semestre.getDataFim()));
    }

    public TurmaDTO update(Integer codigo, TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com código: " + codigo));

        // Verificar se os recursos estão sendo alterados
        if (!turma.getRecursos().stream()
                .map(Recurso::getCodigo) // Utilize o método getCodigo para comparar os IDs
                .collect(Collectors.toList())
                .equals(turmaDTO.getRecursosIds())) {
            throw new DataIntegrityException("Recursos não podem ser alterados.");
        }

        // Atualiza os detalhes da turma, exceto os recursos
        turma.setHorario(turmaDTO.getHorario());

        // Atualiza a disciplina
        if (turmaDTO.getDisciplinaCodigo() != null) {
            Disciplina disciplina = disciplinaService.findByIdEntity(turmaDTO.getDisciplinaCodigo());
            turma.setDisciplina(disciplina);
        }

        // Atualiza o professor
        if (turmaDTO.getProfessorId() != null) {
            Professor professor = professorService.findByIdEntity(turmaDTO.getProfessorId());
            turma.setProfessor(professor);
        }

        Turma updatedTurma = turmaRepository.save(turma);
        return turmaMapper.toDTO(updatedTurma);
    }

    public void delete(Integer codigo) {
        Turma turma = turmaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com código: " + codigo));

        // Verifica se existem aulas associadas antes de deletar
        if (turma.getAulas() != null && !turma.getAulas().isEmpty()) {
            throw new DataIntegrityException("Não é possível deletar a turma pois existem aulas para ela.");
        }

        turmaRepository.delete(turma);
    }

    public List<TurmaDTO> findByDisciplina(String disciplinaCodigo) {
        Disciplina disciplina = disciplinaService.findByIdEntity(disciplinaCodigo);
        return turmaRepository.findByDisciplina(disciplina)
                .stream()
                .map(turmaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
