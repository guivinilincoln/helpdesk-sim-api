package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.model.Status;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BalcaoRepository balcaoRepository;


    public Chamado criarChamado(Chamado chamado) {
        if (chamado.getDataChamado() == null) {
            chamado.setDataChamado(new Date());
        }
        Balcao balcaoDisponivel = balcaoRepository.findAll().stream()
                .filter(balcao -> chamadoRepository.countByBalcaoAndStatusNot(balcao, Status.CONCLUIDO) < 5)
                .findFirst()
                .orElse(null);
        if (balcaoDisponivel != null) {
            chamado.setBalcao(balcaoDisponivel);
            chamado.setStatus(Status.ABERTO);
            return chamadoRepository.save(chamado);
        } else {
            // Coloque o chamado em espera
            chamado.setStatus(Status.EM_ESPERA);
            return chamadoRepository.save(chamado);
        }
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

    public int countChamadosAtivos(Balcao balcao) {
        return chamadoRepository.countByBalcaoAndStatusNot(balcao, Status.CONCLUIDO);
    }
}
