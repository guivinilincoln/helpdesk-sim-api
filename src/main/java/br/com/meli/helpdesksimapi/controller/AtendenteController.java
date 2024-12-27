package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.AtendenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/atendentes")
@RequiredArgsConstructor
public class AtendenteController {

    private final AtendenteService atendenteService;

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<PageInfoDTO<AtendenteDTO>>> listarAtendentes(Pageable pageable) {
        Page<AtendenteDTO> paginatedResult = atendenteService.listarAtendentes(pageable);
        PageInfoDTO<AtendenteDTO> pageInfo = new PageInfoDTO<>(
                paginatedResult.getContent(),
                paginatedResult.getTotalPages(),
                paginatedResult.getTotalElements()
        );
        SuccessResponseDTO<PageInfoDTO<AtendenteDTO>> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atendentes retornados com sucesso!", pageInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<AtendenteDTO>> buscarPorId(@PathVariable Long id) {
        AtendenteDTO atendenteDTO = atendenteService.buscarAtendentePorId(id);
        SuccessResponseDTO<AtendenteDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", atendenteDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<AtendenteDTO>> criarAtendente(@Valid @RequestBody AtendenteDTO atendenteDTO) {
        AtendenteDTO criado = atendenteService.criarAtendente(atendenteDTO);
        SuccessResponseDTO<AtendenteDTO> response = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Criado com sucesso!", criado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<AtendenteDTO>> deletarAtendente(@PathVariable Long id) {
        AtendenteDTO deletado = atendenteService.deletarAtendente(id);
        SuccessResponseDTO<AtendenteDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atendente deletado com sucesso!", deletado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendenteDTO> alterarAtendente(@PathVariable Long id, @Valid @RequestBody AtendenteDTO atendenteDTO) {
        atendenteDTO.setAtendenteId(id);
        AtendenteDTO atualizado = atendenteService.alterarAtendente(atendenteDTO);
        return ResponseEntity.ok(atualizado);
    }

}
