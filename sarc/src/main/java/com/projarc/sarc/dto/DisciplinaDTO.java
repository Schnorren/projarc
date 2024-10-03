package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {
    private String codigo; // Identificador da disciplina
    private String nome; // Nome fantasia
    private Integer cargaHoraria;
    private List<TurmaDTO> turmas; // Campo de turmas adicionado
}
