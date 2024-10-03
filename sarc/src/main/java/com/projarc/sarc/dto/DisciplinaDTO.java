package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {
    private String codigo;           // Identificador da disciplina
    private String nome;             // Nome fantasia
    private Integer cargaHoraria;    // Total de encontros
}
