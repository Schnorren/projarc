package com.projarc.sarc.domain.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo; // Identificador do recurso

    @Column(nullable = false)
    private String descricao; // Descrição do recurso

    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Alocacao> alocacoes;
}