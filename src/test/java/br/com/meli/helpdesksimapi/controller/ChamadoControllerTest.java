package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.ChamadoDTO;
import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.ChamadoService;
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

import java.nio.file.AccessDeniedException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChamadoControllerTest {

    @Mock
    private ChamadoService chamadoService;

    @InjectMocks
    private ChamadoController chamadoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarChamado() throws AccessDeniedException {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        SuccessResponseDTO<ChamadoDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Chamado criado com sucesso!", chamadoDTO);
        when(chamadoService.criarChamado(any(ChamadoDTO.class))).thenReturn(chamadoDTO);

        ResponseEntity<SuccessResponseDTO<ChamadoDTO>> response = chamadoController.criarChamado(chamadoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(chamadoService, times(1)).criarChamado(chamadoDTO);
    }

    @Test
    void listarChamados() {
        Page<ChamadoDTO> paginatedResult = new PageImpl<>(Collections.singletonList(new ChamadoDTO()), PageRequest.of(0, 10), 1);
        PageInfoDTO<ChamadoDTO> pageInfo = new PageInfoDTO<>(paginatedResult.getContent(), paginatedResult.getTotalPages(), paginatedResult.getTotalElements());
        SuccessResponseDTO<PageInfoDTO<ChamadoDTO>> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamados retornados com sucesso!", pageInfo);
        when(chamadoService.listarChamados(any(PageRequest.class))).thenReturn(paginatedResult);

        ResponseEntity<SuccessResponseDTO<PageInfoDTO<ChamadoDTO>>> response = chamadoController.listarChamados(PageRequest.of(0, 10));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(chamadoService, times(1)).listarChamados(any(PageRequest.class));
    }

    @Test
    void buscarChamadoPorId() {
        Long chamadoId = 1L;
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        SuccessResponseDTO<ChamadoDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamado retornado com sucesso!", chamadoDTO);
        when(chamadoService.buscarChamadoPorId(chamadoId)).thenReturn(chamadoDTO);

        ResponseEntity<SuccessResponseDTO<ChamadoDTO>> response = chamadoController.buscarChamadoPorId(chamadoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(chamadoService, times(1)).buscarChamadoPorId(chamadoId);
    }

    @Test
    void alterarChamado() {
        Long chamadoId = 1L;
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setChamadoId(chamadoId);
        SuccessResponseDTO<ChamadoDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamado atualizado com sucesso!", chamadoDTO);
        when(chamadoService.alterarChamado(any(ChamadoDTO.class))).thenReturn(chamadoDTO);

        ResponseEntity<SuccessResponseDTO<ChamadoDTO>> response = chamadoController.alterarChamado(chamadoId, chamadoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(chamadoService, times(1)).alterarChamado(chamadoDTO);
    }

    @Test
    void deletarChamado() {
        Long chamadoId = 1L;
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        SuccessResponseDTO<ChamadoDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamado deletado com sucesso!", chamadoDTO);
        when(chamadoService.deletarChamado(chamadoId)).thenReturn(chamadoDTO);

        ResponseEntity<SuccessResponseDTO<ChamadoDTO>> response = chamadoController.deletarChamado(chamadoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(chamadoService, times(1)).deletarChamado(chamadoId);
    }
}
