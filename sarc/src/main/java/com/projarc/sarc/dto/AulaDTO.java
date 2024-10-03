package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AulaDTO {
    private Long id;                 // Identificador único da aula
    private LocalDate data;          // Data da aula
    private String horario;          // Horário (letras do horário)
    private Integer turmaCodigo;     // Código da turma associada
}
