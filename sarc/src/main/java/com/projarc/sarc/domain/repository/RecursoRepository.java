package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarc.sarc.domain.model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    boolean existsByDescricao(String descricao);
}
