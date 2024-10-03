package com.projarc.sarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projarc.sarc.dto.RecursoDTO;
import com.projarc.sarc.service.RecursoService;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    private final RecursoService recursoService;

    @Autowired
    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<RecursoDTO> getRecursoByCodigo(@PathVariable Integer codigo) {
        RecursoDTO recursoDTO = recursoService.findById(codigo);
        return ResponseEntity.ok(recursoDTO);
    }

    @GetMapping
    public ResponseEntity<List<RecursoDTO>> getAllRecursos() {
        List<RecursoDTO> recursos = recursoService.findAll();
        return ResponseEntity.ok(recursos);
    }

    @PostMapping
    public ResponseEntity<RecursoDTO> createRecurso(@RequestBody RecursoDTO recursoDTO) {
        RecursoDTO createdRecurso = recursoService.save(recursoDTO);
        return ResponseEntity.ok(createdRecurso);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<RecursoDTO> updateRecurso(@PathVariable Integer codigo, @RequestBody RecursoDTO recursoDTO) {
        RecursoDTO updatedRecurso = recursoService.update(codigo, recursoDTO);
        return ResponseEntity.ok(updatedRecurso);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable Integer codigo) {
        recursoService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
