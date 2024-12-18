package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.model.Status;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChamadoProcessor {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BalcaoRepository balcaoRepository;


    @Scheduled(fixedRate = 120000) // A cada 2 minutos
    public void moveAbertosParaEmAtendimento() {
        List<Chamado> chamadosAbertos = chamadoRepository.findByStatusOrderByDataChamado(Status.ABERTO);
        chamadosAbertos.forEach(chamado -> {
            chamado.setStatus(Status.EM_ATENDIMENTO);
            chamadoRepository.save(chamado);
            System.out.println("Mudou para EM_ATENDIMENTO: ID = " + chamado.getChamadoId());
        });
    }

    @Scheduled(fixedRate = 240000) // A cada 4 minutos
    public void moveEmAtendimentoParaConcluido() {
        List<Chamado> chamadosEmAtendimento = chamadoRepository.findByStatusOrderByDataChamado(Status.EM_ATENDIMENTO);
        chamadosEmAtendimento.forEach(chamado -> {
            chamado.setStatus(Status.CONCLUIDO);
            chamadoRepository.save(chamado);
            System.out.println("Mudou para CONCLUIDO: ID = " + chamado.getChamadoId());
        });
    }

    @Scheduled(fixedRate = 180000) // 3 minutos
    public void gerenciarFilaDeEspera() {
        chamadoRepository.findByStatus(Status.EM_ESPERA).forEach(chamado -> {
            Balcao balcaoDisponivel = balcaoRepository.findAll().stream()
                    .filter(balcao -> chamadoRepository.countByBalcaoAndStatusNot(balcao, Status.CONCLUIDO) < 5)
                    .findFirst()
                    .orElse(null);

            if (balcaoDisponivel != null) {
                chamado.setStatus(Status.ABERTO);
                chamado.setBalcao(balcaoDisponivel);
                chamadoRepository.save(chamado);
            }
        });
    }
}
