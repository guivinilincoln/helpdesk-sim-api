package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.ChamadoDTO;
import br.com.meli.helpdesksimapi.dto.MaquininhaDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.*;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.repository.ChamadoRepository;
import br.com.meli.helpdesksimapi.repository.MaquininhaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ChamadoServiceTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private BalcaoRepository balcaoRepository;

    @Mock
    private MaquininhaRepository maquininhaRepository;

    @InjectMocks
    private ChamadoService chamadoService;

    private Chamado chamado;
    private ChamadoDTO chamadoDTO;
    private Maquininha maquininha;
    private Balcao balcao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração da Maquininha
        maquininha = new Maquininha();
        maquininha.setDeviceId(1L);
        maquininha.setSerialNumber("123456");

        // Configuração da MaquininhaDTO
        MaquininhaDTO maquininhaDTO = new MaquininhaDTO();
        maquininhaDTO.setDeviceId(1L);
        maquininhaDTO.setSerialNumber("123456");

        // Configuração do Chamado
        chamado = new Chamado();
        chamado.setChamadoId(1L);
        chamado.setStatus(Status.ABERTO);
        chamado.setMaquininha(maquininha);

        // Configuração do ChamadoDTO
        chamadoDTO = new ChamadoDTO();
        chamadoDTO.setChamadoId(1L);
        chamadoDTO.setMaquininha(maquininhaDTO);

        // Configuração do Balcao
        balcao = new Balcao();
        balcao.setBalcaoId(1L);
    }

    @Test
    void testCriarChamadoMaquininhaNaoExistente() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> chamadoService.criarChamado(chamadoDTO));
        verify(maquininhaRepository, times(1)).findById(anyLong());
    }

    @Test
    void testCriarChamadoJaExistenteParaUsuarioAtual() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.of(maquininha));
        when(chamadoRepository.findByUsuarioAndMaquininhaAndStatusNot(any(), any(), any())).thenReturn(Collections.singletonList(chamado));

        assertThrows(IllegalArgumentException.class, () -> chamadoService.criarChamado(chamadoDTO));
        verify(chamadoRepository, times(1)).findByUsuarioAndMaquininhaAndStatusNot(any(), any(), any());
    }

    @Test
    void testCriarChamadoSerialNumberEmUsoPorOutroUsuario() {
        Chamado outroChamado = new Chamado();
        outroChamado.setUsuario(new Usuario()); // Cria um objeto Usuario para o outro chamado
        outroChamado.setMaquininha(maquininha);

        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.of(maquininha));
        when(chamadoRepository.findByUsuarioAndMaquininhaAndStatusNot(any(), any(), any())).thenReturn(Collections.emptyList());
        when(chamadoRepository.findByMaquininhaSerialNumberAndStatusNot(anyString(), any())).thenReturn(Collections.singletonList(outroChamado));

        assertThrows(IllegalArgumentException.class, () -> chamadoService.criarChamado(chamadoDTO));
    }

    @Test
    void testCriarChamado() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.of(maquininha));
        when(chamadoRepository.findByUsuarioAndMaquininhaAndStatusNot(any(), any(), any())).thenReturn(Collections.emptyList());
        when(chamadoRepository.findByMaquininhaSerialNumberAndStatusNot(anyString(), any())).thenReturn(Collections.emptyList());
        when(chamadoRepository.save(any(Chamado.class))).thenReturn(chamado);
        when(balcaoRepository.findAll()).thenReturn(Collections.singletonList(balcao));

        ChamadoDTO result = chamadoService.criarChamado(chamadoDTO);

        assertNotNull(result);
        assertEquals(chamadoDTO.getChamadoId(), result.getChamadoId());
        verify(chamadoRepository, times(1)).save(any(Chamado.class));
    }

    @Test
    void testListarChamados() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Chamado> page = new PageImpl<>(Collections.singletonList(chamado));
        when(chamadoRepository.findAll(pageable)).thenReturn(page);

        Page<ChamadoDTO> result = chamadoService.listarChamados(pageable);

        assertEquals(1, result.getTotalElements());
        assertNotNull(result.getContent().get(0));
        verify(chamadoRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testBuscarChamadoPorId() {
        when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(chamado));

        ChamadoDTO result = chamadoService.buscarChamadoPorId(1L);

        assertNotNull(result);
        assertEquals(chamadoDTO.getChamadoId(), result.getChamadoId());
        verify(chamadoRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarChamadoPorIdNotFound() {
        when(chamadoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> chamadoService.buscarChamadoPorId(1L));
    }

    @Test
    void testAlterarChamado() {
        when(chamadoRepository.existsById(anyLong())).thenReturn(true);
        when(chamadoRepository.save(any(Chamado.class))).thenReturn(chamado);

        ChamadoDTO result = chamadoService.alterarChamado(chamadoDTO);

        assertNotNull(result);
        assertEquals(chamadoDTO.getChamadoId(), result.getChamadoId());
        verify(chamadoRepository, times(1)).save(any(Chamado.class));
        verify(chamadoRepository, times(1)).existsById(anyLong());
    }

    @Test
    void testAlterarChamadoNotFound() {
        when(chamadoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> chamadoService.alterarChamado(chamadoDTO));
    }

    @Test
    void testDeletarChamado() {
        when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(chamado));

        ChamadoDTO result = chamadoService.deletarChamado(1L);

        assertNotNull(result);
        assertEquals(chamadoDTO.getChamadoId(), result.getChamadoId());
        verify(chamadoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeletarChamadoNotFound() {
        when(chamadoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> chamadoService.deletarChamado(1L));
    }

    @Test
    void testBuscarChamadoPorId_NullId() {
        assertThrows(ResourceNotFoundException.class, () -> chamadoService.buscarChamadoPorId(null));
    }

    @Test
    void testDeletarChamado_NullId() {
        assertThrows(ResourceNotFoundException.class, () -> chamadoService.deletarChamado(null));
    }
}
