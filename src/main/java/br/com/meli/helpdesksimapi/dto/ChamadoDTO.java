package br.com.meli.helpdesksimapi.dto;

import br.com.meli.helpdesksimapi.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDTO {
    private Long chamadoId;

    @NotNull(message = "O usuário não deve ser nulo")
    private UsuarioDTO usuario;

    @NotNull(message = "O balcão não deve ser nulo")
    private BalcaoDTO balcao;

    @NotNull(message = "O status não deve ser nulo")
    private Status status = Status.ABERTO;

    @NotNull(message = "A data do chamado não deve ser nula")
    private Date dataChamado = new Date();

    private Date dataResolucao;

    @NotNull(message = "O motivo do chamado não deve ser nulo")
    private String motivoChamado;

    @NotNull(message = "O produto não deve ser nulo")
    private String produto;

    @NotNull(message = "A maquininha não deve ser nula")
    private MaquininhaDTO maquininha;
}
