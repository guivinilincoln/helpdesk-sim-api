package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.BalcaoDTO;
import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.BalcaoService;
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

class BalcaoControllerTest {

    @Mock
    private BalcaoService balcaoService;

    @InjectMocks
    private BalcaoController balcaoController;

    private BalcaoDTO balcaoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        balcaoDTO = new BalcaoDTO();
        balcaoDTO.setBalcaoId(1L);
        // Configure outros campos do BalcaoDTO conforme necessário
    }

    @Test
    void testCriarBalcao() {
        when(balcaoService.criarBalcao(any(BalcaoDTO.class))).thenReturn(balcaoDTO);

        ResponseEntity<SuccessResponseDTO<BalcaoDTO>> response = balcaoController.criarBalcao(balcaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(balcaoDTO, response.getBody().getData());
        assertEquals("Criado com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testBuscarBalcaoPorId() {
        when(balcaoService.buscarBalcaoPorId(1L)).thenReturn(balcaoDTO);

        ResponseEntity<SuccessResponseDTO<BalcaoDTO>> response = balcaoController.buscarBalcaoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balcaoDTO, response.getBody().getData());
        assertEquals("Valores retornados com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testListarBalcoes() {
        Page<BalcaoDTO> page = new PageImpl<>(Collections.singletonList(balcaoDTO));
        when(balcaoService.listarBalcoes(any(Pageable.class))).thenReturn(page);

        ResponseEntity<SuccessResponseDTO<PageInfoDTO<BalcaoDTO>>> response = balcaoController.listarBalcoes(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getData().getTotalPages());
        assertEquals(1, response.getBody().getData().getTotalElements());
        assertEquals("Balcoes retornados com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testAlterarBalcao() {
        when(balcaoService.alterarBalcao(any(BalcaoDTO.class))).thenReturn(balcaoDTO);

        ResponseEntity<SuccessResponseDTO<BalcaoDTO>> response = balcaoController.alterarBalcao(1L, balcaoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balcaoDTO, response.getBody().getData());
        assertEquals("Atualizado com sucesso!", response.getBody().getMessage());
    }

    @Test
    void testDeletarBalcao() {
        when(balcaoService.deletarBalcao(1L)).thenReturn(balcaoDTO);

        ResponseEntity<SuccessResponseDTO<BalcaoDTO>> response = balcaoController.deletarBalcao(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balcaoDTO, response.getBody().getData());
        assertEquals("Deletado com sucesso!", response.getBody().getMessage());
    }
}
