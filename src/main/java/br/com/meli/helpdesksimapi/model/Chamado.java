package br.com.meli.helpdesksimapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
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

    @NotNull
    private Date dataChamado;

    private Date dataResolucao;

    @NotNull
    private String motivoChamado;

    @NotNull
    private String produto;

    @ManyToOne
    @JoinColumn(name = "deviceId", nullable = false)
    private Maquininha maquininha;
}
