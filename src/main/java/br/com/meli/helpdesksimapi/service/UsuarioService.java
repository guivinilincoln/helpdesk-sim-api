package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Usuario;
import br.com.meli.helpdesksimapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> buscarPorCustumId(String custumId) {
         usuarioRepository.buscarUsuarioCustomerId(custumId);
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void removerUsaurio(Long id) {
        usuarioRepository.deleteById(id);
    }


}
