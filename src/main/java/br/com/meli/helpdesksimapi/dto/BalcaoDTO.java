package br.com.meli.helpdesksimapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalcaoDTO {
    private Long balcaoId;

    @NotNull(message = "O nome do balcão não deve ser nulo")
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    private String nomeBalcao;
    private AtendenteDTO atendente;
}
