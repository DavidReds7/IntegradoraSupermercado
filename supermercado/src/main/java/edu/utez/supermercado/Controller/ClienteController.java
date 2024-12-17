package edu.utez.supermercado.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utez.supermercado.Entities.Cliente;
import edu.utez.supermercado.Service.ClienteService;

@RestController
 @RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @PostMapping("/agregarCliente")
    public Cliente crearCliente(@RequestParam String nombre){
        return clienteService.crearCliente(nombre);
    }
}
