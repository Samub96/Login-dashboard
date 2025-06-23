package ips.citas.dto;

public class TerapiaResponseDTO {
    private Long id;
    private String nombreTerapia;
    private int cantidadSesiones;
    private int sesionesRealizadas;
    private int sesionesPendientes;

    public TerapiaResponseDTO() {
    }

    public TerapiaResponseDTO(Long id, String nombreTerapia, int cantidadSesiones, int sesionesRealizadas, int sesionesPendientes) {
        this.id = id;
        this.nombreTerapia = nombreTerapia;
        this.cantidadSesiones = cantidadSesiones;
        this.sesionesRealizadas = sesionesRealizadas;
        this.sesionesPendientes = sesionesPendientes;
    }
// Getters, Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTerapia() {
        return nombreTerapia;
    }

    public void setNombreTerapia(String nombreTerapia) {
        this.nombreTerapia = nombreTerapia;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public int getSesionesRealizadas() {
        return sesionesRealizadas;
    }

    public void setSesionesRealizadas(int sesionesRealizadas) {
        this.sesionesRealizadas = sesionesRealizadas;
    }

    public int getSesionesPendientes() {
        return sesionesPendientes;
    }

    public void setSesionesPendientes(int sesionesPendientes) {
        this.sesionesPendientes = sesionesPendientes;
    }
}
