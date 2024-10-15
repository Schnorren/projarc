package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Semestre;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.AulaRepository;
import com.projarc.sarc.dto.AulaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.AulaMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TurmaService turmaService;
    private final AulaMapper aulaMapper;
    private final SemestreService semestreService;

    @Autowired
    public AulaService(AulaRepository aulaRepository, TurmaService turmaService, AulaMapper aulaMapper,
            SemestreService semestreService) {
        this.aulaRepository = aulaRepository;
        this.turmaService = turmaService;
        this.aulaMapper = aulaMapper;
        this.semestreService = semestreService;
    }

    public AulaDTO findById(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada com ID: " + id));
        return aulaMapper.toDTO(aula);
    }

    public void generateWeeklyClassesForAllTurmas() {
        System.out.println("Iniciando a geração de aulas para todas as turmas...");

        // Obtém o semestre atual
        Semestre semestreAtual = semestreService.getCurrentSemester();
        System.out.println("Semestre atual: " + semestreAtual.getAtivo());

        // Busca todas as turmas existentes
        List<Turma> turmas = turmaService.findAllEntities();

        // Cria aulas para cada turma ao longo do semestre
        for (Turma turma : turmas) {
            LocalDate currentDate = semestreAtual.getDataInicio();

            // Itera a partir da data de início do semestre até o final
            while (!currentDate.isAfter(semestreAtual.getDataFim())) {
                // Verifica se o dia da semana corresponde ao dia configurado para a turma
                if (currentDate.getDayOfWeek().equals(turma.getDiaSemana().getDayOfWeek())) {
                    // Cria a aula para a data atual
                    Aula aula = new Aula();
                    aula.setTurma(turma);
                    aula.setData(currentDate);
                    aula.setHorario(turma.getHorario());
                    aula.setDiaSemana(turma.getDiaSemana());

                    // Salva a aula no repositório
                    aulaRepository.save(aula);
                }

                // Incrementa a data em um dia
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    public Aula findByIdEntity(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada com ID: " + id));
    }

    public List<AulaDTO> findAll() {
        return aulaRepository.findAll()
                .stream()
                .map(aulaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AulaDTO save(AulaDTO aulaDTO) {
        Semestre semestreAtual = semestreService.getCurrentSemester();

        Turma turma = turmaService.findByIdEntity(aulaDTO.getTurmaCodigo());

        if (turma == null) {
            throw new DataIntegrityException("Turma não encontrada.");
        }

        if (turma.getDiaSemana() != aulaDTO.getDiaSemana()) {
            throw new DataIntegrityException("O dia da semana da aula deve corresponder ao da turma.");
        }

        // REGRA 3 - Cada horário de turma utiliza o sistema de horários da
        // universidade.
        if (aulaDTO.getHorario() == null || !Arrays.asList(HorarioEnum.values()).contains(aulaDTO.getHorario())) {
            throw new DataIntegrityException("Horário inválido. Deve seguir o sistema de horários da universidade.");
        }

        // REGRA 7 - As aulas ocorrem entre as datas de início e término de cada
        // semestre.
        if (aulaDTO.getData().isBefore(semestreAtual.getDataInicio())
                || aulaDTO.getData().isAfter(semestreAtual.getDataFim())) {
            throw new DataIntegrityException("A data da aula está fora do intervalo permitido para o semestre.");
        }

        Aula aula = aulaMapper.toEntity(aulaDTO);
        Aula savedAula = aulaRepository.save(aula);
        return aulaMapper.toDTO(savedAula);
    }

    public AulaDTO update(Long id, AulaDTO aulaDTO) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada com ID: " + id));

        // Atualiza os detalhes da aula
        aula.setData(aulaDTO.getData());
        aula.setHorario(aulaDTO.getHorario());

        // Atualiza a turma
        if (aulaDTO.getTurmaCodigo() != null) {
            Turma turma = turmaService.findByIdEntity(aulaDTO.getTurmaCodigo());
            aula.setTurma(turma);

            throw new UnsupportedOperationException("Aulas não podem ser modificadas após serem cadastradas.");
        }

        Aula updatedAula = aulaRepository.save(aula);
        return aulaMapper.toDTO(updatedAula);
    }

    public void delete(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada com ID: " + id));

        // Verifica se a aula está associada a alguma alocação
        if (aula.getAlocacoes() != null && !aula.getAlocacoes().isEmpty()) {
            throw new DataIntegrityException("Não é possível deletar a aula pois ela está associada a alocações.");
        }

        aulaRepository.delete(aula);
    }

    public List<AulaDTO> findByTurma(Integer turmaCodigo) {
        Turma turma = turmaService.findByIdEntity(turmaCodigo);
        return aulaRepository.findByTurma(turma)
                .stream()
                .map(aulaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
