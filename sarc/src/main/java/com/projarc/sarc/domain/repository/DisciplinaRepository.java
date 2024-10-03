package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarc.sarc.domain.model.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {
    boolean existsByCodigo(String codigo);
}
