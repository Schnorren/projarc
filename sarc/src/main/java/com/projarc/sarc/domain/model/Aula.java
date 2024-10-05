package com.projarc.sarc.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único da aula

    @Column(nullable = false)
    private LocalDate data; // Data da aula

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HorarioEnum horario; // Horário da aula

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaSemanaEnum diaSemana; // Dia da semana da aula

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_codigo", nullable = false)
    private Turma turma; // Turma associada

    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Alocacao> alocacoes;
}
