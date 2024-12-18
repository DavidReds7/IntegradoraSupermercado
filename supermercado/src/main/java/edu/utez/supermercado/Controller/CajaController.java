package edu.utez.supermercado.Controller;

import edu.utez.supermercado.Cola;
import edu.utez.supermercado.Entities.Cliente;
import edu.utez.supermercado.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caja")
public class CajaController {

    private Cola<Cliente> colaClientes = new Cola<Cliente>(50);
    private Cola<Cliente> colaClientes2 = new Cola<Cliente>(50);

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/agregar")
    public String agregarCliente(@RequestParam Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        colaClientes.offer(cliente);

        return "Cliente agregado a la cola: " + cliente.getNombre();
    }

    @GetMapping("/atender")
    public String atenderCliente() {
        Cliente clienteAtendido = colaClientes.poll();

        if (clienteAtendido != null) {
            return "Cliente atendido: " + clienteAtendido.getNombre();
        } else {
            return "No hay clientes en la cola para atender.";
        }
    }

    
    @GetMapping("/obtenerFila")
    public String obtenerFila() {
        if (colaClientes.isEmpty()) {
            return "La cola está vacía.";
        }

        String fila = "Clientes en la cola: ";
        while (!colaClientes.isEmpty()) {
            colaClientes2.offer(colaClientes.peek());
            Cliente cliente = colaClientes.poll();
            fila = fila+(cliente.getNombre())+" ";
        }

        while (!colaClientes2.isEmpty()) {
            colaClientes.offer(colaClientes2.poll());
        }
        return fila;
    }
}
