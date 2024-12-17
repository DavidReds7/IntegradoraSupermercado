package edu.utez.supermercado.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.utez.supermercado.Entities.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
