package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalcaoService {

    @Autowired
    private BalcaoRepository balcaoRepository;

    @Autowired
    private AtendenteRepository atendenteRepository;

    public Balcao criarBalcao(Balcao balcao) {
        Atendente atendente = atendenteRepository.findById(balcao.getAtendente().getAtendenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Atendente não encontrado"));
        balcao.setAtendente(atendente);
        return balcaoRepository.save(balcao);
    }
    public List<Balcao> listarBalcoes() {
        return balcaoRepository.findAll();
    }
    public Balcao buscarBalcaoPorId(Long id) {
        return balcaoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Balção com o ID " + id + " não encontrado"));
    }
    public Balcao alterarBalcao(Balcao balcao) {
        if(!balcaoRepository.existsById(balcao.getBalcaoId())){
            throw new ResourceNotFoundException("Balção com o ID " + balcao.getBalcaoId() + " não encontrado para o alterar");
        }
        return balcaoRepository.save(balcao);
    }
    public void deletarBalcao(Long id) {
       if (!balcaoRepository.existsById(id)) {
           throw new ResourceNotFoundException("Balcão com ID " + id + " não encontrado");
       }
       balcaoRepository.deleteById(id);
    }
}
