package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.domain.repository.RecursoRepository;
import com.projarc.sarc.dto.RecursoDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.exception.ResourceNotFoundException;
import com.projarc.sarc.mapper.RecursoMapper;

import java.time.LocalDate; 
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    @Autowired
    public RecursoService(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    public RecursoDTO findById(Integer codigo) {
        Recurso recurso = recursoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com código: " + codigo));
        return recursoMapper.toDTO(recurso);
    }

    public Recurso findByIdEntity(Integer codigo) { 
        return recursoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com código: " + codigo));
    }

    public List<RecursoDTO> findAll() {
        return recursoRepository.findAll()
                .stream()
                .map(recursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RecursoDTO save(RecursoDTO recursoDTO) {
        // Verifica se a descrição já está em uso
        if (recursoRepository.existsByDescricao(recursoDTO.getDescricao())) {
            throw new DataIntegrityException("Já existe um recurso com a descrição: " + recursoDTO.getDescricao());
        }
        Recurso recurso = recursoMapper.toEntity(recursoDTO);
        Recurso savedRecurso = recursoRepository.save(recurso);
        return recursoMapper.toDTO(savedRecurso);
    }

    public RecursoDTO update(Integer codigo, RecursoDTO recursoDTO) {
        Recurso recurso = recursoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com código: " + codigo));

        // Atualiza a descrição do recurso
        recurso.setDescricao(recursoDTO.getDescricao());

        Recurso updatedRecurso = recursoRepository.save(recurso);
        return recursoMapper.toDTO(updatedRecurso);
    }

    public void delete(Integer codigo) {
        Recurso recurso = recursoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado com código: " + codigo));

        // Verifica se o recurso está associado a alguma alocação
        if (recurso.getAlocacoes() != null && !recurso.getAlocacoes().isEmpty()) {
            throw new DataIntegrityException("Não é possível deletar o recurso pois ele está associado a alocações.");
        }

        recursoRepository.delete(recurso);
    }

    // Método para verificar a disponibilidade do recurso em uma data e horário
    // específicos
    public boolean isRecursoAvailable(Integer recursoCodigo, String horario, String data) {
        Recurso recurso = recursoRepository.findById(recursoCodigo)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Recurso não encontrado com código: " + recursoCodigo));

        // Converter a string data para LocalDate
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(data);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido. Use YYYY-MM-DD.");
        }

        return recurso.getAlocacoes().stream()
                .noneMatch(alocacao -> alocacao.getHorario().equalsIgnoreCase(horario)
                        && alocacao.getData().equals(localDate));
    }
}
