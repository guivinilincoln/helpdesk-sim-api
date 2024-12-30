package br.com.meli.helpdesksimapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "balcao")
public class Balcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balcaoId;

    private String nomeBalcao;

    @ManyToOne
    @JoinColumn(name = "atendente_id", nullable = false)
    private Atendente atendente;
}
