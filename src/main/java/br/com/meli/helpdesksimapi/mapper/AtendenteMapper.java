package br.com.meli.helpdesksimapi.mapper;

import br.com.meli.helpdesksimapi.dto.AtendenteDTO;
import br.com.meli.helpdesksimapi.model.Atendente;

public class AtendenteMapper {

    public static AtendenteDTO toDTO(Atendente atendente) {
        if (atendente == null) return null;

        AtendenteDTO dto = new AtendenteDTO();
        dto.setAtendenteId(atendente.getAtendenteId());
        dto.setNome(atendente.getNome());

        return dto;
    }

    public static Atendente toEntity(AtendenteDTO dto) {
        if (dto == null) return null;

        Atendente atendente = new Atendente();
        atendente.setAtendenteId(dto.getAtendenteId());
        atendente.setNome(dto.getNome());

        return atendente;
    }
}
