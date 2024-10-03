package com.projarc.sarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projarc.sarc.dto.AlocacaoDTO;
import com.projarc.sarc.service.AlocacaoService;

import java.util.List;

@RestController
@RequestMapping("/api/alocacoes")
public class AlocacaoController {

    private final AlocacaoService alocacaoService;

    @Autowired
    public AlocacaoController(AlocacaoService alocacaoService) {
        this.alocacaoService = alocacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlocacaoDTO> getAlocacaoById(@PathVariable Long id) {
        AlocacaoDTO alocacaoDTO = alocacaoService.findById(id);
        return ResponseEntity.ok(alocacaoDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlocacaoDTO>> getAllAlocacoes() {
        List<AlocacaoDTO> alocacoes = alocacaoService.findAll();
        return ResponseEntity.ok(alocacoes);
    }

    @PostMapping
    public ResponseEntity<AlocacaoDTO> createAlocacao(@RequestBody AlocacaoDTO alocacaoDTO) {
        AlocacaoDTO createdAlocacao = alocacaoService.save(alocacaoDTO);
        return ResponseEntity.ok(createdAlocacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlocacaoDTO> updateAlocacao(@PathVariable Long id, @RequestBody AlocacaoDTO alocacaoDTO) {
        AlocacaoDTO updatedAlocacao = alocacaoService.update(id, alocacaoDTO);
        return ResponseEntity.ok(updatedAlocacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlocacao(@PathVariable Long id) {
        alocacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<AlocacaoDTO>> getAlocacoesByData(@PathVariable String data) {
        List<AlocacaoDTO> alocacoes = alocacaoService.findByData(data);
        return ResponseEntity.ok(alocacoes);
    }

    @GetMapping("/turma/{turmaCodigo}")
    public ResponseEntity<List<AlocacaoDTO>> getAlocacoesByTurma(@PathVariable Integer turmaCodigo) {
        List<AlocacaoDTO> alocacoes = alocacaoService.findByTurma(turmaCodigo);
        return ResponseEntity.ok(alocacoes);
    }
}
