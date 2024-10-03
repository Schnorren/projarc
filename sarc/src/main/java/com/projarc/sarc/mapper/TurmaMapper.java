package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.dto.TurmaDTO;

@Mapper(componentModel = "spring")
public interface TurmaMapper {
    TurmaMapper INSTANCE = Mappers.getMapper(TurmaMapper.class);
    
    @Mapping(source = "disciplina.codigo", target = "disciplinaCodigo")
    @Mapping(source = "professor.id", target = "professorId")
    TurmaDTO toDTO(Turma turma);
    
    @Mapping(source = "disciplinaCodigo", target = "disciplina.codigo")
    @Mapping(source = "professorId", target = "professor.id")
    Turma toEntity(TurmaDTO turmaDTO);
}
