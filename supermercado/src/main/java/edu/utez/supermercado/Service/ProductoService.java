package edu.utez.supermercado.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utez.supermercado.Entities.Producto;
import edu.utez.supermercado.Repository.ProductoRepository;

@Service
public class ProductoService {
@Autowired
private ProductoRepository productoRepository;
    public Producto crearProducto(String nombre){
        Producto producto = new Producto();
        producto.setNombre(nombre);
        return productoRepository.save(producto);
    }
}

