package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;

    public Atendente criarAtendente(Atendente atendente) {
        return atendenteRepository.save(atendente);
    }

    public Atendente buscarAtendentePorId(Long id) {
        return atendenteRepository.findById(id).orElse(null);
    }

    public List<Atendente> listarAtendentes() {
        return atendenteRepository.findAll();
    }

    public Atendente alterarAtendente(Atendente atendente) {
        return atendenteRepository.save(atendente);
    }

    public void deletarAtendente(Atendente atendente) {
        atendenteRepository.delete(atendente);
    }

}
