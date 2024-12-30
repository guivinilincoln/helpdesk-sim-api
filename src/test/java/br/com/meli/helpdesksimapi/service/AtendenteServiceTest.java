package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.mapper.AtendenteMapper;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
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

class AtendenteServiceTest {

    @Mock
    private AtendenteRepository atendenteRepository;

    @InjectMocks
    private AtendenteService atendenteService;

    private Atendente atendente;
    private AtendenteDTO atendenteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        atendente = new Atendente();
        atendente.setAtendenteId(1L);
        atendente.setNome("Guiherme Teste");

        atendenteDTO = new AtendenteDTO();
        atendenteDTO.setAtendenteId(1L);
        atendenteDTO.setNome("Guiherme Teste");
    }

    @Test
    void testCriarAtendente() {
        when(atendenteRepository.save(any(Atendente.class))).thenReturn(atendente);

        AtendenteDTO result = atendenteService.criarAtendente(atendenteDTO);

        assertNotNull(result);
        assertEquals(atendenteDTO.getAtendenteId(), result.getAtendenteId());
        assertEquals(atendenteDTO.getNome(), result.getNome());
        verify(atendenteRepository, times(1)).save(any(Atendente.class));
    }

    @Test
    void testBuscarAtendentePorId() {
        when(atendenteRepository.findById(anyLong())).thenReturn(Optional.of(atendente));

        AtendenteDTO result = atendenteService.buscarAtendentePorId(1L);

        assertNotNull(result);
        assertEquals(atendenteDTO.getAtendenteId(), result.getAtendenteId());
        assertEquals(atendenteDTO.getNome(), result.getNome());
        verify(atendenteRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarAtendentePorIdNotFound() {
        when(atendenteRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> atendenteService.buscarAtendentePorId(1L));
    }

    @Test
    void testListarAtendentes() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Atendente> page = new PageImpl<>(Collections.singletonList(atendente));
        when(atendenteRepository.findAll(pageable)).thenReturn(page);

        Page<AtendenteDTO> result = atendenteService.listarAtendentes(pageable);

        assertEquals(1, result.getTotalElements());
        assertNotNull(result.getContent().get(0));
        verify(atendenteRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testAlterarAtendente() {
        when(atendenteRepository.existsById(anyLong())).thenReturn(true);
        when(atendenteRepository.save(any(Atendente.class))).thenReturn(atendente);

        AtendenteDTO result = atendenteService.alterarAtendente(atendenteDTO);

        assertNotNull(result);
        assertEquals(atendenteDTO.getAtendenteId(), result.getAtendenteId());
        assertEquals(atendenteDTO.getNome(), result.getNome());
        verify(atendenteRepository, times(1)).save(any(Atendente.class));
        verify(atendenteRepository, times(1)).existsById(anyLong());
    }

    @Test
    void testAlterarAtendenteNotFound() {
        when(atendenteRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> atendenteService.alterarAtendente(atendenteDTO));
    }

    @Test
    void testDeletarAtendente() {
        when(atendenteRepository.findById(anyLong())).thenReturn(Optional.of(atendente));

        AtendenteDTO result = atendenteService.deletarAtendente(1L);

        assertNotNull(result);
        assertEquals(atendenteDTO.getAtendenteId(), result.getAtendenteId());
        assertEquals(atendenteDTO.getNome(), result.getNome());
        verify(atendenteRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeletarAtendenteNotFound() {
        when(atendenteRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> atendenteService.deletarAtendente(1L));
    }
}
