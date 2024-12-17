package edu.utez.supermercado.Repository;

import edu.utez.supermercado.Entities.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByClienteId(Long clienteId);
}
