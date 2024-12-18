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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataChamado;

    private Date dataResolucao;

    @NotNull
    private String motivoChamado;

    @NotNull
    private String produto;

    @ManyToOne
    @JoinColumn(name = "deviceId", nullable = false)
    private Maquininha maquininha;

    // Construtor padrão
    public Chamado() {
        this.dataChamado = new Date();
    }

    // Getters e Setters

    public Long getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(Long chamadoId) {
        this.chamadoId = chamadoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Balcao getBalcao() {
        return balcao;
    }

    public void setBalcao(Balcao balcao) {
        this.balcao = balcao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDataChamado() {
        return dataChamado;
    }

    public void setDataChamado(Date dataChamado) {
        this.dataChamado = dataChamado;
    }

    public Date getDataResolucao() {
        return dataResolucao;
    }

    public void setDataResolucao(Date dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

    public String getMotivoChamado() {
        return motivoChamado;
    }

    public void setMotivoChamado(String motivoChamado) {
        this.motivoChamado = motivoChamado;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Maquininha getMaquininha() {
        return maquininha;
    }

    public void setMaquininha(Maquininha maquininha) {
        this.maquininha = maquininha;
    }

    // toString, equals e hashCode

    @Override
    public String toString() {
        return "Chamado{" +
                "chamadoId=" + chamadoId +
                ", usuario=" + (usuario != null ? usuario.getUsuarioId() : null) +
                ", balcao=" + (balcao != null ? balcao.getBalcaoId() : null) +
                ", status=" + status +
                ", dataChamado=" + dataChamado +
                ", dataResolucao=" + dataResolucao +
                ", motivoChamado='" + motivoChamado + '\'' +
                ", produto='" + produto + '\'' +
                ", maquininha=" + (maquininha != null ? maquininha.getDeviceId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chamado)) return false;
        Chamado chamado = (Chamado) o;
        return chamadoId != null && chamadoId.equals(chamado.chamadoId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
