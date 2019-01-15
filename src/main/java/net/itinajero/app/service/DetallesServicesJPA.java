package net.itinajero.app.service;

import net.itinajero.app.model.Detalle;
import net.itinajero.app.repository.DetallesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DetallesServicesJPA implements IDetallesService {
    @Autowired
    private DetallesRepository detallesRepository;

    @Override
    public void insertar(Detalle detalle) {
        detallesRepository.save(detalle);
    }
}
