package edu.utez.supermercado.Service;

import edu.utez.supermercado.Entities.CarritoProducto;
import edu.utez.supermercado.Pila;
import edu.utez.supermercado.Repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    private Pila<CarritoProducto> pilaEliminaciones = new Pila<>(50);

    public CarritoProducto agregarProductoACarrito(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public List<CarritoProducto> obtenerProductosDelCarrito(Long clienteId) {
        return carritoProductoRepository.findByClienteId(clienteId);
    }

    public String eliminarProductoDelCarrito(Long productoId, Long clienteId) {
        Optional<CarritoProducto> producto = carritoProductoRepository.findById(productoId);

        if (producto.isPresent()) {
            CarritoProducto carritoProducto = producto.get();

            if (carritoProducto.getCliente().getId().equals(clienteId)) {
                CarritoProducto productoGuardado = new CarritoProducto(
                        carritoProducto.getId(),
                        carritoProducto.getCliente(),
                        carritoProducto.getProducto(),
                        carritoProducto.getCantidad()
                );

                pilaEliminaciones.push(productoGuardado);
                System.out.println("Producto almacenado en la pila:");
                System.out.println("ID: " + productoGuardado.getId());
                System.out.println("Cliente ID: " + productoGuardado.getCliente().getId());
                System.out.println("Producto ID: " + (productoGuardado.getProducto() != null ? productoGuardado.getProducto().getId() : "N/A"));
                System.out.println("Cantidad: " + productoGuardado.getCantidad());
                
                carritoProducto.setCantidad(0);
                carritoProducto.setProducto(null);

                carritoProductoRepository.save(carritoProducto);

                return "Producto actualizado correctamente";
            } else {
                return "El producto no pertenece al cliente indicado";
            }
        } else {
            return "Producto no encontrado en el carrito";
        }
    }

    public String deshacerEliminacion() {
        if (!pilaEliminaciones.isEmpty()) {
            CarritoProducto ultimoEliminado = pilaEliminaciones.pop();

            if (ultimoEliminado.getId() != null) {
                CarritoProducto productoRestaurado = carritoProductoRepository.findById(ultimoEliminado.getId()).orElse(null);

                if (productoRestaurado != null) {
                    productoRestaurado.setCantidad(ultimoEliminado.getCantidad());
                    productoRestaurado.setProducto(ultimoEliminado.getProducto());

                    System.out.println("Restaurando producto desde la pila:");
                    System.out.println("ID: " + productoRestaurado.getId());
                    System.out.println("Cliente ID: " + productoRestaurado.getCliente().getId());
                    System.out.println("Producto ID: " + (productoRestaurado.getProducto() != null ? productoRestaurado.getProducto().getId() : "N/A"));
                    System.out.println("Cantidad: " + productoRestaurado.getCantidad());

                    carritoProductoRepository.save(productoRestaurado);

                    return "Se ha deshecho la última eliminación exitosamente";
                } else {
                    return "Producto no encontrado para restaurar";
                }
            } else {
                return "El producto restaurado no tiene un ID válido";
            }
        } else {
            return "No hay eliminaciones para deshacer";
        }
    }
}
