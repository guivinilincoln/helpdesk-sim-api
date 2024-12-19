package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.SuccessResponse;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.service.BalcaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balcoes")
public class BalcaoController {

    @Autowired
    private BalcaoService balcaoService;

    @GetMapping
    public List<Balcao> listarBalcoes() {
        return balcaoService.listarBalcoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> buscarPorId(@PathVariable Long id) {
        Balcao balcao = balcaoService.buscarBalcaoPorId(id);
        SuccessResponse<Balcao> response = new SuccessResponse<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", balcao);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> salvarBalcao(@Valid @RequestBody Balcao balcao) {
        Balcao criar = balcaoService.criarBalcao(balcao);
        SuccessResponse<Balcao> response = new SuccessResponse<>(HttpStatus.CREATED.value(), "Criado com sucesso!", criar);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Balcao> deletarBalcao(@PathVariable Long id) {
       balcaoService.deletarBalcao(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Balcao> alterarBalcao(@PathVariable Long id, @RequestBody Balcao balcao) {
        balcao.setBalcaoId(id);
        Balcao atualizar = balcaoService.alterarBalcao(balcao);
        return ResponseEntity.status(HttpStatus.OK.value()).body(atualizar);
    }


}
