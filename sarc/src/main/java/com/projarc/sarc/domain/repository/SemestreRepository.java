package com.projarc.sarc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projarc.sarc.domain.model.Semestre;
import java.util.Optional;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
    Optional<Semestre> findByAtivoTrue();
}
