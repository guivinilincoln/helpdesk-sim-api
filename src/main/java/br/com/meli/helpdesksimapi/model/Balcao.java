package br.com.meli.helpdesksimapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Balcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balcaoId;

    @OneToOne
    @JoinColumn(name = "atendenteId", nullable = false)
    private Atendente atendente;

    @NotNull
    private String nomeBalcao;

}
