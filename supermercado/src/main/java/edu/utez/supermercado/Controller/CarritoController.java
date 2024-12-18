package edu.utez.supermercado.Controller;

import edu.utez.supermercado.Entities.CarritoProducto;
import edu.utez.supermercado.Service.CarritoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoProductoService carritoProductoService;

    @PostMapping("/agregar")
    public String agregarProductoAlCarrito(@RequestBody CarritoProducto carritoProducto) {
        carritoProductoService.agregarProductoACarrito(carritoProducto);
        return "Producto agregado al carrito del cliente: " + carritoProducto.getCliente().getId();
    }

    @GetMapping("/{clienteId}")
    public List<CarritoProducto> listarProductosDelCarrito(@PathVariable Long clienteId) {
        return carritoProductoService.obtenerProductosDelCarrito(clienteId);
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam Long productoId, @RequestParam Long clienteId) {
        return carritoProductoService.eliminarProductoDelCarrito(productoId, clienteId);
    }

    @PostMapping("/deshacer")
    public String deshacerEliminacion() {
        return carritoProductoService.deshacerEliminacion();
    }
}
