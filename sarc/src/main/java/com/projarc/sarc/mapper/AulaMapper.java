package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.dto.AulaDTO;

@Mapper(componentModel = "spring")
public interface AulaMapper {

    @Mapping(source = "turma.codigo", target = "turmaCodigo")
    AulaDTO toDTO(Aula aula);

    @Mapping(source = "turmaCodigo", target = "turma.codigo")
    Aula toEntity(AulaDTO aulaDTO);
}
