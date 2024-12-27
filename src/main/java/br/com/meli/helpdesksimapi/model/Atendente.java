package br.com.meli.helpdesksimapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atendente")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendenteId;

    private String nome;

}
