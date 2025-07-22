package ips.citas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TerapiaRequestDTO {
    @NotBlank
    private String nombreTerapia;

    @Min(1)
    private int cantidadSesiones;

    @NotBlank
    private String numeroOrden ;

    public TerapiaRequestDTO(String nombreTerapia, int cantidadSesiones, String numeroOrden) {
        this.nombreTerapia = nombreTerapia;
        this.cantidadSesiones = cantidadSesiones;
        this.numeroOrden = numeroOrden;
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

    public @NotBlank String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(@NotBlank String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
}
