package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado criarChamado(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    public List<Chamado> buscarTodosChamados() {
        return chamadoRepository.findAll();
    }

    public Chamado buscarChamadoPorId(Long id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElse(null);
    }

    public Page<Chamado> buscarChamadosPaginados(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return chamadoRepository.findAll(pageRequest);
    }

    public Page<Chamado> buscarChamadoPorCustomerId(String customerId, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return chamadoRepository.findByUsuarioCustomerId(customerId, pageRequest);
    }
}
