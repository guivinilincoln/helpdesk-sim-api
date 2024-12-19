package br.com.meli.helpdesksimapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Maquininha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @NotNull(message = "O número serial não deve ser nulo")
    @Size(max = 10, message = "O número serial deve ter no máximo 10 caracteres")
    private String serialNumber;

    public Maquininha() {
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Maquininha{" +
                "deviceId=" + deviceId +
                ", serialNumber=" + serialNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maquininha)) return false;
        Maquininha maquininha = (Maquininha) o;
        return deviceId != null && deviceId.equals(maquininha.deviceId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
