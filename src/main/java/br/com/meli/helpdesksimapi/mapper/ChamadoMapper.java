package br.com.meli.helpdesksimapi.mapper;

import br.com.meli.helpdesksimapi.dto.ChamadoDTO;
import br.com.meli.helpdesksimapi.model.Chamado;
import br.com.meli.helpdesksimapi.model.Status;

import java.util.Date;

public class ChamadoMapper {

    public static ChamadoDTO toDTO(Chamado chamado) {
        if (chamado == null) {
            return null;
        }
        ChamadoDTO dto = new ChamadoDTO();
        dto.setChamadoId(chamado.getChamadoId());
        dto.setUsuario(UsuarioMapper.toDTO(chamado.getUsuario()));
        dto.setBalcao(BalcaoMapper.toDTO(chamado.getBalcao()));
        dto.setStatus(chamado.getStatus());
        dto.setDataChamado(chamado.getDataChamado());
        dto.setDataResolucao(chamado.getDataResolucao());
        dto.setMotivoChamado(chamado.getMotivoChamado());
        dto.setProduto(chamado.getProduto());
        dto.setMaquininha(MaquininhaMapper.toDTO(chamado.getMaquininha()));
        return dto;
    }

    public static Chamado toEntity(ChamadoDTO chamadoDTO) {
        if (chamadoDTO == null) {
            return null;
        }
        Chamado chamado = new Chamado();
        chamado.setChamadoId(chamadoDTO.getChamadoId());
        chamado.setUsuario(UsuarioMapper.toEntity(chamadoDTO.getUsuario()));
        chamado.setBalcao(BalcaoMapper.toEntity(chamadoDTO.getBalcao()));
        chamado.setStatus(chamadoDTO.getStatus() != null ? chamadoDTO.getStatus() : Status.ABERTO);
        chamado.setDataChamado(chamadoDTO.getDataChamado() != null ? chamadoDTO.getDataChamado() : new Date());
        chamado.setDataResolucao(chamadoDTO.getDataResolucao());
        chamado.setMotivoChamado(chamadoDTO.getMotivoChamado());
        chamado.setProduto(chamadoDTO.getProduto());
        chamado.setMaquininha(MaquininhaMapper.toEntity(chamadoDTO.getMaquininha()));
        return chamado;
    }
}
