package com.projarc.sarc.domain.model;


import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    private String codigo; // Identificador da disciplina

    @Column(nullable = false)
    private String nome; // Nome fantasia

    @Column(nullable = false)
    private Integer cargaHoraria; // Total de encontros

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Turma> turmas;
}