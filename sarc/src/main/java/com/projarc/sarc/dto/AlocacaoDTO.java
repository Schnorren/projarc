package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import com.projarc.sarc.domain.model.HorarioEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlocacaoDTO {
    private Long id;                 // Identificador único da alocação
    private LocalDate data;          // Data da reserva
    private HorarioEnum horario;    // Horário (enum)
    private Long aulaId;             // ID da aula associada
    private Integer recursoCodigo;   // Código do recurso reservado
}
