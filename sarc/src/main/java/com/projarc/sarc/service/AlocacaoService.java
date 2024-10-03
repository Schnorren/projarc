package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Alocacao;
import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.domain.repository.AlocacaoRepository;
import com.projarc.sarc.dto.AlocacaoDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.AlocacaoMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlocacaoService {

    private final AlocacaoRepository alocacaoRepository;
    private final AulaService aulaService;
    private final RecursoService recursoService;
    private final ProfessorService professorService;
    private final AlocacaoMapper alocacaoMapper;

    @Autowired
    public AlocacaoService(AlocacaoRepository alocacaoRepository, AulaService aulaService,
            RecursoService recursoService, ProfessorService professorService, AlocacaoMapper alocacaoMapper) {
        this.alocacaoRepository = alocacaoRepository;
        this.aulaService = aulaService;
        this.recursoService = recursoService;
        this.professorService = professorService;
        this.alocacaoMapper = alocacaoMapper;
    }

    public AlocacaoDTO findById(Long id) {
        Alocacao alocacao = alocacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alocação não encontrada com ID: " + id));
        return alocacaoMapper.toDTO(alocacao);
    }

    public List<AlocacaoDTO> findAll() {
        return alocacaoRepository.findAll()
                .stream()
                .map(alocacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlocacaoDTO save(AlocacaoDTO alocacaoDTO) {
        // Verifica se a aula existe
        Aula aula = aulaService.findByIdEntity(alocacaoDTO.getAulaId());

        // Verifica se o recurso existe
        Recurso recurso = recursoService.findByIdEntity(alocacaoDTO.getRecursoCodigo());

        // Verifica se o recurso está disponível na data e horário desejados
        if (!recursoService.isRecursoAvailable(recurso.getCodigo(), alocacaoDTO.getHorario(),
                alocacaoDTO.getData().toString())) {
            throw new DataIntegrityException("Recurso não disponível na data e horário selecionados.");
        }

        // Verifica se o professor está disponível na data e horário
        Long professorId = aula.getTurma().getProfessor().getId();
        if (!professorService.isProfessorAvailable(professorId, alocacaoDTO.getHorario())) {
            throw new DataIntegrityException("Professor não está disponível no horário selecionado.");
        }

        boolean recursoJaAlocado = alocacaoRepository.existsByRecurso_CodigoAndDataAndHorario(
                alocacaoDTO.getRecursoCodigo(), alocacaoDTO.getData(), alocacaoDTO.getHorario());

        if (recursoJaAlocado) {
            throw new DataIntegrityException("O recurso já está alocado para esse horário e data.");
        }

        if (aula.getTurma().getCodigo().longValue() != alocacaoDTO.getAulaId()) {
            throw new DataIntegrityException("O recurso só pode ser alocado em aulas da mesma turma.");
        }

        Alocacao alocacao = alocacaoMapper.toEntity(alocacaoDTO);
        Alocacao savedAlocacao = alocacaoRepository.save(alocacao);
        return alocacaoMapper.toDTO(savedAlocacao);
    }

    public AlocacaoDTO update(Long id, AlocacaoDTO alocacaoDTO) {
        Alocacao alocacao = alocacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alocação não encontrada com ID: " + id));

        // Atualiza os detalhes da alocação
        alocacao.setData(alocacaoDTO.getData());
        alocacao.setHorario(alocacaoDTO.getHorario());

        // Atualiza a aula
        if (alocacaoDTO.getAulaId() != null) {
            Aula aula = aulaService.findByIdEntity(alocacaoDTO.getAulaId());
            alocacao.setAula(aula);
        }

        // Atualiza o recurso
        if (alocacaoDTO.getRecursoCodigo() != null) {
            Recurso recurso = recursoService.findByIdEntity(alocacaoDTO.getRecursoCodigo());
            alocacao.setRecurso(recurso);
        }

        // Revalida as regras de reserva
        if (!recursoService.isRecursoAvailable(alocacao.getRecurso().getCodigo(), alocacao.getHorario(),
                alocacao.getData().toString())) {
            throw new DataIntegrityException("Recurso não disponível na data e horário selecionados.");
        }

        Long professorId = alocacao.getAula().getTurma().getProfessor().getId();
        if (!professorService.isProfessorAvailable(professorId, alocacao.getHorario())) {
            throw new DataIntegrityException("Professor não está disponível no horário selecionado.");
        }

        Alocacao updatedAlocacao = alocacaoRepository.save(alocacao);
        return alocacaoMapper.toDTO(updatedAlocacao);
    }

    public void delete(Long id) {
        Alocacao alocacao = alocacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alocação não encontrada com ID: " + id));
        alocacaoRepository.delete(alocacao);
    }

    public List<AlocacaoDTO> findByData(String data) {
        // Converter a string data para LocalDate
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(data);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido. Use YYYY-MM-DD.");
        }

        return alocacaoRepository.findByData(localDate)
                .stream()
                .map(alocacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AlocacaoDTO> findByTurma(Integer turmaCodigo) {
        return alocacaoRepository.findByAula_Turma_Codigo(turmaCodigo)
                .stream()
                .map(alocacaoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
