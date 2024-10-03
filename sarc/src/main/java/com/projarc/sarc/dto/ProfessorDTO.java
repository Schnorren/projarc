package com.projarc.sarc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long id;                 // Identificador único do professor
    private String nome;             // Nome do professor
    private String email;            // Email institucional
}
