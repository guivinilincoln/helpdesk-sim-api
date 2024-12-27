package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.dto.UsuarioDTO;
import br.com.meli.helpdesksimapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<UsuarioDTO>> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO criado = usuarioService.criarUsuario(usuarioDTO);
        SuccessResponseDTO<UsuarioDTO> response = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Criado com sucesso!", criado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<UsuarioDTO>> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        SuccessResponseDTO<UsuarioDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", usuarioDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<PageInfoDTO<UsuarioDTO>>> listarUsuarios(Pageable pageable) {
        Page<UsuarioDTO> paginatedResult = usuarioService.listarUsuarios(pageable);
        PageInfoDTO<UsuarioDTO> pageInfo = new PageInfoDTO<>(
                paginatedResult.getContent(),
                paginatedResult.getTotalPages(),
                paginatedResult.getTotalElements()
        );
        SuccessResponseDTO<PageInfoDTO<UsuarioDTO>> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Usuários retornados com sucesso!", pageInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<UsuarioDTO>> alterarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setUsuarioId(id);
        UsuarioDTO atualizado = usuarioService.alterarUsuario(usuarioDTO);
        SuccessResponseDTO<UsuarioDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atualizado com sucesso!", atualizado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<UsuarioDTO>> deletarUsuario(@PathVariable Long id) {
        UsuarioDTO deletado = usuarioService.deletarUsuario(id);
        SuccessResponseDTO<UsuarioDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Deletado com sucesso!", deletado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
