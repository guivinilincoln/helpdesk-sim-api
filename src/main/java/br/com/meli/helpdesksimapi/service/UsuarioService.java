package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.Usuario;
import br.com.meli.helpdesksimapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        verificarCustumerId(usuario.getCustomerId());
        return usuarioRepository.save(usuario);
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario com ID " + id + " Não encontrado"));
    }


    public Usuario atualizarUsuario(Usuario usuario) {
       if(!usuarioRepository.existsById(usuario.getUsuarioId())){
           throw new ResourceNotFoundException("Usuario com ID " + usuario.getUsuarioId()+ " não encontrato para ataulização");
       }
       return usuarioRepository.save(usuario);
    }

    public void removerUsaurio(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario com ID " + id + " não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private void verificarCustumerId(String customerId) {
        if(usuarioRepository.findByCustomerId(customerId).isPresent()) {
            throw new IllegalArgumentException("O custumerId já existe.");
        }
    }

}
