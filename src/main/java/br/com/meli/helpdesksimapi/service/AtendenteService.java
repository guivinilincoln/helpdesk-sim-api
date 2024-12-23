package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.dto.PagedResponseDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.exception.TestCustomValidationException;
import br.com.meli.helpdesksimapi.mapper.AtendenteMapper;
import br.com.meli.helpdesksimapi.model.Atendente;
import br.com.meli.helpdesksimapi.repository.AtendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AtendenteService {

    private final AtendenteRepository atendenteRepository;

    public AtendenteDTO criarAtendente(AtendenteDTO atendenteDTO) {
       // validacaoCampos(atendenteDTO);
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

    public void deletarAtendente(Long id) {
        if (!atendenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Atendente com ID " + id + " não encontrado");
        }
        atendenteRepository.deleteById(id);
    }

    private static void validacaoCampos(AtendenteDTO atendenteDTO) {
        Map<String, String> fieldErrors = new HashMap<>();
        if (atendenteDTO.getNome() == null || atendenteDTO.getNome().isEmpty()) {
            fieldErrors.put("nome", "O nome não pode ser nulo ou vazio");
        }

        if (!fieldErrors.isEmpty()) {
            throw new TestCustomValidationException(fieldErrors);
        }
    }
}
