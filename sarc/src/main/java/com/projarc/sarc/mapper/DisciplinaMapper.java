package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.dto.DisciplinaDTO;

@Mapper(componentModel = "spring", uses = { TurmaMapper.class })
public interface DisciplinaMapper {

    DisciplinaMapper INSTANCE = Mappers.getMapper(DisciplinaMapper.class);

    @Mapping(source = "turmas", target = "turmas") // Mapeando a lista de turmas
    DisciplinaDTO toDTO(Disciplina disciplina);

    @Mapping(source = "turmas", target = "turmas") // Mapeando a lista de turmas
    Disciplina toEntity(DisciplinaDTO disciplinaDTO);
}
