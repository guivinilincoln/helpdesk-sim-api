package br.com.meli.helpdesksimapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @NotNull(message = "O ID do cliente não deve ser nulo")
    private String customerId;

    @NotNull(message = "O nome do usuário não deve ser nulo")
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Pattern(regexp = "^[\\p{L}\\p{M}' \\.\\-]+$", message = "O nome deve conter apenas letras e espaços")
    private String nomeUsuario;

    public Usuario() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", customerId='" + customerId + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return usuarioId != null && usuarioId.equals(usuario.usuarioId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
