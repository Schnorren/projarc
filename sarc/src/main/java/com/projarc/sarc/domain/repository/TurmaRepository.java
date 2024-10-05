package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.model.DiaSemanaEnum;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    boolean existsByCodigoAndDisciplina(Integer codigo, Disciplina disciplina);

    List<Turma> findByDisciplina(Disciplina disciplina);

    // Atualizado para incluir diaSemana e usar DiaSemanaEnum
    List<Turma> findByProfessorAndDiaSemanaAndHorario(Professor professor, DiaSemanaEnum diaSemana, HorarioEnum horario);

    // Atualizado para usar HorarioEnum em vez de String
    List<Turma> findByProfessorAndHorario(Professor professor, HorarioEnum horario);

    List<Turma> findByHorario(HorarioEnum horario);
}
