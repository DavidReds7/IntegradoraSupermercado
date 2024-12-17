package edu.utez.supermercado.Entities;

import jakarta.persistence.*;
import java.util.LinkedList;

@Entity
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCaja;
    private Boolean activa;

    @ElementCollection
    private LinkedList<Long> filaClientes;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCaja() {
        return nombreCaja;
    }

    public void setNombreCaja(String nombreCaja) {
        this.nombreCaja = nombreCaja;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public LinkedList<Long> getFilaClientes() {
        return filaClientes;
    }

    public void setFilaClientes(LinkedList<Long> filaClientes) {
        this.filaClientes = filaClientes;
    }
}