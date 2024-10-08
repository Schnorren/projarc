package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;

import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.dto.ProfessorDTO;

@Mapper(componentModel = "spring", uses = { TurmaMapper.class })
public interface ProfessorMapper {

    ProfessorDTO toDTO(Professor professor);

    Professor toEntity(ProfessorDTO professorDTO);
}
