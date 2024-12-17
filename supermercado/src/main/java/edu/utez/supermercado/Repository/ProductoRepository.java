package edu.utez.supermercado.Repository;

import edu.utez.supermercado.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {  }