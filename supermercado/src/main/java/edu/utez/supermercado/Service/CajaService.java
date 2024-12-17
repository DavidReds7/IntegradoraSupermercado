package edu.utez.supermercado.Service;

import edu.utez.supermercado.Repository.CajaRepository;
import edu.utez.supermercado.Entities.Caja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CajaService {

    @Autowired
    private CajaRepository cajaRepository;

    public Caja agregarCaja(Caja caja) {
        return cajaRepository.save(caja);
    }

    public List<Caja> obtenerCajas() {
        return cajaRepository.findAll();
    }

    public void atenderCliente(Long cajaId) {
        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new IllegalArgumentException("Caja no encontrada"));
        if (!caja.getFilaClientes().isEmpty()) {
            caja.getFilaClientes().remove(0);
            cajaRepository.save(caja);
        }
    }
}