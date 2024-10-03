package com.projarc.sarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projarc.sarc.dto.DisciplinaDTO;
import com.projarc.sarc.service.DisciplinaService;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DisciplinaDTO> getDisciplinaByCodigo(@PathVariable String codigo) {
        DisciplinaDTO disciplinaDTO = disciplinaService.findById(codigo);
        return ResponseEntity.ok(disciplinaDTO);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> getAllDisciplinas() {
        List<DisciplinaDTO> disciplinas = disciplinaService.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> createDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO createdDisciplina = disciplinaService.save(disciplinaDTO);
        return ResponseEntity.ok(createdDisciplina);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<DisciplinaDTO> updateDisciplina(@PathVariable String codigo, @RequestBody DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO updatedDisciplina = disciplinaService.update(codigo, disciplinaDTO);
        return ResponseEntity.ok(updatedDisciplina);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable String codigo) {
        disciplinaService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
