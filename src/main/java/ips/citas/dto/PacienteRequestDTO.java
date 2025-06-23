package ips.citas.dto;

import jakarta.validation.constraints.NotBlank;

public class PacienteRequestDTO {
    @NotBlank
    private String documentoIdentidad;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String entidadSalud;

    public PacienteRequestDTO() {
    }

    public PacienteRequestDTO(String documentoIdentidad, String nombre, String apellido, String entidadSalud) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.entidadSalud = entidadSalud;
    }

    public @NotBlank String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(@NotBlank String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank String getEntidadSalud() {
        return entidadSalud;
    }

    public void setEntidadSalud(@NotBlank String entidadSalud) {
        this.entidadSalud = entidadSalud;
    }

    // Getters, Setters
}
