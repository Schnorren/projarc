package com.projarc.sarc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.dto.TurmaDTO;

@Mapper(componentModel = "spring", uses = { AulaMapper.class })
public interface TurmaMapper {

    @Mapping(source = "disciplina.codigo", target = "disciplinaCodigo")
    @Mapping(source = "professor.id", target = "professorId")
    @Mapping(source = "diaSemana", target = "diaSemana")
    @Mapping(source = "horario", target = "horario")
    TurmaDTO toDTO(Turma turma);

    @Mapping(source = "disciplinaCodigo", target = "disciplina.codigo")
    @Mapping(source = "professorId", target = "professor.id")
    @Mapping(source = "diaSemana", target = "diaSemana")
    @Mapping(source = "horario", target = "horario")
    Turma toEntity(TurmaDTO turmaDTO);
}
