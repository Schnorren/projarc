package com.projarc.sarc.domain.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do professor

    @Column(nullable = false)
    private String nome; // Nome do professor

    @Column(nullable = false, unique = true)
    private String email; // Email institucional

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Turma> turmas;
}