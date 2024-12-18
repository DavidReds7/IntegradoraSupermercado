package edu.utez.supermercado.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.utez.supermercado.Entities.Caja;

public interface CajaRepository extends JpaRepository<Caja, Long> {  }