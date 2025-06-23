package ips.citas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdenRequestDTO {
    @NotBlank
    private String numeroOrden;

    @NotNull
    private Long pacienteId;

// Constructor
    public OrdenRequestDTO() {
    }

    public OrdenRequestDTO(String numeroOrden, Long pacienteId) {
        this.numeroOrden = numeroOrden;
        this.pacienteId = pacienteId;
    }
    // Getters, Setters
    public @NotBlank String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(@NotBlank String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public @NotNull Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(@NotNull Long pacienteId) {
        this.pacienteId = pacienteId;
    }


}