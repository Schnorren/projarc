package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.dto.RecursoDTO;

@Mapper(componentModel = "spring")
public interface RecursoMapper {
    RecursoMapper INSTANCE = Mappers.getMapper(RecursoMapper.class);
    
    RecursoDTO toDTO(Recurso recurso);
    
    Recurso toEntity(RecursoDTO recursoDTO);
}
