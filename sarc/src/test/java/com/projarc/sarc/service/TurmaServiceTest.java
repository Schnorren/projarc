package com.projarc.sarc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.projarc.sarc.domain.model.DiaSemanaEnum;
import com.projarc.sarc.domain.model.Disciplina;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Professor;
import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.domain.model.Semestre;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.RecursoRepository;
import com.projarc.sarc.domain.repository.TurmaRepository;
import com.projarc.sarc.dto.TurmaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.mapper.TurmaMapper;

@SpringBootTest
public class TurmaServiceTest {

  @Mock
  private TurmaRepository turmaRepository;

  @Mock
  private DisciplinaService disciplinaService;

  @Mock
  private ProfessorService professorService;

  @Mock
  private RecursoRepository recursoRepository;

  @Mock
  private SemestreService semestreService;

  @Mock
  private TurmaMapper turmaMapper;

  @InjectMocks
  private TurmaService turmaService;

  private TurmaDTO turmaDTO;
  private Disciplina disciplina;
  private Professor professor;
  private Turma turma;
  private Semestre semestreAtual;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Configuração do TurmaDTO
    turmaDTO = new TurmaDTO();
    turmaDTO.setCodigo(101);
    turmaDTO.setDisciplinaCodigo("DISC101");
    turmaDTO.setProfessorId(1L);
    turmaDTO.setDiaSemana(DiaSemanaEnum.SEGUNDA);
    turmaDTO.setHorario(HorarioEnum.A);
    turmaDTO.setDataInicio(LocalDate.of(2024, 3, 1));
    turmaDTO.setDataFim(LocalDate.of(2024, 6, 30));
    turmaDTO.setRecursosIds(Arrays.asList(1, 2));

    // Simulação de uma disciplina
    disciplina = new Disciplina();
    disciplina.setCodigo("DISC101");
    disciplina.setNome("Matemática");

    // Simulação de um professor
    professor = new Professor();
    professor.setId(1L);
    professor.setNome("João Silva");

    // Simulação de uma turma
    turma = new Turma();
    turma.setCodigo(101);
    turma.setDisciplina(disciplina);
    turma.setProfessor(professor);
    turma.setDiaSemana(DiaSemanaEnum.SEGUNDA);
    turma.setHorario(HorarioEnum.A);

    // Simulação de um semestre
    semestreAtual = new Semestre();
    semestreAtual.setDataInicio(LocalDate.of(2024, 3, 1));
    semestreAtual.setDataFim(LocalDate.of(2024, 6, 30));

    // Mock do SemestreService para retornar o semestre atual
    when(semestreService.getCurrentSemester()).thenReturn(semestreAtual);

    // Mock dos recursos
    when(recursoRepository.findById(1)).thenReturn(Optional.of(new Recurso(1, "Projetor")));
    when(recursoRepository.findById(2)).thenReturn(Optional.of(new Recurso(2, "Laboratório")));
  }

  @Test
  void shouldThrowExceptionWhenProfessorIsAlreadyAllocatedAtSameTimeAndDay() {
    when(disciplinaService.findByIdEntity("DISC101")).thenReturn(disciplina);
    when(professorService.findByIdEntity(1L)).thenReturn(professor);

    // Simulando que o professor já tem uma turma no mesmo horário
    when(turmaRepository.findByProfessorAndDiaSemanaAndHorario(professor, DiaSemanaEnum.SEGUNDA, HorarioEnum.A))
        .thenReturn(List.of(turma));

    // Verifica se a exceção DataIntegrityException é lançada
    DataIntegrityException exception = assertThrows(DataIntegrityException.class, () -> {
      turmaService.save(turmaDTO);
    });

    assertEquals("O professor já está alocado a outra turma no mesmo dia e horário.", exception.getMessage());
    System.out.println("Exceção lançada corretamente para professor já alocado no mesmo horário e dia.");
  }

  @Test
  void shouldSaveTurmaWhenProfessorIsNotAllocatedAtSameTimeAndDay() {
    when(disciplinaService.findByIdEntity("DISC101")).thenReturn(disciplina);
    when(professorService.findByIdEntity(1L)).thenReturn(professor);
    when(turmaRepository.findByProfessorAndDiaSemanaAndHorario(professor, DiaSemanaEnum.SEGUNDA, HorarioEnum.A))
        .thenReturn(List.of()); // Professor está disponível

    Semestre semestreValido = new Semestre(1L, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 30), true);
    when(semestreService.getCurrentSemester()).thenReturn(semestreValido);

    when(turmaMapper.toEntity(turmaDTO)).thenReturn(turma);
    when(turmaRepository.save(any(Turma.class))).thenReturn(turma);
    when(turmaMapper.toDTO(turma)).thenReturn(turmaDTO);

    // Salvar a turma
    TurmaDTO savedTurma = turmaService.save(turmaDTO);

    assertNotNull(savedTurma);
    assertEquals(101, savedTurma.getCodigo());
    assertEquals("DISC101", savedTurma.getDisciplinaCodigo());

    System.out.println("Turma salva com sucesso.");
  }

  @Test
  void shouldThrowExceptionWhenDateIsOutsideSemester() {
    when(disciplinaService.findByIdEntity("DISC101")).thenReturn(disciplina);
    when(professorService.findByIdEntity(1L)).thenReturn(professor);

    // Define uma data fora do semestre
    turmaDTO.setDataInicio(LocalDate.of(2025, 1, 1));
    turmaDTO.setDataFim(LocalDate.of(2025, 2, 1));

    when(semestreService.getCurrentSemester())
        .thenReturn(new Semestre(1L, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 6, 30), true));

    DataIntegrityException exception = assertThrows(DataIntegrityException.class, () -> {
      turmaService.save(turmaDTO);
    });

    assertEquals("As aulas estão fora do período do semestre atual.", exception.getMessage());
    System.out.println("Exceção lançada corretamente para data fora do semestre.");
  }

  @Test
  void shouldSaveTurmaWithMultipleResources() {
    when(disciplinaService.findByIdEntity("DISC101")).thenReturn(disciplina);
    when(professorService.findByIdEntity(1L)).thenReturn(professor);

    // Mock dos recursos
    when(recursoRepository.findById(1)).thenReturn(Optional.of(new Recurso(1, "Projetor")));
    when(recursoRepository.findById(2)).thenReturn(Optional.of(new Recurso(2, "Laboratório")));

    turmaDTO.setRecursosIds(List.of(1, 2));

    when(turmaMapper.toEntity(turmaDTO)).thenReturn(turma);
    when(turmaRepository.save(any(Turma.class))).thenReturn(turma);
    when(turmaMapper.toDTO(turma)).thenReturn(turmaDTO);

    TurmaDTO savedTurma = turmaService.save(turmaDTO);

    assertNotNull(savedTurma);
    assertEquals(2, savedTurma.getRecursosIds().size());
  }

}
