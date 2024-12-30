package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.dto.UsuarioDTO;
import br.com.meli.helpdesksimapi.service.UsuarioService;
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

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        SuccessResponseDTO<UsuarioDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Criado com sucesso!", usuarioDTO);
        when(usuarioService.criarUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        ResponseEntity<SuccessResponseDTO<UsuarioDTO>> response = usuarioController.criarUsuario(usuarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(usuarioService, times(1)).criarUsuario(usuarioDTO);
    }

    @Test
    void buscarUsuarioPorId() {
        Long usuarioId = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        SuccessResponseDTO<UsuarioDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", usuarioDTO);
        when(usuarioService.buscarUsuarioPorId(usuarioId)).thenReturn(usuarioDTO);

        ResponseEntity<SuccessResponseDTO<UsuarioDTO>> response = usuarioController.buscarUsuarioPorId(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(usuarioService, times(1)).buscarUsuarioPorId(usuarioId);
    }

    @Test
    void listarUsuarios() {
        Page<UsuarioDTO> paginatedResult = new PageImpl<>(Collections.singletonList(new UsuarioDTO()), PageRequest.of(0, 10), 1);
        PageInfoDTO<UsuarioDTO> pageInfo = new PageInfoDTO<>(paginatedResult.getContent(), paginatedResult.getTotalPages(), paginatedResult.getTotalElements());
        SuccessResponseDTO<PageInfoDTO<UsuarioDTO>> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Usuários retornados com sucesso!", pageInfo);
        when(usuarioService.listarUsuarios(any(PageRequest.class))).thenReturn(paginatedResult);

        ResponseEntity<SuccessResponseDTO<PageInfoDTO<UsuarioDTO>>> response = usuarioController.listarUsuarios(PageRequest.of(0, 10));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(usuarioService, times(1)).listarUsuarios(any(PageRequest.class));
    }

    @Test
    void alterarUsuario() {
        Long usuarioId = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuarioId(usuarioId);
        SuccessResponseDTO<UsuarioDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atualizado com sucesso!", usuarioDTO);
        when(usuarioService.alterarUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        ResponseEntity<SuccessResponseDTO<UsuarioDTO>> response = usuarioController.alterarUsuario(usuarioId, usuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(usuarioService, times(1)).alterarUsuario(usuarioDTO);
    }

    @Test
    void deletarUsuario() {
        Long usuarioId = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        SuccessResponseDTO<UsuarioDTO> successResponseDTO = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Deletado com sucesso!", usuarioDTO);
        when(usuarioService.deletarUsuario(usuarioId)).thenReturn(usuarioDTO);

        ResponseEntity<SuccessResponseDTO<UsuarioDTO>> response = usuarioController.deletarUsuario(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successResponseDTO, response.getBody());
        verify(usuarioService, times(1)).deletarUsuario(usuarioId);
    }
}
