package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.ErroResponse;
import br.com.meli.helpdesksimapi.dto.SuccessResponse;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Usuario;
import br.com.meli.helpdesksimapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario salvo = usuarioService.salvar(usuario);
            SuccessResponse<Usuario> successResponse = new SuccessResponse<>(HttpStatus.CREATED.value(), "Usuario criado com sucesso !", salvo);
            return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ErroResponse errorResponse = new ErroResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
       SuccessResponse<Usuario> response = new SuccessResponse<>(HttpStatus.OK.value(), "Usuario encontrado !", usuario);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        usuarioService.removerUsaurio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setUsuarioId(id);
        Usuario atualizado = usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.OK.value()).body(atualizado);
    }

}
