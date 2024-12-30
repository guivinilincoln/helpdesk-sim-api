package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.ChamadoDTO;
import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/v1/api/chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<ChamadoDTO>> criarChamado(@Valid @RequestBody ChamadoDTO chamadoDTO) throws AccessDeniedException {
        ChamadoDTO criado = chamadoService.criarChamado(chamadoDTO);
        SuccessResponseDTO<ChamadoDTO> response = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Chamado criado com sucesso!", criado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<PageInfoDTO<ChamadoDTO>>> listarChamados(Pageable pageable) {
        Page<ChamadoDTO> paginatedResult = chamadoService.listarChamados(pageable);
        PageInfoDTO<ChamadoDTO> pageInfo = new PageInfoDTO<>(
                paginatedResult.getContent(),
                paginatedResult.getTotalPages(),
                paginatedResult.getTotalElements()
        );
        SuccessResponseDTO<PageInfoDTO<ChamadoDTO>> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamados retornados com sucesso!", pageInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<ChamadoDTO>> buscarChamadoPorId(@PathVariable Long id) {
        ChamadoDTO chamado = chamadoService.buscarChamadoPorId(id);
        SuccessResponseDTO<ChamadoDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamado retornado com sucesso!", chamado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<ChamadoDTO>> alterarChamado(@PathVariable Long id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        chamadoDTO.setChamadoId(id);
        ChamadoDTO atualizado = chamadoService.alterarChamado(chamadoDTO);
        SuccessResponseDTO<ChamadoDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamado atualizado com sucesso!", atualizado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<ChamadoDTO>> deletarChamado(@PathVariable Long id) {
        ChamadoDTO deletado = chamadoService.deletarChamado(id);
        SuccessResponseDTO<ChamadoDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Chamado deletado com sucesso!", deletado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
