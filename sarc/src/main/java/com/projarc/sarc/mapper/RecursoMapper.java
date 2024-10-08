package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.dto.RecursoDTO;

@Mapper(componentModel = "spring", uses = { AlocacaoMapper.class })
public interface RecursoMapper {

    @Mapping(target = "alocacoes", source = "alocacoes")
    RecursoDTO toDTO(Recurso recurso);

    @Mapping(target = "alocacoes", ignore = true) // Ignora a propriedade 'alocacoes' ao converter de DTO para entidade
    Recurso toEntity(RecursoDTO recursoDTO);
}
