package br.com.meli.helpdesksimapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chamado")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chamadoId;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "balcaoId", nullable = false)
    private Balcao balcao;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date dataChamado;

    private Date dataResolucao;

    private String motivoChamado;

    private String produto;

    @ManyToOne
    @JoinColumn(name = "deviceId", nullable = false)
    private Maquininha maquininha;

}
