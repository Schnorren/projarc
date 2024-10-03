package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Alocacao;
import com.projarc.sarc.dto.AlocacaoDTO;

@Mapper(componentModel = "spring")
public interface AlocacaoMapper {
    AlocacaoMapper INSTANCE = Mappers.getMapper(AlocacaoMapper.class);
    
    @Mapping(source = "aula.id", target = "aulaId")
    @Mapping(source = "recurso.codigo", target = "recursoCodigo")
    AlocacaoDTO toDTO(Alocacao alocacao);
    
    @Mapping(source = "aulaId", target = "aula.id")
    @Mapping(source = "recursoCodigo", target = "recurso.codigo")
    Alocacao toEntity(AlocacaoDTO alocacaoDTO);
}
