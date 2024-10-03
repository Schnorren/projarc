package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.dto.ProfessorDTO;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);
    
    ProfessorDTO toDTO(Professor professor);
    
    Professor toEntity(ProfessorDTO professorDTO);
}
