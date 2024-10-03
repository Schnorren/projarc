package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarc.sarc.domain.model.Alocacao;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {
    List<Alocacao> findByData(LocalDate data);

    // Encontrar alocações por turma
    List<Alocacao> findByAula_Turma_Codigo(Integer turmaCodigo);

    // Verificar disponibilidade do recurso em uma data e horário
    boolean existsByRecurso_CodigoAndDataAndHorario(Integer recursoCodigo, LocalDate data, String horario);
}
