package br.com.meli.helpdesksimapi.dto;

import br.com.meli.helpdesksimapi.model.Maquininha;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long usuarioId;

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @NotNull(message = "O ID do cliente não deve ser nulo")
    private String customerId;

    @NotNull(message = "O nome do usuário não deve ser nulo")
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Pattern(regexp = "^[\\p{L}\\p{M}' \\.\\-]+$", message = "O nome deve conter apenas letras e espaços")
    private String nomeUsuario;



}
