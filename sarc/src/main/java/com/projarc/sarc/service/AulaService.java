package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.AulaRepository;
import com.projarc.sarc.dto.AulaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.AulaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TurmaService turmaService;
    private final AulaMapper aulaMapper;

    @Autowired
    public AulaService(AulaRepository aulaRepository, TurmaService turmaService, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.turmaService = turmaService;
        this.aulaMapper = aulaMapper;
    }

    public AulaDTO findById(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada com ID: " + id));
        return aulaMapper.toDTO(aula);
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
        // Verifica se a turma existe
        Turma turma = turmaService.findByIdEntity(aulaDTO.getTurmaCodigo());



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
