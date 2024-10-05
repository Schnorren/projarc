package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.DiaSemanaEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlocacaoDTO {
    private Long id;                 // Identificador único da alocação
    private LocalDate data;          // Data da reserva
    private HorarioEnum horario;    // Horário (enum)
    private DiaSemanaEnum diaSemana; // Dia da semana da reserva
    private Long aulaId;             // ID da aula associada
    private Integer recursoCodigo;   // Código do recurso reservado
}
