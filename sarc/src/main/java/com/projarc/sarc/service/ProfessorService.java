package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.domain.repository.ProfessorRepository;
import com.projarc.sarc.dto.ProfessorDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.ProfessorMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTO findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));
        return professorMapper.toDTO(professor);
    }

    public Professor findByIdEntity(Long id) { 
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));
    }

    public List<ProfessorDTO> findAll() {
        return professorRepository.findAll()
                .stream()
                .map(professorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        // Verifica se o email já está em uso
        if (professorRepository.existsByEmail(professorDTO.getEmail())) {
            throw new DataIntegrityException("Já existe um professor com o email: " + professorDTO.getEmail());
        }
        Professor professor = professorMapper.toEntity(professorDTO);
        Professor savedProfessor = professorRepository.save(professor);
        return professorMapper.toDTO(savedProfessor);
    }

    public ProfessorDTO update(Long id, ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));

        // Atualiza os detalhes do professor
        professor.setNome(professorDTO.getNome());

        // Verifica se o email está sendo atualizado e se já está em uso
        if (!professor.getEmail().equals(professorDTO.getEmail())) {
            if (professorRepository.existsByEmail(professorDTO.getEmail())) {
                throw new DataIntegrityException("Já existe um professor com o email: " + professorDTO.getEmail());
            }
            professor.setEmail(professorDTO.getEmail());
        }

        Professor updatedProfessor = professorRepository.save(professor);
        return professorMapper.toDTO(updatedProfessor);
    }

    public void delete(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));

        // Verifica se o professor está associado a alguma turma
        if (professor.getTurmas() != null && !professor.getTurmas().isEmpty()) {
            throw new DataIntegrityException("Não é possível deletar o professor pois ele está associado a turmas.");
        }

        professorRepository.delete(professor);
    }

    // Método adicional para verificar disponibilidade do professor
    public boolean isProfessorAvailable(Long professorId, String horario) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + professorId));
        // Implementar lógica para verificar conflitos de horário
        // Por exemplo, verificar se o professor já tem uma turma no mesmo horário
        return professor.getTurmas().stream()
                .noneMatch(turma -> turma.getHorario().equalsIgnoreCase(horario));
    }
}
