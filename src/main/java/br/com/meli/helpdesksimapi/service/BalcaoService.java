package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalcaoService {

    @Autowired
    private BalcaoRepository balcaoRepository;

    public Balcao criarBalcao(Balcao balcao) {
        return balcaoRepository.save(balcao);
    }
    public List<Balcao> listarBalcoes() {
        return balcaoRepository.findAll();
    }
    public Balcao buscarBalcaoPorId(Long id) {
        return balcaoRepository.findById(id).orElse(null);
    }
    public Balcao alterarBalcao(Balcao balcao) {
        return balcaoRepository.save(balcao);
    }
    public boolean deletarBalcao(Long id) {
        Optional<Balcao> balcao = balcaoRepository.findById(id);
        if (balcao.isPresent()) {
            balcaoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Balcao> buscarBalcaoPorAtendenteId(Long atentendeId) {
        balcaoRepository.findByAtendenteAtendenteId(atentendeId);
        return balcaoRepository.findAll();
    }


}
