package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.service.AtendenteService;
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

    @PostMapping
    public ResponseEntity<Atendente> criarAtendente(@RequestBody Atendente atendente) {
        Atendente criado = atendenteService.criarAtendente(atendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Atendente> deletarAtendente(@PathVariable Long id) {
        boolean foiRemovido = atendenteService.deletarAtendente(id);
        if (!foiRemovido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
