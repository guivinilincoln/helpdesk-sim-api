package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.SuccessResponse;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.service.AtendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendentes")
public class AtendenteController {
    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> listarAtendentes() {
        return atendenteService.listarAtendentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> buscarPorId(@PathVariable Long id) {
        Atendente atendente = atendenteService.buscarAtendentePorId(id);
        SuccessResponse<Atendente> response = new SuccessResponse<>(HttpStatus.OK.value(), "Valores retornados com sucesso!", atendente);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> criarAtendente(@Valid @RequestBody Atendente atendente) {
        Atendente criado = atendenteService.criarAtendente(atendente);
        SuccessResponse<Atendente> response = new SuccessResponse<>(HttpStatus.CREATED.value(), "Criado com sucesso!", criado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendente(@PathVariable Long id) {
        atendenteService.deletarAtendente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> alterarAtendente(@PathVariable Long id, @RequestBody Atendente atendente) {
        atendente.setAtendenteId(id);
        Atendente atualizado = atendenteService.alterarAtendente(atendente);
        return ResponseEntity.status(HttpStatus.OK.value()).body(atualizado);
    }

}
