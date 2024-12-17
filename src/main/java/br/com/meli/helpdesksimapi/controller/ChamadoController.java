package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<Chamado> criarChamado(@Valid @RequestBody Chamado chamado) {
        Chamado criado = chamadoService.criarChamado(chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Chamado>> buscarTodosChamados() {
        List<Chamado> chamados = chamadoService.buscarTodosChamados();
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscarChamadoPorId(@PathVariable Long id) {
        Chamado chamado = chamadoService.buscarChamadoPorId(id);
        if (chamado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(chamado);
    }

    @GetMapping("/paginados")
    public ResponseEntity<Page<Chamado>> buscarChamadosPaginados(@RequestParam int page, @RequestParam int size) {
        Page<Chamado> chamados = chamadoService.buscarChamadosPaginados(page, size);
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/usuario/{customerId}")
    public ResponseEntity<Page<Chamado>> buscarChamadoPorCustomerId(@PathVariable String customerId,
                                                                    @RequestParam int page,
                                                                    @RequestParam int size) {
        Page<Chamado> chamados = chamadoService.buscarChamadoPorCustomerId(customerId, page, size);
        return ResponseEntity.ok(chamados);
    }
}
