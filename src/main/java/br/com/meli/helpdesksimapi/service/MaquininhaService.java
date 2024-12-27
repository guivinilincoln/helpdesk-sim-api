package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.MaquininhaDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.mapper.MaquininhaMapper;
import br.com.meli.helpdesksimapi.model.Maquininha;
import br.com.meli.helpdesksimapi.repository.MaquininhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaquininhaService {

    private final MaquininhaRepository maquininhaRepository;

    public MaquininhaDTO criarMaquininha(MaquininhaDTO maquininhaDTO) {
        Maquininha maquininha = MaquininhaMapper.toEntity(maquininhaDTO);
        Maquininha criada = maquininhaRepository.save(maquininha);
        return MaquininhaMapper.toDTO(criada);
    }

    public Page<MaquininhaDTO> listarMaquininhas(Pageable pageable) {
        return maquininhaRepository.findAll(pageable)
                .map(MaquininhaMapper::toDTO);
    }

    public MaquininhaDTO buscarMaquininhaPorId(Long id) {
        Maquininha maquininha = maquininhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maquininha com o ID " + id + " não encontrada"));
        return MaquininhaMapper.toDTO(maquininha);
    }

    public MaquininhaDTO alterarMaquininha(MaquininhaDTO maquininhaDTO) {
        if (!maquininhaRepository.existsById(maquininhaDTO.getDeviceId())) {
            throw new ResourceNotFoundException("Maquininha com o ID " + maquininhaDTO.getDeviceId() + " não encontrada para alterar");
        }
        Maquininha maquininha = MaquininhaMapper.toEntity(maquininhaDTO);
        Maquininha atualizada = maquininhaRepository.save(maquininha);
        return MaquininhaMapper.toDTO(atualizada);
    }

    public MaquininhaDTO deletarMaquininha(Long id) {
        Maquininha maquininha = maquininhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maquininha com o ID " + id + " não encontrada"));
        maquininhaRepository.deleteById(id);
        return MaquininhaMapper.toDTO(maquininha);
    }
}
