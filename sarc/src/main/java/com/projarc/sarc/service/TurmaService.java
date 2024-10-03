package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.TurmaRepository;
import com.projarc.sarc.dto.TurmaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.TurmaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaService disciplinaService;
    private final ProfessorService professorService;
    private final TurmaMapper turmaMapper;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, DisciplinaService disciplinaService,
            ProfessorService professorService, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.disciplinaService = disciplinaService;
        this.professorService = professorService;
        this.turmaMapper = turmaMapper;
    }

    public TurmaDTO findById(Integer codigo) {
        Turma turma = turmaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com código: " + codigo));
        return turmaMapper.toDTO(turma);
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
        // Verifica se a disciplina existe
        Disciplina disciplina = disciplinaService.findByIdEntity(turmaDTO.getDisciplinaCodigo());

        // Verifica se o professor existe
        Professor professor = professorService.findByIdEntity(turmaDTO.getProfessorId());

        // Verifica a unicidade do código da turma dentro da disciplina
        if (turmaRepository.existsByCodigoAndDisciplina(turmaDTO.getCodigo(), disciplina)) {
            throw new DataIntegrityException("Já existe uma turma com o código: " + turmaDTO.getCodigo()
                    + " para a disciplina: " + disciplina.getNome());
        }

        Turma turma = turmaMapper.toEntity(turmaDTO);
        Turma savedTurma = turmaRepository.save(turma);
        return turmaMapper.toDTO(savedTurma);
    }

    public TurmaDTO update(Integer codigo, TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com código: " + codigo));

        // Atualiza os detalhes da turma
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
