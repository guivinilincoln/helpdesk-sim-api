package br.com.meli.helpdesksimapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaquininhaDTO {
    private Long deviceId;

    @NotNull(message = "O número serial não deve ser nulo")
    @Size(max = 10, message = "O número serial deve ter no máximo 10 caracteres")
    private String serialNumber;


}
