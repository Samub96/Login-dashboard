package ips.citas.dto;

import java.util.List;

public class OrdenResponseDTO {
    private Long id;
    private String numeroOrden;
    private List<TerapiaResponseDTO> terapias;

    public OrdenResponseDTO() {
    }

    public OrdenResponseDTO(Long id, String numeroOrden, List<TerapiaResponseDTO> terapias) {
        this.id = id;
        this.numeroOrden = numeroOrden;
        this.terapias = terapias;
    }
// Getters, Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public List<TerapiaResponseDTO> getTerapias() {
        return terapias;
    }

    public void setTerapias(List<TerapiaResponseDTO> terapias) {
        this.terapias = terapias;
    }
}
