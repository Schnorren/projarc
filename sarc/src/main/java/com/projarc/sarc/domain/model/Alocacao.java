package com.projarc.sarc.domain.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único da alocação

    @Column(nullable = false)
    private LocalDate data; // Data da reserva

    @Column(nullable = false)
    private String horario; // Horário (letras do horário)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula; // Aula associada

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recurso_codigo", nullable = false)
    private Recurso recurso; // Recurso reservado
}