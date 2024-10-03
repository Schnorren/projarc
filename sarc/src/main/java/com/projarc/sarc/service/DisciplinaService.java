package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.repository.DisciplinaRepository;
import com.projarc.sarc.dto.DisciplinaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.DisciplinaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository, DisciplinaMapper disciplinaMapper) {
        this.disciplinaRepository = disciplinaRepository;
        this.disciplinaMapper = disciplinaMapper;
    }

    public DisciplinaDTO findById(String codigo) {
        Disciplina disciplina = disciplinaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com código: " + codigo));
        return disciplinaMapper.toDTO(disciplina);
    }

    public Disciplina findByIdEntity(String codigo) {
        return disciplinaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com código: " + codigo));
    }

    public List<DisciplinaDTO> findAll() {
        return disciplinaRepository.findAll()
                .stream()
                .map(disciplinaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        if (disciplinaRepository.existsById(disciplinaDTO.getCodigo())) {
            throw new DataIntegrityException("Já existe uma disciplina com o código: " + disciplinaDTO.getCodigo());
        }
        Disciplina disciplina = disciplinaMapper.toEntity(disciplinaDTO);
        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);
        return disciplinaMapper.toDTO(savedDisciplina);
    }

    public DisciplinaDTO update(String codigo, DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = disciplinaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com código: " + codigo));

        // Atualiza os detalhes da disciplina
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setCargaHoraria(disciplinaDTO.getCargaHoraria());

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return disciplinaMapper.toDTO(updatedDisciplina);
    }

    public void delete(String codigo) {
        Disciplina disciplina = disciplinaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com código: " + codigo));

        // Verifica se existem turmas associadas antes de deletar
        if (disciplina.getTurmas() != null && !disciplina.getTurmas().isEmpty()) {
            throw new DataIntegrityException("Não é possível deletar a disciplina pois existem turmas associadas.");
        }

        disciplinaRepository.delete(disciplina);
    }
}
