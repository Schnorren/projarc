package com.projarc.sarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projarc.sarc.dto.TurmaDTO;
import com.projarc.sarc.service.TurmaService;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TurmaDTO> getTurmaByCodigo(@PathVariable Integer codigo) {
        TurmaDTO turmaDTO = turmaService.findById(codigo);
        return ResponseEntity.ok(turmaDTO);
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> getAllTurmas() {
        List<TurmaDTO> turmas = turmaService.findAll();
        return ResponseEntity.ok(turmas);
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> createTurma(@RequestBody TurmaDTO turmaDTO) {
        TurmaDTO createdTurma = turmaService.save(turmaDTO);
        return ResponseEntity.ok(createdTurma);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<TurmaDTO> updateTurma(@PathVariable Integer codigo, @RequestBody TurmaDTO turmaDTO) {
        TurmaDTO updatedTurma = turmaService.update(codigo, turmaDTO);
        return ResponseEntity.ok(updatedTurma);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Integer codigo) {
        turmaService.delete(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disciplina/{disciplinaCodigo}")
    public ResponseEntity<List<TurmaDTO>> getTurmasByDisciplina(@PathVariable String disciplinaCodigo) {
        List<TurmaDTO> turmas = turmaService.findByDisciplina(disciplinaCodigo);
        return ResponseEntity.ok(turmas);
    }
}
