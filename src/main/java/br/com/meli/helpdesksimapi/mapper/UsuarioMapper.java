package br.com.meli.helpdesksimapi.mapper;

import br.com.meli.helpdesksimapi.dto.UsuarioDTO;
import br.com.meli.helpdesksimapi.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setCustomerId(usuario.getCustomerId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioDTO.getUsuarioId());
        usuario.setCustomerId(usuarioDTO.getCustomerId());
        usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
        return usuario;
    }
}
