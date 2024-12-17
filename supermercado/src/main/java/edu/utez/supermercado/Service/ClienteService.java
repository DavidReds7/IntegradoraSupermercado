package edu.utez.supermercado.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utez.supermercado.Entities.Cliente;
import edu.utez.supermercado.Repository.ClienteRepository;

@Service
public class ClienteService {
@Autowired
private ClienteRepository clienteRepository;
    public Cliente crearCliente(String nombre){
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        return clienteRepository.save(cliente);
    }
}
