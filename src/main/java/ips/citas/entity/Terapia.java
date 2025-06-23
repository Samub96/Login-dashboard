package ips.citas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


import jakarta.persistence.*;

@Entity
public class Terapia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreTerapia;

    @Column(nullable = false)
    private int cantidadSesiones;

    private int sesionesRealizadas;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;




    //constructor

    public Terapia() {
    }

    public Terapia(Long id, Orden orden, String nombreTerapia, int cantidadSesiones, int sesionesRealizadas) {
        this.id = id;
        this.orden = orden;
        this.nombreTerapia = nombreTerapia;
        this.cantidadSesiones = cantidadSesiones;
        this.sesionesRealizadas = sesionesRealizadas;
    }

    // Getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
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
}
