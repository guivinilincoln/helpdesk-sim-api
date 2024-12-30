package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.BalcaoDTO;
import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.service.BalcaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/balcoes")
@RequiredArgsConstructor
public class BalcaoController {

    private final BalcaoService balcaoService;

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<BalcaoDTO>> criarBalcao(@Valid @RequestBody BalcaoDTO balcaoDTO) {
        BalcaoDTO criado = balcaoService.criarBalcao(balcaoDTO);
        SuccessResponseDTO<BalcaoDTO> response = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Criado com sucesso!", criado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<BalcaoDTO>> buscarBalcaoPorId(@PathVariable Long id) {
        BalcaoDTO balcaoDTO = balcaoService.buscarBalcaoPorId(id);
        SuccessResponseDTO<BalcaoDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", balcaoDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<SuccessResponseDTO<PageInfoDTO<BalcaoDTO>>> listarBalcoes(Pageable pageable) {
        Page<BalcaoDTO> paginatedResult = balcaoService.listarBalcoes(pageable);
        PageInfoDTO<BalcaoDTO> pageInfo = new PageInfoDTO<>(
                paginatedResult.getContent(),
                paginatedResult.getTotalPages(),
                paginatedResult.getTotalElements()
        );
        SuccessResponseDTO<PageInfoDTO<BalcaoDTO>> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Balcoes retornados com sucesso!", pageInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<BalcaoDTO>> alterarBalcao(@PathVariable Long id, @Valid @RequestBody BalcaoDTO balcaoDTO) {
        balcaoDTO.setBalcaoId(id);
        BalcaoDTO atualizado = balcaoService.alterarBalcao(balcaoDTO);
        SuccessResponseDTO<BalcaoDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atualizado com sucesso!", atualizado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<BalcaoDTO>> deletarBalcao(@PathVariable Long id) {
        BalcaoDTO deletado = balcaoService.deletarBalcao(id);
        SuccessResponseDTO<BalcaoDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Deletado com sucesso!", deletado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
