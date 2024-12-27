package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.BalcaoDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.mapper.BalcaoMapper;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalcaoService {

    private final BalcaoRepository balcaoRepository;
    private final AtendenteRepository atendenteRepository;

    public BalcaoDTO criarBalcao(BalcaoDTO balcaoDTO) {
        Balcao balcao = BalcaoMapper.toEntity(balcaoDTO);

        if (balcao.getAtendente() != null) {
            Atendente atendente = atendenteRepository.findById(balcao.getAtendente().getAtendenteId())
                    .orElseGet(() -> atendenteRepository.save(balcao.getAtendente()));
            balcao.setAtendente(atendente);
        }

        Balcao salvo = balcaoRepository.save(balcao);
        return BalcaoMapper.toDTO(salvo);
    }

    public Page<BalcaoDTO> listarBalcoes(Pageable pageable) {
        return balcaoRepository.findAll(pageable)
                .map(BalcaoMapper::toDTO);
    }

    public BalcaoDTO buscarBalcaoPorId(Long id) {
        Balcao balcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Balcão com o ID " + id + " não encontrado"));
        return BalcaoMapper.toDTO(balcao);
    }

    public BalcaoDTO alterarBalcao(BalcaoDTO balcaoDTO) {
        if (!balcaoRepository.existsById(balcaoDTO.getBalcaoId())) {
            throw new ResourceNotFoundException("Balcão com o ID " + balcaoDTO.getBalcaoId() + " não encontrado para alterar");
        }
        Balcao balcao = BalcaoMapper.toEntity(balcaoDTO);
        Balcao salvo = balcaoRepository.save(balcao);
        return BalcaoMapper.toDTO(salvo);
    }

    public BalcaoDTO deletarBalcao(Long id) {
        Balcao balcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Balcão com o ID " + id + " não encontrado"));
        balcaoRepository.deleteById(id);
        return BalcaoMapper.toDTO(balcao);
    }
}
