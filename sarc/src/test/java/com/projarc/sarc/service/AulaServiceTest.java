package com.projarc.sarc.service;

import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.domain.model.DiaSemanaEnum;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Semestre;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.AulaRepository;
import com.projarc.sarc.dto.AulaDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.mapper.AulaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AulaServiceTest {

  @Mock
  private AulaRepository aulaRepository;

  @Mock
  private AulaMapper aulaMapper;

  @Mock
  private TurmaService turmaService;

  @Mock
  private SemestreService semestreService;

  @InjectMocks
  private AulaService aulaService;

  private AulaDTO aulaDTO;
  private Turma turma;
  private Semestre semestre;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Mock da turma e dos dados de aula
    turma = new Turma();
    turma.setCodigo(1);
    turma.setHorario(HorarioEnum.A);
    turma.setDiaSemana(DiaSemanaEnum.SEGUNDA);

    // Mock do semestre atual
    semestre = new Semestre();
    semestre.setAtivo(true);
    semestre.setDataInicio(LocalDate.of(2024, 1, 1));
    semestre.setDataFim(LocalDate.of(2024, 6, 30));

    // Mock do DTO de Aula
    aulaDTO = new AulaDTO();
    aulaDTO.setTurmaCodigo(turma.getCodigo());
    aulaDTO.setHorario(HorarioEnum.A);
    aulaDTO.setDiaSemana(turma.getDiaSemana());
    aulaDTO.setData(LocalDate.of(2024, 2, 15));

    // Mock do mapeamento de DTO para Entidade
    Aula aula = new Aula();
    aula.setTurma(turma);
    aula.setHorario(aulaDTO.getHorario());
    aula.setData(aulaDTO.getData());

    // Configurando mocks
    when(aulaMapper.toEntity(any(AulaDTO.class))).thenReturn(aula);
    when(aulaRepository.save(any(Aula.class))).thenReturn(aula);
    when(turmaService.findByIdEntity(anyInt())).thenReturn(turma);
    when(semestreService.getCurrentSemester()).thenReturn(semestre); // Mock do SemestreService
  }

  @Test
  void shouldSaveWhenHorarioIsInUniversitySchedule() {
    // Configurando o aulaDTO corretamente para passar pela validação
    aulaDTO.setDiaSemana(turma.getDiaSemana());
    aulaDTO.setHorario(HorarioEnum.A);
    aulaDTO.setData(LocalDate.of(2024, 2, 15));

    aulaService.save(aulaDTO);

    verify(aulaRepository, times(1)).save(any(Aula.class));
  }

  @Test
  void shouldThrowExceptionWhenHorarioIsNotInUniversitySchedule() {
    // Configura um horário inválido no DTO
    aulaDTO.setHorario(null);

    assertThrows(DataIntegrityException.class, () -> aulaService.save(aulaDTO));

    verify(aulaRepository, never()).save(any(Aula.class));
  }
}
