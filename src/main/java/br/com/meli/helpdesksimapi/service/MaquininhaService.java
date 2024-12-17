package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Maquininha;
import br.com.meli.helpdesksimapi.repository.MaquininhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquininhaService {

    @Autowired
    private MaquininhaRepository maquininhaRepository;

    public Maquininha criarMaquininha(Maquininha maquininha) {
        return maquininhaRepository.save(maquininha);
    }

    public List<Maquininha> listarMaquininhas() {
        return maquininhaRepository.findAll();
    }

    public Maquininha buscarMaquininhaPorId(Long id) {
        return maquininhaRepository.findById(id).orElse(null);
    }

    public Maquininha alterarMaquinha(Maquininha maquininha) {
        return maquininhaRepository.save(maquininha);
    }

    public boolean deletarMaquininha(Long id) {
        Optional<Maquininha> maquininha = maquininhaRepository.findById(id);
        if( maquininha.isPresent() ) {
            maquininhaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
