package edu.utez.supermercado.Controller;

import edu.utez.supermercado.Entities.Cliente;
import edu.utez.supermercado.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/caja")
public class CajaController {
    @Autowired
    private ClienteRepository clienteRepository;

    private  Queue< Optional<Cliente> > queue=new LinkedList<>();

    @PostMapping("/agregar")
    public String agregarCliente(@RequestParam Long clienteId) {
        Optional<Cliente> cliente=clienteRepository.findById(clienteId);
       
        queue.offer(cliente);
        return "Cliente "+cliente.get().getNombre();
    }

    @GetMapping("/atender")
    public Optional<Cliente> atenderCliente(@RequestParam Long cajaId) {
        Optional<Cliente> cliente=queue.poll();
       return cliente;
    }

    @GetMapping("/obtenerFila")
    public List<Optional<Cliente>> obtenerFila(@RequestParam Long cajaId) {
        List<Optional<Cliente>> lista=new ArrayList<>();
        while (!queue.isEmpty()) {
            lista.add(queue.poll());
        }
        for (Optional<Cliente> cliente : lista) {
            queue.offer(cliente) ;
        }
        return lista;
    }
}