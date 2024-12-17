
package edu.utez.supermercado.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

//esa clase pertenece a una tabla
@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "producto", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<CarritoProducto> carrito = new ArrayList<>();
    

    private Double precio;


}
