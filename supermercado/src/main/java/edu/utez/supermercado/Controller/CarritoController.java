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
    public String eliminarProductoDelCarrito(@RequestParam Long carritoProductoId) {
        Optional<CarritoProducto> productoEliminado = carritoProductoRepository.findById(carritoProductoId);
        if (productoEliminado.isPresent()) {
            CarritoProducto producto = productoEliminado.get();
            historialEliminados.push(producto);
            carritoProductoRepository.deleteById(carritoProductoId);
            return String.format("Producto eliminado: Cliente ID = %d, Producto ID = %d, Cantidad = %d",
                    producto.getCliente().getId(),
                    producto.getProducto().getId(),
                    producto.getCantidad());
        } else {
            return "No se encontró el producto a eliminar.";
        }
    }

    @PostMapping("/deshacer")
    public String deshacerEliminacion() {
        if (!historialEliminados.isEmpty()) {
            CarritoProducto productoRecuperado= new CarritoProducto();
             productoRecuperado = historialEliminados.pop();
             System.out.println("Recuperado: "+productoRecuperado.toString());
             CarritoProducto productoRestaurado=new CarritoProducto();
             
             productoRestaurado.setCantidad(productoRecuperado.getCantidad());
             productoRestaurado.setCliente(productoRecuperado.getCliente());
             productoRestaurado.setProducto(productoRecuperado.getProducto());

            carritoProductoRepository.save(productoRestaurado);
            return String.format("Producto restaurado al carrito: Cliente ID = %d, Producto ID = %d, Cantidad = %d",
                    productoRecuperado.getCliente().getId(),
                    productoRecuperado.getProducto().getId(),
                    productoRecuperado.getCantidad());
        } else {
            return "No hay eliminaciones previas para deshacer.";
        }
    }

}