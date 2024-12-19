package br.com.meli.helpdesksimapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Balcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balcaoId;

    @NotNull(message = "O nome do balcão não deve ser nulo")
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    private String nomeBalcao;

    @ManyToOne
    @JoinColumn(name = "atendente_id", nullable = false)
    private Atendente atendente;

    public Balcao() { }

    public Long getBalcaoId() {
        return balcaoId;
    }

    public void setBalcaoId(Long balcaoId) {
        this.balcaoId = balcaoId;
    }

    public String getNomeBalcao() {
        return nomeBalcao;
    }

    public void setNomeBalcao(String nomeBalcao) {
        this.nomeBalcao = nomeBalcao;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }


    @Override
    public String toString() {
        return "Balcao{" +
                "balcaoId=" + balcaoId +
                ", nomeBalcao='" + nomeBalcao + '\'' +
                ", atendente=" + (atendente != null ? atendente.getAtendenteId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balcao)) return false;
        Balcao balcao = (Balcao) o;
        return balcaoId!= null && balcaoId.equals(balcao.balcaoId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
