package br.com.meli.helpdesksimapi.mapper;

import br.com.meli.helpdesksimapi.dto.MaquininhaDTO;
import br.com.meli.helpdesksimapi.model.Maquininha;

public class MaquininhaMapper {

    public static MaquininhaDTO toDTO(Maquininha maquininha) {
        if (maquininha == null) {
            return null;
        }
        MaquininhaDTO dto = new MaquininhaDTO();
        dto.setDeviceId(maquininha.getDeviceId());
        dto.setSerialNumber(maquininha.getSerialNumber());
        return dto;
    }

    public static Maquininha toEntity(MaquininhaDTO maquininhaDTO) {
        if (maquininhaDTO == null) {
            return null;
        }
        Maquininha maquininha = new Maquininha();
        maquininha.setDeviceId(maquininhaDTO.getDeviceId());
        maquininha.setSerialNumber(maquininhaDTO.getSerialNumber());
        return maquininha;
    }
}
