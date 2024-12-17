package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.model.Maquininha;
import br.com.meli.helpdesksimapi.service.MaquininhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquininhas")
public class MaquininhaController {

    @Autowired
    private MaquininhaService MaquininhaService;
    @Autowired
    private MaquininhaService maquininhaService;

    @PostMapping
    public Maquininha salvarMaquininha(@RequestBody Maquininha maquininha) {
        return maquininhaService.criarMaquininha(maquininha);
    }

    @GetMapping
    public List<Maquininha> listarMaquininhas() {
        return maquininhaService.listarMaquininhas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquininha> buscarMaquininha(@PathVariable Long id) {
        Maquininha maquininha = maquininhaService.buscarMaquininhaPorId(id);
        if (maquininha == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(maquininha);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Maquininha> deletarMaquininha(@PathVariable Long id) {
        boolean foiRemovuido = maquininhaService.deletarMaquininha(id);
        if (!foiRemovuido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
