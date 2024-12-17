package edu.utez.supermercado.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.utez.supermercado.Entities.CarritoProducto;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    
}
