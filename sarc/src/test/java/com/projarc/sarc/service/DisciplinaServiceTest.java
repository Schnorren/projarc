package com.projarc.sarc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.repository.DisciplinaRepository;
import com.projarc.sarc.dto.DisciplinaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.mapper.DisciplinaMapper;

@SpringBootTest
public class DisciplinaServiceTest {

  @Mock
  private DisciplinaRepository disciplinaRepository;

  @Mock
  private DisciplinaMapper disciplinaMapper;

  @InjectMocks
  private DisciplinaService disciplinaService;

  private DisciplinaDTO disciplinaDTO;

  @BeforeEach
  void setUp() {
    disciplinaDTO = new DisciplinaDTO();
    disciplinaDTO.setCodigo("CS101");
    disciplinaDTO.setNome("Introdução à Computação");
    disciplinaDTO.setCargaHoraria(60);
  }

  @Test
  void shouldThrowExceptionWhenCodigoIsNotUnique() {
    // Simulando que o código da disciplina já existe no banco de dados
    when(disciplinaRepository.existsByCodigo("CS101")).thenReturn(true);

    assertThrows(DataIntegrityException.class, () -> {
      disciplinaService.save(disciplinaDTO);
    });

    System.out.println("Exceção lançada corretamente para código de disciplina duplicado.");
  }

  @Test
  void shouldSaveDisciplinaWhenCodigoIsUnique() {
    // Simulando que o código da disciplina não existe
    when(disciplinaRepository.existsByCodigo("CS101")).thenReturn(false);

    Disciplina disciplina = new Disciplina();
    disciplina.setCodigo("CS101");
    disciplina.setNome("Introdução à Computação");
    disciplina.setCargaHoraria(60);

    when(disciplinaMapper.toEntity(disciplinaDTO)).thenReturn(disciplina);

    when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplina);

    DisciplinaDTO savedDisciplinaDTO = new DisciplinaDTO();
    savedDisciplinaDTO.setCodigo("CS101");
    savedDisciplinaDTO.setNome("Introdução à Computação");
    savedDisciplinaDTO.setCargaHoraria(60);

    when(disciplinaMapper.toDTO(disciplina)).thenReturn(savedDisciplinaDTO);

    // Salvar a disciplina
    DisciplinaDTO savedDisciplina = disciplinaService.save(disciplinaDTO);

    assertNotNull(savedDisciplina);
    assertEquals("CS101", savedDisciplina.getCodigo());

    System.out.println("Disciplina salva com sucesso.");
  }
}
