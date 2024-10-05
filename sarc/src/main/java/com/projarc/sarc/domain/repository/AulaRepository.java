package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Turma;

import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    List<Aula> findByTurma(Turma turma);

    List<Aula> findByHorario(HorarioEnum horario);
}
