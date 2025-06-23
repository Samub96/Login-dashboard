package ips.citas.dto;

import java.util.List;

public class PacienteResponseDTO {
    private Long id;
    private String documentoIdentidad;
    private String nombre;
    private String apellido;
    private String entidadSalud;
    private List<OrdenResponseDTO> ordenes;

    public PacienteResponseDTO() {

    }

    public PacienteResponseDTO(Long id, String documentoIdentidad, String nombre, String apellido, String entidadSalud, List<OrdenResponseDTO> ordenes) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.entidadSalud = entidadSalud;
        this.ordenes = ordenes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEntidadSalud() {
        return entidadSalud;
    }

    public void setEntidadSalud(String entidadSalud) {
        this.entidadSalud = entidadSalud;
    }

    public List<OrdenResponseDTO> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrdenResponseDTO> ordenes) {
        this.ordenes = ordenes;
    }

    // Getters, Setters
}
