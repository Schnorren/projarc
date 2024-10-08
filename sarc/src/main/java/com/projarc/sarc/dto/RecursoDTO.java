package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecursoDTO {
    private Integer codigo;          // Identificador do recurso
    private String descricao;        // Descrição do recurso
}
