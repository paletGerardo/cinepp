package net.itinajero.app.service;

import net.itinajero.app.model.Detalle;
import org.springframework.stereotype.Service;

public interface IDetallesService {
    void insertar(Detalle detalle);
    void eliminar(int idDetalle);
}
