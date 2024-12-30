package br.com.meli.helpdesksimapi.service;

import br.com.meli.helpdesksimapi.dto.ChamadoDTO;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import br.com.meli.helpdesksimapi.mapper.ChamadoMapper;
import br.com.meli.helpdesksimapi.mapper.MaquininhaMapper;
import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.model.Maquininha;
import br.com.meli.helpdesksimapi.model.Status;
import br.com.meli.helpdesksimapi.repository.BalcaoRepository;
import br.com.meli.helpdesksimapi.repository.ChamadoRepository;
import br.com.meli.helpdesksimapi.repository.MaquininhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final BalcaoRepository balcaoRepository;
    private final MaquininhaRepository maquininhaRepository;

    public ChamadoDTO criarChamado(ChamadoDTO chamadoDTO) {
        Chamado chamado = ChamadoMapper.toEntity(chamadoDTO);

        Maquininha maquininha = verificarMaquininha(chamado);
        verificarChamadosExistentes(chamado, maquininha);
        chamado.setMaquininha(maquininha);

        Balcao balcaoDisponivel = encontrarBalcaoDisponivel();
        if (balcaoDisponivel != null) {
            chamado.setBalcao(balcaoDisponivel);
        }

        Chamado salvo = chamadoRepository.save(chamado);
        return ChamadoMapper.toDTO(salvo);
    }

    public Page<ChamadoDTO> listarChamados(Pageable pageable) {
        return chamadoRepository.findAll(pageable)
                .map(ChamadoMapper::toDTO);
    }

    public ChamadoDTO buscarChamadoPorId(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chamado com o ID " + id + " não encontrado"));
        return ChamadoMapper.toDTO(chamado);
    }

    public ChamadoDTO alterarChamado(ChamadoDTO chamadoDTO) {
        if (!chamadoRepository.existsById(chamadoDTO.getChamadoId())) {
            throw new ResourceNotFoundException("Chamado com o ID " + chamadoDTO.getChamadoId() + " não encontrado para alterar");
        }
        Chamado chamado = ChamadoMapper.toEntity(chamadoDTO);
        Chamado salvo = chamadoRepository.save(chamado);
        return ChamadoMapper.toDTO(salvo);
    }

    public ChamadoDTO deletarChamado(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chamado com o ID " + id + " não encontrado"));
        chamadoRepository.deleteById(id);
        return ChamadoMapper.toDTO(chamado);
    }

    private Maquininha verificarMaquininha(Chamado chamado) {
        Maquininha maquininha = maquininhaRepository.findById(chamado.getMaquininha().getDeviceId())
                .orElseThrow(() -> new IllegalArgumentException("A Maquininha especificada não existe."));
        return maquininha;
    }

    private void verificarChamadosExistentes(Chamado chamado, Maquininha maquininha) {
        List<Chamado> chamadosParaUsuarioAtual = chamadoRepository.findByUsuarioAndMaquininhaAndStatusNot(
                chamado.getUsuario(), maquininha, Status.CONCLUIDO);

        if (!chamadosParaUsuarioAtual.isEmpty()) {
            throw new IllegalArgumentException("Já existe um chamado aberto para este usuário e número de série.");
        }

        List<Chamado> chamadosParaSerialNumber = chamadoRepository.findByMaquininhaSerialNumberAndStatusNot(
                maquininha.getSerialNumber(), Status.CONCLUIDO);

        for (Chamado c : chamadosParaSerialNumber) {
            if (!c.getUsuario().equals(chamado.getUsuario())) {
                throw new IllegalArgumentException("Outro usuário já possui um chamado em atendimento para este número serial.");
            }
        }
    }

    private Balcao encontrarBalcaoDisponivel() {
        return balcaoRepository.findAll().stream()
                .filter(balcao -> chamadoRepository.countByBalcaoAndStatusNot(balcao, Status.CONCLUIDO) < 5)
                .findFirst()
                .orElse(null);
    }
}
