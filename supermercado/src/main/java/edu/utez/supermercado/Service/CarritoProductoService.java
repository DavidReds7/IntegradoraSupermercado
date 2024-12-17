package edu.utez.supermercado.Service;

import edu.utez.supermercado.Entities.CarritoProducto;
import edu.utez.supermercado.Repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    public CarritoProducto agregarProductoACarrito(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public List<CarritoProducto> obtenerProductosDelCarrito(Long clienteId) {
        return carritoProductoRepository.findByClienteId(clienteId);
    }

    public void eliminarProductoDelCarrito(Long id) {
        carritoProductoRepository.deleteById(id);
    }

    public void deshacerEliminacionProducto(CarritoProducto carritoProducto) {
        carritoProductoRepository.save(carritoProducto);
    }
}