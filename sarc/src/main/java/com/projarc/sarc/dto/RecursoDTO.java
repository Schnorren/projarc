package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecursoDTO {
    private Integer codigo;          // Identificador do recurso
    private String descricao;        // Descrição do recurso
    private List<AlocacaoDTO> alocacoes; // Lista de alocações associadas
}
