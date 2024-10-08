package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;

import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.dto.RecursoDTO;

@Mapper(componentModel = "spring", uses = { AlocacaoMapper.class })
public interface RecursoMapper {

    RecursoDTO toDTO(Recurso recurso);

    Recurso toEntity(RecursoDTO recursoDTO);
}
