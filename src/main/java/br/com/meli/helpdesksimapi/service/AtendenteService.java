package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.mapper.AtendenteMapper;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AtendenteService {

    private final AtendenteRepository atendenteRepository;

    public AtendenteDTO criarAtendente(AtendenteDTO atendenteDTO) {
        Atendente atendente = AtendenteMapper.toEntity(atendenteDTO);
        Atendente salvo = atendenteRepository.save(atendente);
        return AtendenteMapper.toDTO(salvo);
    }


    public AtendenteDTO buscarAtendentePorId(Long id) {
        Atendente atendente = atendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendente com ID " + id + " não encontrado"));
        return AtendenteMapper.toDTO(atendente);
    }

    public Page<AtendenteDTO> listarAtendentes(Pageable pageable) {
        return atendenteRepository.findAll(pageable)
                .map(AtendenteMapper::toDTO);
    }

    public AtendenteDTO alterarAtendente(AtendenteDTO atendenteDTO) {
        if (!atendenteRepository.existsById(atendenteDTO.getAtendenteId())) {
            throw new ResourceNotFoundException("Atendente com ID " + atendenteDTO.getAtendenteId() + " não encontrado para atualização");
        }
        Atendente atendente = AtendenteMapper.toEntity(atendenteDTO);
        Atendente atualizado = atendenteRepository.save(atendente);
        return AtendenteMapper.toDTO(atualizado);
    }

    public AtendenteDTO deletarAtendente(Long id) {
        Atendente atendente = atendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendente com ID " + id + " não encontrado"));
        atendenteRepository.deleteById(id);
        return AtendenteMapper.toDTO(atendente);
    }

}
