package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projarc.sarc.domain.model.Alocacao;
import com.projarc.sarc.dto.AlocacaoDTO;

@Mapper(componentModel = "spring")
public interface AlocacaoMapper {

    @Mapping(source = "aula.id", target = "aulaId")
    @Mapping(source = "recurso.codigo", target = "recursoCodigo")
    @Mapping(source = "horario", target = "horario")
    AlocacaoDTO toDTO(Alocacao alocacao);

    @Mapping(source = "aulaId", target = "aula.id")
    @Mapping(source = "recursoCodigo", target = "recurso.codigo")
    @Mapping(source = "horario", target = "horario")
    Alocacao toEntity(AlocacaoDTO alocacaoDTO);
}
