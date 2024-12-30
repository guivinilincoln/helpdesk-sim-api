package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.model.Status;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.repository.ChamadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ChamadoProcessorTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private BalcaoRepository balcaoRepository;

    @InjectMocks
    private ChamadoProcessor chamadoProcessor;

    private Chamado chamado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        chamado = new Chamado();
        chamado.setChamadoId(1L);
        chamado.setStatus(Status.ABERTO);
    }

    @Test
    void testMoveAbertosParaEmAtendimento() {
        when(chamadoRepository.findByStatusOrderByDataChamado(Status.ABERTO))
                .thenReturn(Collections.singletonList(chamado));

        chamadoProcessor.moveAbertosParaEmAtendimento();

        verify(chamadoRepository, times(1)).save(any(Chamado.class));
        assertEquals(Status.EM_ATENDIMENTO, chamado.getStatus());
    }

    @Test
    void testMoveEmAtendimentoParaConcluido() {
        chamado.setStatus(Status.EM_ATENDIMENTO);
        when(chamadoRepository.findByStatusOrderByDataChamado(Status.EM_ATENDIMENTO))
                .thenReturn(Collections.singletonList(chamado));

        chamadoProcessor.moveEmAtendimentoParaConcluido();

        verify(chamadoRepository, times(1)).save(any(Chamado.class));
        assertEquals(Status.CONCLUIDO, chamado.getStatus());
    }

    @Test
    void testGerenciarFilaDeEspera() {
        chamado.setStatus(Status.EM_ESPERA);
        Balcao balcaoDisponivel = new Balcao();
        when(chamadoRepository.findByStatus(Status.EM_ESPERA))
                .thenReturn(Collections.singletonList(chamado));
        when(balcaoRepository.findAll()).thenReturn(Collections.singletonList(balcaoDisponivel));
        when(chamadoRepository.countByBalcaoAndStatusNot(balcaoDisponivel, Status.CONCLUIDO))
                .thenReturn(0);

        chamadoProcessor.gerenciarFilaDeEspera();

        verify(chamadoRepository, times(1)).save(any(Chamado.class));
        assertEquals(Status.ABERTO, chamado.getStatus());
        assertEquals(balcaoDisponivel, chamado.getBalcao());
    }
}
