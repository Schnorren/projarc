package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlocacaoDTO {
    private Long id;                 // Identificador único da alocação
    private LocalDate data;          // Data da reserva
    private String horario;          // Horário (letras do horário)
    private Long aulaId;             // ID da aula associada
    private Integer recursoCodigo;   // Código do recurso reservado
}
