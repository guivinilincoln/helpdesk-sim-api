package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.Maquininha;
import br.com.meli.helpdesksimapi.repository.MaquininhaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquininhaService {

    @Autowired
    private MaquininhaRepository maquininhaRepository;

    @Transactional
    public Maquininha criarMaquininha(Maquininha maquininha){
        verificarSerialNumber(maquininha.getSerialNumber());
        return maquininhaRepository.save(maquininha);
    }

    public List<Maquininha> listarMaquininhas() {
        return maquininhaRepository.findAll();
    }

    public Maquininha buscarMaquininhaPorId(Long id) {
        return maquininhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maquininha com ID " + id + " não encontrado"));
    }

    public Maquininha alterarMaquinha(Maquininha maquininha) {
        if(!maquininhaRepository.existsById(maquininha.getDeviceId())){
            throw new ResourceNotFoundException("Maquininha com ID " + maquininha.getDeviceId()+ " não encontrado para atualização");
        }
        return maquininhaRepository.save(maquininha);
    }

    public void deletarMaquininha(Long id) {
        if (!maquininhaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maquininha com ID " + id + " não encontrado");
        }
        maquininhaRepository.deleteById(id);
    }

    private void verificarSerialNumber(String serialNumber) {
        if (maquininhaRepository.findBySerialNumber(serialNumber).isPresent()) {
            throw new IllegalArgumentException("O número serial já existe.");
        }
    }
}
