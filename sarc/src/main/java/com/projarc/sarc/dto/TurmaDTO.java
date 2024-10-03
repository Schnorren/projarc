package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO {
    private Integer codigo;          // Identificador da turma
    private String disciplinaCodigo; // Código da disciplina
    private Long professorId;        // ID do professor
    private String horario;          // Dias da semana e horários
}
