package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.MaquininhaDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.Maquininha;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class MaquininhaServiceTest {

    @Mock
    private MaquininhaRepository maquininhaRepository;

    @InjectMocks
    private MaquininhaService maquininhaService;

    private Maquininha maquininha;
    private MaquininhaDTO maquininhaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar objeto Maquininha
        maquininha = new Maquininha();
        maquininha.setDeviceId(1L);
        maquininha.setSerialNumber("123456");

        // Configurar objeto MaquininhaDTO
        maquininhaDTO = new MaquininhaDTO();
        maquininhaDTO.setDeviceId(1L);
        maquininhaDTO.setSerialNumber("123456");
    }

    @Test
    void testCriarMaquininha() {
        when(maquininhaRepository.save(any(Maquininha.class))).thenReturn(maquininha);

        MaquininhaDTO result = maquininhaService.criarMaquininha(maquininhaDTO);

        assertNotNull(result);
        assertEquals(maquininhaDTO.getDeviceId(), result.getDeviceId());
        assertEquals(maquininhaDTO.getSerialNumber(), result.getSerialNumber());
        verify(maquininhaRepository, times(1)).save(any(Maquininha.class));
    }

    @Test
    void testListarMaquininhas() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Maquininha> page = new PageImpl<>(Collections.singletonList(maquininha));
        when(maquininhaRepository.findAll(pageable)).thenReturn(page);

        Page<MaquininhaDTO> result = maquininhaService.listarMaquininhas(pageable);

        assertEquals(1, result.getTotalElements());
        assertNotNull(result.getContent().get(0));
        verify(maquininhaRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testBuscarMaquininhaPorId() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.of(maquininha));

        MaquininhaDTO result = maquininhaService.buscarMaquininhaPorId(1L);

        assertNotNull(result);
        assertEquals(maquininhaDTO.getDeviceId(), result.getDeviceId());
        assertEquals(maquininhaDTO.getSerialNumber(), result.getSerialNumber());
        verify(maquininhaRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarMaquininhaPorIdNotFound() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> maquininhaService.buscarMaquininhaPorId(1L));
    }

    @Test
    void testAlterarMaquininha() {
        when(maquininhaRepository.existsById(anyLong())).thenReturn(true);
        when(maquininhaRepository.save(any(Maquininha.class))).thenReturn(maquininha);

        MaquininhaDTO result = maquininhaService.alterarMaquininha(maquininhaDTO);

        assertNotNull(result);
        assertEquals(maquininhaDTO.getDeviceId(), result.getDeviceId());
        assertEquals(maquininhaDTO.getSerialNumber(), result.getSerialNumber());
        verify(maquininhaRepository, times(1)).save(any(Maquininha.class));
        verify(maquininhaRepository, times(1)).existsById(anyLong());
    }

    @Test
    void testAlterarMaquininhaNotFound() {
        when(maquininhaRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> maquininhaService.alterarMaquininha(maquininhaDTO));
    }

    @Test
    void testDeletarMaquininha() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.of(maquininha));

        MaquininhaDTO result = maquininhaService.deletarMaquininha(1L);

        assertNotNull(result);
        assertEquals(maquininhaDTO.getDeviceId(), result.getDeviceId());
        verify(maquininhaRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeletarMaquininhaNotFound() {
        when(maquininhaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> maquininhaService.deletarMaquininha(1L));
    }
}
