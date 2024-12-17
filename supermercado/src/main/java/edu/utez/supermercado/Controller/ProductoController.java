package edu.utez.supermercado.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utez.supermercado.Entities.Producto;
import edu.utez.supermercado.Service.ProductoService;

@RestController
@RequestMapping("/producto")

public class ProductoController {
     @Autowired
    private ProductoService productoService;
    @PostMapping("/agregarProducto")
    public Producto crearProducto(@RequestParam String nombre){
        return productoService.crearProducto(nombre);
    }

}
