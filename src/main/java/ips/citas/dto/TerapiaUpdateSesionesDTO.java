package ips.citas.dto;

import jakarta.validation.constraints.Min;

public class TerapiaUpdateSesionesDTO {
    @Min(0)
    private int sesionesRealizadas;

    @Min(0)
    public int getSesionesRealizadas() {
        return sesionesRealizadas;
    }

    public void setSesionesRealizadas(@Min(0) int sesionesRealizadas) {
        this.sesionesRealizadas = sesionesRealizadas;
    }
}