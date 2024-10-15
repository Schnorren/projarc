package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.projarc.sarc.domain.model.HorarioEnum;

import java.time.LocalDate;
import java.util.List;

import com.projarc.sarc.domain.model.DiaSemanaEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO {
    private Integer codigo; // Identificador da turma
    private String disciplinaCodigo; // Código da disciplina
    private Long professorId; // ID do professor
    private DiaSemanaEnum diaSemana; // Dia da semana da turma
    private HorarioEnum horario; // Horário da turma
    private LocalDate dataInicio; // Data de início das aulas
    private LocalDate dataFim; // Data de término das aulas
    private List<Integer> recursosIds; // IDs dos recursos associados
}
