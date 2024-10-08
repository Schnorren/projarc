package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.dto.AulaDTO;

@Mapper(componentModel = "spring", uses = { AlocacaoMapper.class })
public interface AulaMapper {

    @Mapping(source = "turma.codigo", target = "turmaCodigo")
    @Mapping(source = "horario", target = "horario")
    @Mapping(source = "diaSemana", target = "diaSemana")
    AulaDTO toDTO(Aula aula);

    @Mapping(source = "turmaCodigo", target = "turma.codigo")
    @Mapping(source = "horario", target = "horario")
    @Mapping(source = "diaSemana", target = "diaSemana")
    @Mapping(target = "alocacoes", ignore = true) // Ignora a lista de alocações ao converter de DTO para entidade
    Aula toEntity(AulaDTO aulaDTO);
}
