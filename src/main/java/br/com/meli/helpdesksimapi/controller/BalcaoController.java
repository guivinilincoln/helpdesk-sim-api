package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.service.BalcaoService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Balcao> buscarPorId(@PathVariable Long id) {
        Balcao balcao = balcaoService.buscarBalcaoPorId(id);
        if (balcao == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(balcao);
        }
    }

    @GetMapping
    public List<Balcao> listarBalcoes() {
        return balcaoService.listarBalcoes();
    }

    @PostMapping
    public Balcao salvarBalcao(@RequestBody Balcao balcao) {
        return balcaoService.criarBalcao(balcao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Balcao> deletarBalcao(@PathVariable Long id) {
        boolean foiRemovido = balcaoService.deletarBalcao(id);
        if (!foiRemovido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
