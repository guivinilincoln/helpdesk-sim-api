package br.com.meli.helpdesksimapi.mapper;

import br.com.meli.helpdesksimapi.dto.BalcaoDTO;
import br.com.meli.helpdesksimapi.model.Balcao;

public class BalcaoMapper {

    public static BalcaoDTO toDTO(Balcao balcao) {
        if (balcao == null) return null;

        BalcaoDTO dto = new BalcaoDTO();
        dto.setBalcaoId(balcao.getBalcaoId());
        dto.setNomeBalcao(balcao.getNomeBalcao());
        dto.setAtendente(AtendenteMapper.toDTO(balcao.getAtendente()));

        return dto;
    }

    public static Balcao toEntity(BalcaoDTO dto) {
        if (dto == null) return null;

        Balcao balcao = new Balcao();
        balcao.setBalcaoId(dto.getBalcaoId());
        balcao.setNomeBalcao(dto.getNomeBalcao());
        balcao.setAtendente(AtendenteMapper.toEntity(dto.getAtendente()));

        return balcao;
    }
}
