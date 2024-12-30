package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.BalcaoDTO;
import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BalcaoServiceTest {

    @Mock
    private BalcaoRepository balcaoRepository;

    @Mock
    private AtendenteRepository atendenteRepository;

    @InjectMocks
    private BalcaoService balcaoService;

    private Balcao balcao;
    private BalcaoDTO balcaoDTO;
    private Atendente atendente;
    private AtendenteDTO atendenteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurando Atendente
        atendente = new Atendente();
        atendente.setAtendenteId(1L);
        atendente.setNome("Nome Atendente");

        atendenteDTO = new AtendenteDTO();
        atendenteDTO.setAtendenteId(1L);
        atendenteDTO.setNome("Nome Atendente");

        // Configurando Balcão
        balcao = new Balcao();
        balcao.setBalcaoId(1L);
        balcao.setNomeBalcao("Nome Balcão");
        balcao.setAtendente(atendente);

        // Configurando BalcaoDTO
        balcaoDTO = new BalcaoDTO();
        balcaoDTO.setBalcaoId(1L);
        balcaoDTO.setNomeBalcao("Nome Balcão");
        balcaoDTO.setAtendente(atendenteDTO);
    }

    @Test
    void testCriarBalcao() {
        when(atendenteRepository.findById(anyLong())).thenReturn(Optional.of(atendente));
        when(balcaoRepository.save(any(Balcao.class))).thenReturn(balcao);

        BalcaoDTO result = balcaoService.criarBalcao(balcaoDTO);

        assertNotNull(result);
        assertEquals(balcaoDTO.getBalcaoId(), result.getBalcaoId());
        assertEquals(balcaoDTO.getNomeBalcao(), result.getNomeBalcao());
        verify(balcaoRepository, times(1)).save(any(Balcao.class));
    }

    @Test
    void testListarBalcoes() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Balcao> page = new PageImpl<>(Collections.singletonList(balcao));
        when(balcaoRepository.findAll(pageable)).thenReturn(page);

        Page<BalcaoDTO> result = balcaoService.listarBalcoes(pageable);

        assertEquals(1, result.getTotalElements());
        assertNotNull(result.getContent().get(0));
        verify(balcaoRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testBuscarBalcaoPorId() {
        when(balcaoRepository.findById(anyLong())).thenReturn(Optional.of(balcao));

        BalcaoDTO result = balcaoService.buscarBalcaoPorId(1L);

        assertNotNull(result);
        assertEquals(balcaoDTO.getBalcaoId(), result.getBalcaoId());
        assertEquals(balcaoDTO.getNomeBalcao(), result.getNomeBalcao());
        verify(balcaoRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarBalcaoPorIdNotFound() {
        when(balcaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> balcaoService.buscarBalcaoPorId(1L));
    }

    @Test
    void testAlterarBalcao() {
        when(balcaoRepository.existsById(anyLong())).thenReturn(true);
        when(balcaoRepository.save(any(Balcao.class))).thenReturn(balcao);

        BalcaoDTO result = balcaoService.alterarBalcao(balcaoDTO);

        assertNotNull(result);
        assertEquals(balcaoDTO.getBalcaoId(), result.getBalcaoId());
        assertEquals(balcaoDTO.getNomeBalcao(), result.getNomeBalcao());
        verify(balcaoRepository, times(1)).save(any(Balcao.class));
        verify(balcaoRepository, times(1)).existsById(anyLong());
    }

    @Test
    void testAlterarBalcaoNotFound() {
        when(balcaoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> balcaoService.alterarBalcao(balcaoDTO));
    }

    @Test
    void testDeletarBalcao() {
        when(balcaoRepository.findById(anyLong())).thenReturn(Optional.of(balcao));

        BalcaoDTO result = balcaoService.deletarBalcao(1L);

        assertNotNull(result);
        assertEquals(balcaoDTO.getBalcaoId(), result.getBalcaoId());
        assertEquals(balcaoDTO.getNomeBalcao(), result.getNomeBalcao());
        verify(balcaoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeletarBalcaoNotFound() {
        when(balcaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> balcaoService.deletarBalcao(1L));
    }
}
