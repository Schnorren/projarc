package com.projarc.sarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projarc.sarc.dto.AulaDTO;
import com.projarc.sarc.service.AulaService;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaService aulaService;

    @Autowired
    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getAulaById(@PathVariable Long id) {
        AulaDTO aulaDTO = aulaService.findById(id);
        return ResponseEntity.ok(aulaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AulaDTO>> getAllAulas() {
        List<AulaDTO> aulas = aulaService.findAll();
        return ResponseEntity.ok(aulas);
    }

    @PostMapping
    public ResponseEntity<AulaDTO> createAula(@RequestBody AulaDTO aulaDTO) {
        AulaDTO createdAula = aulaService.save(aulaDTO);
        return ResponseEntity.ok(createdAula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> updateAula(@PathVariable Long id, @RequestBody AulaDTO aulaDTO) {
        AulaDTO updatedAula = aulaService.update(id, aulaDTO);
        return ResponseEntity.ok(updatedAula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
        aulaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/turma/{turmaCodigo}")
    public ResponseEntity<List<AulaDTO>> getAulasByTurma(@PathVariable Integer turmaCodigo) {
        List<AulaDTO> aulas = aulaService.findByTurma(turmaCodigo);
        return ResponseEntity.ok(aulas);
    }
}
