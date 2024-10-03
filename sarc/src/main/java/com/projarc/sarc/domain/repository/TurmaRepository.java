package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.model.Turma;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    boolean existsByCodigoAndDisciplina(Integer codigo, Disciplina disciplina);

    List<Turma> findByDisciplina(Disciplina disciplina);
}
