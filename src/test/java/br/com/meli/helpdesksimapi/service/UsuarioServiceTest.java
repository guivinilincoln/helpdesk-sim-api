package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.UsuarioDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.Usuario;
import br.com.meli.helpdesksimapi.repository.UsuarioRepository;
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

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar objeto Usuario
        usuario = new Usuario();
        usuario.setUsuarioId(1L);
        usuario.setNomeUsuario("Usuario Teste");

        // Configurar objeto UsuarioDTO
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuarioId(1L);
        usuarioDTO.setNomeUsuario("Usuario Teste");
    }

    @Test
    void testCriarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO result = usuarioService.criarUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals(usuarioDTO.getUsuarioId(), result.getUsuarioId());
        assertEquals(usuarioDTO.getNomeUsuario(), result.getNomeUsuario());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testListarUsuarios() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> page = new PageImpl<>(Collections.singletonList(usuario));
        when(usuarioRepository.findAll(pageable)).thenReturn(page);

        Page<UsuarioDTO> result = usuarioService.listarUsuarios(pageable);

        assertEquals(1, result.getTotalElements());
        assertNotNull(result.getContent().get(0));
        verify(usuarioRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testBuscarUsuarioPorId() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        UsuarioDTO result = usuarioService.buscarUsuarioPorId(1L);

        assertNotNull(result);
        assertEquals(usuarioDTO.getUsuarioId(), result.getUsuarioId());
        assertEquals(usuarioDTO.getNomeUsuario(), result.getNomeUsuario());
        verify(usuarioRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarUsuarioPorIdNotFound() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.buscarUsuarioPorId(1L));
    }

    @Test
    void testAlterarUsuario() {
        when(usuarioRepository.existsById(anyLong())).thenReturn(true);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO result = usuarioService.alterarUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals(usuarioDTO.getUsuarioId(), result.getUsuarioId());
        assertEquals(usuarioDTO.getNomeUsuario(), result.getNomeUsuario());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(usuarioRepository, times(1)).existsById(anyLong());
    }

    @Test
    void testAlterarUsuarioNotFound() {
        when(usuarioRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.alterarUsuario(usuarioDTO));
    }

    @Test
    void testDeletarUsuario() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        UsuarioDTO result = usuarioService.deletarUsuario(1L);

        assertNotNull(result);
        assertEquals(usuarioDTO.getUsuarioId(), result.getUsuarioId());
        verify(usuarioRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeletarUsuarioNotFound() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.deletarUsuario(1L));
    }
}
