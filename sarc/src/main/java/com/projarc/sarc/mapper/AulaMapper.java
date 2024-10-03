package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.dto.AulaDTO;

@Mapper(componentModel = "spring")
public interface AulaMapper {
    AulaMapper INSTANCE = Mappers.getMapper(AulaMapper.class);
    
    @Mapping(source = "turma.codigo", target = "turmaCodigo")
    AulaDTO toDTO(Aula aula);
    
    @Mapping(source = "turmaCodigo", target = "turma.codigo")
    Aula toEntity(AulaDTO aulaDTO);
}
