package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.dto.ProfessorDTO;

@Mapper(componentModel = "spring", uses = { TurmaMapper.class })
public interface ProfessorMapper {

    @Mapping(target = "turmas", source = "turmas") // Mapeia a propriedade 'turmas'
    ProfessorDTO toDTO(Professor professor);

    @Mapping(target = "turmas", ignore = true) // Ignora a propriedade 'turmas' ao converter de DTO para entidade
    Professor toEntity(ProfessorDTO professorDTO);
}
