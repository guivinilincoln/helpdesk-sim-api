package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.UsuarioDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.mapper.UsuarioMapper;
import br.com.meli.helpdesksimapi.model.Usuario;
import br.com.meli.helpdesksimapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(salvo);
    }

    public Page<UsuarioDTO> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(UsuarioMapper::toDTO);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID " + id + " não encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO alterarUsuario(UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(usuarioDTO.getUsuarioId())) {
            throw new ResourceNotFoundException("Usuário com o ID " + usuarioDTO.getUsuarioId() + " não encontrado para alterar");
        }
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(salvo);
    }

    public UsuarioDTO deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID " + id + " não encontrado"));
        usuarioRepository.deleteById(id);
        return UsuarioMapper.toDTO(usuario);
    }
}
