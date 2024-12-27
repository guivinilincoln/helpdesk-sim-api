package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.PageInfoDTO;
import br.com.meli.helpdesksimapi.dto.SuccessResponseDTO;
import br.com.meli.helpdesksimapi.dto.MaquininhaDTO;
import br.com.meli.helpdesksimapi.service.MaquininhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/maquininhas")
@RequiredArgsConstructor
public class MaquininhaController {

    private final MaquininhaService maquininhaService;

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> criarMaquininha(@Valid @RequestBody MaquininhaDTO maquininhaDTO) {
        MaquininhaDTO criada = maquininhaService.criarMaquininha(maquininhaDTO);
        SuccessResponseDTO<MaquininhaDTO> response = new SuccessResponseDTO<>(HttpStatus.CREATED.value(), "Criada com sucesso!", criada);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> buscarMaquininhaPorId(@PathVariable Long id) {
        MaquininhaDTO maquininhaDTO = maquininhaService.buscarMaquininhaPorId(id);
        SuccessResponseDTO<MaquininhaDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", maquininhaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<PageInfoDTO<MaquininhaDTO>>> listarMaquininhas(Pageable pageable) {
        Page<MaquininhaDTO> paginatedResult = maquininhaService.listarMaquininhas(pageable);
        PageInfoDTO<MaquininhaDTO> pageInfo = new PageInfoDTO<>(
                paginatedResult.getContent(),
                paginatedResult.getTotalPages(),
                paginatedResult.getTotalElements()
        );
        SuccessResponseDTO<PageInfoDTO<MaquininhaDTO>> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Maquininhas retornadas com sucesso!", pageInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> alterarMaquininha(@PathVariable Long id, @Valid @RequestBody MaquininhaDTO maquininhaDTO) {
        maquininhaDTO.setDeviceId(id);
        MaquininhaDTO atualizada = maquininhaService.alterarMaquininha(maquininhaDTO);
        SuccessResponseDTO<MaquininhaDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Atualizada com sucesso!", atualizada);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO<MaquininhaDTO>> deletarMaquininha(@PathVariable Long id) {
        MaquininhaDTO deletada = maquininhaService.deletarMaquininha(id);
        SuccessResponseDTO<MaquininhaDTO> response = new SuccessResponseDTO<>(HttpStatus.OK.value(), "Deletada com sucesso!", deletada);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
