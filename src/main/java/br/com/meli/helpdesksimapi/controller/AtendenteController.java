package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponse;
import br.com.meli.helpdesksimapi.service.AtendenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/atendentes")
@RequiredArgsConstructor
public class AtendenteController {

    private final AtendenteService atendenteService;

    @GetMapping
    public ResponseEntity<Page<AtendenteDTO>> listarAtendentes(Pageable pageable) {
        Page<AtendenteDTO> paginatedResult = atendenteService.listarAtendentes(pageable);
        return ResponseEntity.ok(paginatedResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<AtendenteDTO>> buscarPorId(@PathVariable Long id) {
        AtendenteDTO atendenteDTO = atendenteService.buscarAtendentePorId(id);
        SuccessResponse<AtendenteDTO> response = new SuccessResponse<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", atendenteDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<AtendenteDTO>> criarAtendente(@Valid @RequestBody AtendenteDTO atendenteDTO) {
        AtendenteDTO criado = atendenteService.criarAtendente(atendenteDTO);
        SuccessResponse<AtendenteDTO> response = new SuccessResponse<>(HttpStatus.CREATED.value(), "Criado com sucesso!", criado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendente(@PathVariable Long id) {
        atendenteService.deletarAtendente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendenteDTO> alterarAtendente(@PathVariable Long id, @Valid @RequestBody AtendenteDTO atendenteDTO) {
        atendenteDTO.setAtendenteId(id);
        AtendenteDTO atualizado = atendenteService.alterarAtendente(atendenteDTO);
        return ResponseEntity.ok(atualizado);
    }

}
