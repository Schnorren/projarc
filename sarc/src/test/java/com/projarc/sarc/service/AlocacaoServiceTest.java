package com.projarc.sarc.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projarc.sarc.domain.model.Alocacao;
import com.projarc.sarc.domain.model.Aula;
import com.projarc.sarc.domain.model.HorarioEnum;
import com.projarc.sarc.domain.model.Recurso;
import com.projarc.sarc.domain.model.Turma;
import com.projarc.sarc.domain.repository.AlocacaoRepository;
import com.projarc.sarc.dto.AlocacaoDTO;
import com.projarc.sarc.exception.DataIntegrityException;
import com.projarc.sarc.mapper.AlocacaoMapper;

public class AlocacaoServiceTest {

  @Mock
  private AlocacaoRepository alocacaoRepository;

  @Mock
  private AulaService aulaService;

  @Mock
  private RecursoService recursoService;

  @Mock
  private AlocacaoMapper alocacaoMapper;

  @InjectMocks
  private AlocacaoService alocacaoService;

  private AlocacaoDTO alocacaoDTO;
  private Aula aula;
  private Recurso recurso;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Simulação dos objetos
    aula = new Aula();
    aula.setId(1L);
    aula.setHorario(HorarioEnum.A);
    aula.setData(LocalDate.now());

    Turma turma = new Turma();
    turma.setCodigo(1);
    aula.setTurma(turma);

    recurso = new Recurso();
    recurso.setCodigo(1);

    alocacaoDTO = new AlocacaoDTO();
    alocacaoDTO.setAulaId(aula.getId());
    alocacaoDTO.setRecursoCodigo(recurso.getCodigo());
    alocacaoDTO.setData(LocalDate.now());
    alocacaoDTO.setHorario(HorarioEnum.A);
  }

  @Test
  void shouldThrowExceptionWhenRecursoIsAlreadyAllocated() {
    when(aulaService.findByIdEntity(anyLong())).thenReturn(aula);
    when(recursoService.findByIdEntity(anyInt())).thenReturn(recurso);

    when(recursoService.isRecursoAvailable(anyInt(), any(HorarioEnum.class), any(LocalDate.class)))
        .thenReturn(true);

    when(alocacaoRepository.existsByRecurso_CodigoAndDataAndHorario(
        eq(alocacaoDTO.getRecursoCodigo()),
        eq(alocacaoDTO.getData()),
        eq(alocacaoDTO.getHorario()))).thenReturn(true);

    assertThrows(DataIntegrityException.class, () -> alocacaoService.save(alocacaoDTO));

    verify(alocacaoRepository, times(1)).existsByRecurso_CodigoAndDataAndHorario(
        eq(alocacaoDTO.getRecursoCodigo()),
        eq(alocacaoDTO.getData()),
        eq(alocacaoDTO.getHorario()));
  }

  @Test
  void shouldSaveWhenRecursoIsNotAllocated() {
    when(aulaService.findByIdEntity(anyLong())).thenReturn(aula);
    when(recursoService.findByIdEntity(anyInt())).thenReturn(recurso);
    when(alocacaoRepository.existsByRecurso_CodigoAndDataAndHorario(
        eq(alocacaoDTO.getRecursoCodigo()), eq(alocacaoDTO.getData()), eq(alocacaoDTO.getHorario()))).thenReturn(false);

    when(recursoService.isRecursoAvailable(anyInt(), any(HorarioEnum.class), any(LocalDate.class)))
        .thenReturn(true);

    Alocacao alocacao = new Alocacao();
    when(alocacaoMapper.toEntity(any(AlocacaoDTO.class))).thenReturn(alocacao);
    when(alocacaoRepository.save(any(Alocacao.class))).thenReturn(alocacao);
    when(alocacaoMapper.toDTO(any(Alocacao.class))).thenReturn(alocacaoDTO);

    AlocacaoDTO savedAlocacao = alocacaoService.save(alocacaoDTO);

    verify(alocacaoRepository, times(1)).save(any(Alocacao.class));
    verify(alocacaoMapper, times(1)).toDTO(any(Alocacao.class));
  }

  @Test
  void shouldThrowExceptionWhenRecursoIsNotAllocatedDuringScheduledClass() {
    // Simular a aula e a turma com horários diferentes da alocação
    aula.setData(LocalDate.of(2024, 10, 15)); // Data diferente da alocação
    aula.setHorario(HorarioEnum.B); // Horário diferente da alocação
    when(aulaService.findByIdEntity(anyLong())).thenReturn(aula);

    when(recursoService.findByIdEntity(anyInt())).thenReturn(recurso);

    // Simular a disponibilidade do recurso (não é o foco deste teste)
    when(recursoService.isRecursoAvailable(anyInt(), any(HorarioEnum.class), any(LocalDate.class)))
        .thenReturn(true);

    // Executa o teste e espera que seja lançada uma DataIntegrityException devido
    // ao horário errado
    assertThrows(DataIntegrityException.class, () -> alocacaoService.save(alocacaoDTO));

    // Verificar se a exceção foi lançada devido ao horário e data da aula não
    // coincidirem
    verify(alocacaoRepository, never()).save(any(Alocacao.class)); // Certifica que o save não foi chamado
  }

  @Test
  void shouldSaveWhenRecursoIsAllocatedDuringScheduledClass() {
    // Simular a aula com a mesma data e horário da alocação
    aula.setData(alocacaoDTO.getData()); // Mesma data
    aula.setHorario(alocacaoDTO.getHorario()); // Mesmo horário
    when(aulaService.findByIdEntity(anyLong())).thenReturn(aula);

    when(recursoService.findByIdEntity(anyInt())).thenReturn(recurso);

    // Simular que o recurso está disponível
    when(recursoService.isRecursoAvailable(anyInt(), any(HorarioEnum.class), any(LocalDate.class)))
        .thenReturn(true);

    Alocacao alocacao = new Alocacao();
    when(alocacaoMapper.toEntity(any(AlocacaoDTO.class))).thenReturn(alocacao);
    when(alocacaoRepository.save(any(Alocacao.class))).thenReturn(alocacao);
    when(alocacaoMapper.toDTO(any(Alocacao.class))).thenReturn(alocacaoDTO);

    // Executa o teste e verifica se o recurso foi salvo corretamente
    AlocacaoDTO savedAlocacao = alocacaoService.save(alocacaoDTO);

    verify(alocacaoRepository, times(1)).save(any(Alocacao.class)); // Certifica que o save foi chamado
  }
}
