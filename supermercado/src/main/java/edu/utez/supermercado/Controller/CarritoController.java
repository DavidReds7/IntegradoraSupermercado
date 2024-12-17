package edu.utez.supermercado.Controller;

import edu.utez.supermercado.Entities.CarritoProducto;
import edu.utez.supermercado.Repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    private Stack<CarritoProducto> historialEliminados = new Stack<>();

    @PostMapping("/agregar")
    public String agregarProductoAlCarrito(@RequestBody CarritoProducto carritoProducto) {
        carritoProductoRepository.save(carritoProducto);
        return "Producto agregado al carrito del cliente: " + carritoProducto.getCliente().getId();
    }

    @GetMapping("/{clienteId}")
    public List<CarritoProducto> listarProductosDelCarrito(@PathVariable Long clienteId) {
        return carritoProductoRepository.findByClienteId(clienteId);
    }

    @PostMapping("/eliminar")
    public String eliminarProductoDelCarrito(@RequestParam Long clienteId) {
        List<CarritoProducto> productosDelCliente = carritoProductoRepository.findByClienteId(clienteId);

        if (productosDelCliente.isEmpty()) {
            return "No hay productos en el carrito para el cliente con ID: " + clienteId;
        }

        for (CarritoProducto producto : productosDelCliente) {
            historialEliminados.push(producto);
            carritoProductoRepository.deleteById(producto.getId());
        }

        return String.format("Se eliminaron %d productos del carrito del cliente con ID: %d",
                productosDelCliente.size(), clienteId);
    }

    @PostMapping("/deshacer")
    public String deshacerEliminacion() {
        if (historialEliminados.isEmpty()) {
            return "No hay eliminaciones previas para deshacer.";
        }

        CarritoProducto productoRecuperado = historialEliminados.pop();
        carritoProductoRepository.save(productoRecuperado);

        return String.format("Producto restaurado al carrito: Cliente ID = %d, Producto ID = %d, Cantidad = %d",
                productoRecuperado.getCliente().getId(),
                productoRecuperado.getProducto().getId(),
                productoRecuperado.getCantidad());
    }
}