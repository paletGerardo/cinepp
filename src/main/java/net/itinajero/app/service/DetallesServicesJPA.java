package net.itinajero.app.service;

import net.itinajero.app.model.Detalle;
import net.itinajero.app.repository.DetallesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallesServicesJPA implements IDetallesService {

    @Autowired
    private DetallesRepository repository;

    @Override
    public void insertar(Detalle detalle) {
        repository.save(detalle);
    }

    @Override
    public void eliminar(int idDetalle) {
        repository.deleteById(idDetalle);
    }
}
