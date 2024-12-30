package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.MaquininhaDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.MaquininhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MaquininhaControllerTest {

    @Mock
    private MaquininhaService maquininhaService;

    @InjectMocks
    private MaquininhaController maquininhaController;

    private MaquininhaDTO maquininhaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        maquininhaDTO = new MaquininhaDTO();
        maquininhaDTO.setDeviceId(1L);
        // Configure outros campos do MaquininhaDTO conforme necessário
    }

    @Test
    void testCriarMaquininha() {
        when(maquininhaService.criarMaquininha(any(MaquininhaDTO.class))).thenReturn(maquininhaDTO);

        ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> response = maquininhaController.criarMaquininha(maquininhaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(maquininhaDTO, response.getBody().getData());
        assertEquals("Criada com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testBuscarMaquininhaPorId() {
        when(maquininhaService.buscarMaquininhaPorId(1L)).thenReturn(maquininhaDTO);

        ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> response = maquininhaController.buscarMaquininhaPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(maquininhaDTO, response.getBody().getData());
        assertEquals("Valores retornados com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testListarMaquininhas() {
        Page<MaquininhaDTO> page = new PageImpl<>(Collections.singletonList(maquininhaDTO));
        when(maquininhaService.listarMaquininhas(any(Pageable.class))).thenReturn(page);

        ResponseEntity<SuccessResponseDTO<PageInfoDTO<MaquininhaDTO>>> response = maquininhaController.listarMaquininhas(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getData().getTotalPages());
        assertEquals(1, response.getBody().getData().getTotalElements());
        assertEquals("Maquininhas retornadas com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testAlterarMaquininha() {
        when(maquininhaService.alterarMaquininha(any(MaquininhaDTO.class))).thenReturn(maquininhaDTO);

        ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> response = maquininhaController.alterarMaquininha(1L, maquininhaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(maquininhaDTO, response.getBody().getData());
        assertEquals("Atualizada com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testDeletarMaquininha() {
        when(maquininhaService.deletarMaquininha(1L)).thenReturn(maquininhaDTO);

        ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> response = maquininhaController.deletarMaquininha(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(maquininhaDTO, response.getBody().getData());
        assertEquals("Deletada com sucesso!", response.getBody().getMessage());
    }
}
