package edu.utez.supermercado.Service;

import edu.utez.supermercado.Entities.Producto;
import edu.utez.supermercado.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }
}