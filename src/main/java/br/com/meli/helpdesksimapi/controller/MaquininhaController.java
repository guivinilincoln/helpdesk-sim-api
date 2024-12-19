package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.ErroResponse;
import br.com.meli.helpdesksimapi.dto.SuccessResponse;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Maquininha;
import br.com.meli.helpdesksimapi.service.MaquininhaService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquininhas")
public class MaquininhaController {

    @Autowired
    private MaquininhaService maquininhaService;

    @PostMapping
    public ResponseEntity<?> salvarMaquininha(@Valid @RequestBody Maquininha maquininha) {
        try {
            Maquininha criar = maquininhaService.criarMaquininha(maquininha);
            SuccessResponse<Maquininha> response = new SuccessResponse<>(HttpStatus.CREATED.value(), "Criado com sucesso ! ", criar);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            ErroResponse errorResponse = new ErroResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<Maquininha> listarMaquininhas() {
        return maquininhaService.listarMaquininhas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> buscarMaquininha(@PathVariable Long id) {
        Maquininha maquininha = maquininhaService.buscarMaquininhaPorId(id);
       SuccessResponse<Maquininha> response= new SuccessResponse<>(HttpStatus.OK.value(), "Buscado com sucesso !", maquininha);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Maquininha> deletarMaquininha(@PathVariable Long id) {
        maquininhaService.deletarMaquininha(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarMaquininha(@PathVariable Long id, @Valid @RequestBody Maquininha maquininha) {
            maquininha.setDeviceId(id);
            Maquininha atualizada = maquininhaService.alterarMaquinha(maquininha);
            SuccessResponse<Maquininha> response = new SuccessResponse<>(HttpStatus.OK.value(), "Atualizado com sucesso!", atualizada);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
