package ips.citas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TerapiaRequestDTO {
    @NotBlank
    private String nombreTerapia;

    @Min(1)
    private int cantidadSesiones;

    @NotNull
    private Long ordenId;

    public TerapiaRequestDTO(String nombreTerapia, int cantidadSesiones, Long ordenId) {
        this.nombreTerapia = nombreTerapia;
        this.cantidadSesiones = cantidadSesiones;
        this.ordenId = ordenId;
    }

    public TerapiaRequestDTO() {
    }

    public @NotBlank String getNombreTerapia() {
        return nombreTerapia;
    }

    public void setNombreTerapia(@NotBlank String nombreTerapia) {
        this.nombreTerapia = nombreTerapia;
    }

    @Min(1)
    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(@Min(1) int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public @NotNull Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(@NotNull Long ordenId) {
        this.ordenId = ordenId;
    }
}
