package br.com.meli.helpdesksimapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtendenteDTO {
    private Long atendenteId;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser nulo ou vazioooo")
    @Pattern(regexp = "^[\\p{L}\\p{M}' \\.\\-]+$", message = "O nome deve conter apenas letras e espaços")
    private String nome;
}
