package com.projarc.sarc.domain.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo; // Identificador da turma

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_codigo", nullable = false)
    private Disciplina disciplina; // Disciplina associada

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor; // Professor responsável

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HorarioEnum horario; // Horário da turma

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaSemanaEnum diaSemana; // Dia da semana da turma

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Aula> aulas;
}
