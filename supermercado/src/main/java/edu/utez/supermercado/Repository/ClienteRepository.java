package edu.utez.supermercado.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.utez.supermercado.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
