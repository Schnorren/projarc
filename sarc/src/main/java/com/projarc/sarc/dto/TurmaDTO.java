package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.DiaSemanaEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO {
    private Integer codigo;          // Identificador da turma
    private String disciplinaCodigo; // Código da disciplina
    private Long professorId;        // ID do professor
    private DiaSemanaEnum diaSemana; // Dia da semana da turma
    private HorarioEnum horario;    // Horário da turma
}
