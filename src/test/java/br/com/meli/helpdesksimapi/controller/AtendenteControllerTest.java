package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.AtendenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtendenteControllerTest {

    @Mock
    private AtendenteService atendenteService;

    @InjectMocks
    private AtendenteController atendenteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarAtendentes() {
        Page<AtendenteDTO> paginatedResult = new PageImpl<>(Collections.singletonList(new AtendenteDTO()), PageRequest.of(0, 10), 1);
        PageInfoDTO<AtendenteDTO> pageInfo = new PageInfoDTO<>(paginatedResult.getContent(), paginatedResult.getTotalPages(), paginatedResult.getTotalElements());
        SuccessResponseDTO<PageInfoDTO<AtendenteDTO>> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atendentes retornados com sucesso!", pageInfo);
        when(atendenteService.listarAtendentes(any(PageRequest.class))).thenReturn(paginatedResult);

        ResponseEntity<SuccessResponseDTO<PageInfoDTO<AtendenteDTO>>> response = atendenteController.listarAtendentes(PageRequest.of(0, 10));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(atendenteService, times(1)).listarAtendentes(any(PageRequest.class));
    }

    @Test
    void buscarPorId() {
        Long atendenteId = 1L;
        AtendenteDTO atendenteDTO = new AtendenteDTO();
        SuccessResponseDTO<AtendenteDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", atendenteDTO);
        when(atendenteService.buscarAtendentePorId(atendenteId)).thenReturn(atendenteDTO);

        ResponseEntity<SuccessResponseDTO<AtendenteDTO>> response = atendenteController.buscarPorId(atendenteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(atendenteService, times(1)).buscarAtendentePorId(atendenteId);
    }

    @Test
    void criarAtendente() {
        AtendenteDTO atendenteDTO = new AtendenteDTO();
        SuccessResponseDTO<AtendenteDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Criado com sucesso!", atendenteDTO);
        when(atendenteService.criarAtendente(any(AtendenteDTO.class))).thenReturn(atendenteDTO);

        ResponseEntity<SuccessResponseDTO<AtendenteDTO>> response = atendenteController.criarAtendente(atendenteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(atendenteService, times(1)).criarAtendente(atendenteDTO);
    }

    @Test
    void deletarAtendente() {
        Long atendenteId = 1L;
        AtendenteDTO atendenteDTO = new AtendenteDTO();
        SuccessResponseDTO<AtendenteDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atendente deletado com sucesso!", atendenteDTO);
        when(atendenteService.deletarAtendente(atendenteId)).thenReturn(atendenteDTO);

        ResponseEntity<SuccessResponseDTO<AtendenteDTO>> response = atendenteController.deletarAtendente(atendenteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(atendenteService, times(1)).deletarAtendente(atendenteId);
    }

    @Test
    void alterarAtendente() {
        Long atendenteId = 1L;
        AtendenteDTO atendenteDTO = new AtendenteDTO();
        atendenteDTO.setAtendenteId(atendenteId);
        when(atendenteService.alterarAtendente(any(AtendenteDTO.class))).thenReturn(atendenteDTO);

        ResponseEntity<AtendenteDTO> response = atendenteController.alterarAtendente(atendenteId, atendenteDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atendenteDTO, response.getBody());
        verify(atendenteService, times(1)).alterarAtendente(atendenteDTO);
    }
}
