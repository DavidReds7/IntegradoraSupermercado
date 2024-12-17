package edu.utez.supermercado.Controller;

import edu.utez.supermercado.Entities.CarritoProducto;
import edu.utez.supermercado.Entities.Producto;
import edu.utez.supermercado.Repository.CarritoProductoRepository;
import edu.utez.supermercado.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/supermercado")
public class SupermercadoController {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/comprar/{clienteId}")
    public ResponseEntity<String> procesarCompra(@PathVariable Long clienteId) {
        List<CarritoProducto> productosEnCarrito = carritoProductoRepository.findByClienteId(clienteId);

        if (productosEnCarrito.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El carrito está vacío. No se puede procesar la compra.");
        }

        Double total = 0.0;
        for (CarritoProducto item : productosEnCarrito) {
            Producto producto = item.getProducto();
            total+=item.getProducto().getPrecio()*item.getCantidad();
            productoRepository.save(producto);
            carritoProductoRepository.delete(item);
        }
        return ResponseEntity.ok("Compra procesada exitosamente."+"total: "+total);
    }
}