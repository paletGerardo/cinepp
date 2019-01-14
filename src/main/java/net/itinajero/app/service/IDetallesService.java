package net.itinajero.app.service;

import net.itinajero.app.model.Detalle;
import org.springframework.stereotype.Service;

@Service
public interface IDetallesService {
    void insertar(Detalle detalle);
}
