package edu.utez.supermercado.Service;

import edu.utez.supermercado.Entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class CajaService {

    private Queue<Cliente> queue = new LinkedList<>();

    public String agregarCliente(Cliente cliente) {
        queue.offer(cliente);
        return "Cliente " + cliente.getNombre() + " agregado a la cola.";
    }

    public String atenderCliente() {
        Cliente cliente = queue.poll();
        if (cliente != null) {
            return "Cliente " + cliente.getNombre() + " atendido.";
        } else {
            return "No hay clientes en la cola.";
        }
    }

    public Queue<Cliente> obtenerFila() {
        return queue;
    }
}
