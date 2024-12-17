package edu.utez.supermercado.Service;

import edu.utez.supermercado.Entities.Cliente;
import edu.utez.supermercado.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository
        .findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }
}