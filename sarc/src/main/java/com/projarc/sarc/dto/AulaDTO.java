package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.DiaSemanaEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AulaDTO {
    private Long id;                     // Identificador único da aula

    @jakarta.validation.constraints.NotNull(message = "A data da aula não pode ser nula.")
    private LocalDate data;              // Data da aula

    @jakarta.validation.constraints.NotNull(message = "O dia da semana da aula não pode ser nulo.")
    private DiaSemanaEnum diaSemana;     // Dia da semana da aula

    @jakarta.validation.constraints.NotNull(message = "O horário da aula não pode ser nulo.")
    private HorarioEnum horario;         // Horário da aula

    @jakarta.validation.constraints.NotNull(message = "O código da turma associada não pode ser nulo.")
    private Integer turmaCodigo;         // Código da turma associada

    private List<AlocacaoDTO> alocacoes; // Lista de alocações associadas
}
