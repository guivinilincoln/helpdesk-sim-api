package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalcaoService {

    @Autowired
    private BalcaoRepository balcaoRepository;

    public Balcao criarBalcao(Balcao balcao) {
        return balcaoRepository.save(balcao);
    }
    public List<Balcao> listarBalcao() {
        return balcaoRepository.findAll();
    }
    public Balcao buscarBalcaoPorId(Long id) {
        return balcaoRepository.findById(id).orElse(null);
    }
    public Balcao alterarBalcao(Balcao balcao) {
        return balcaoRepository.save(balcao);
    }
    public void deletarBalcao(Long id) {
        balcaoRepository.deleteById(id);
    }

    public List<Balcao> buscarBalcaoPorCustomerId(String customerId) {
        balcaoRepository.buscarPorUsuarioCustomerId(customerId);
        return balcaoRepository.findAll();
    }
}
