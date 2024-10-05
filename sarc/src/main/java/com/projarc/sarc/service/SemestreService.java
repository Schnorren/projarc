package com.projarc.sarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projarc.sarc.domain.model.Semestre;
import com.projarc.sarc.domain.repository.SemestreRepository;
import com.projarc.sarc.exception.DataIntegrityException;

@Service
public class SemestreService {

    private final SemestreRepository semestreRepository;

    @Autowired
    public SemestreService(SemestreRepository semestreRepository) {
        this.semestreRepository = semestreRepository;
    }

    public Semestre getCurrentSemester() {
        return semestreRepository.findByAtivoTrue()
            .orElseThrow(() -> new DataIntegrityException("Semestre atual não configurado."));
    }
}
