package br.com.meli.helpdesksimapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendenteId;

    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    public Atendente() {
    }


    public Long getAtendenteId() {
        return atendenteId;
    }

    public void setAtendenteId(Long atendenteId) {
        this.atendenteId = atendenteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Atendente{" +
                "atendenteId=" + atendenteId +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atendente)) return false;

        Atendente atendente = (Atendente) o;

        return atendenteId != null ? atendenteId.equals(atendente.atendenteId) : atendente.atendenteId == null;
    }

    @Override
    public int hashCode() {
        return atendenteId != null ? atendenteId.hashCode() : 0;
    }
}
