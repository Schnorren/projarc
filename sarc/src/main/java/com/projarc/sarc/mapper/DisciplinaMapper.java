package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.dto.DisciplinaDTO;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {
    DisciplinaMapper INSTANCE = Mappers.getMapper(DisciplinaMapper.class);

    DisciplinaDTO toDTO(Disciplina disciplina);

    Disciplina toEntity(DisciplinaDTO disciplinaDTO);
}
