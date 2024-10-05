    package com.projarc.sarc.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import com.projarc.sarc.dto.ProfessorDTO;
    import com.projarc.sarc.service.ProfessorService;

    import java.util.List;

    @RestController
    @RequestMapping("/api/professores")
    public class ProfessorController {

        private final ProfessorService professorService;

        @Autowired
        public ProfessorController(ProfessorService professorService) {
            this.professorService = professorService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long id) {
            ProfessorDTO professorDTO = professorService.findById(id);
            return ResponseEntity.ok(professorDTO);
        }

        @GetMapping
        public ResponseEntity<List<ProfessorDTO>> getAllProfessors() {
            List<ProfessorDTO> professores = professorService.findAll();
            return ResponseEntity.ok(professores);
        }

        @PostMapping
        public ResponseEntity<ProfessorDTO> createProfessor(@RequestBody ProfessorDTO professorDTO) {
            ProfessorDTO createdProfessor = professorService.save(professorDTO);
            return ResponseEntity.ok(createdProfessor);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
            ProfessorDTO updatedProfessor = professorService.update(id, professorDTO);
            return ResponseEntity.ok(updatedProfessor);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
            professorService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }
