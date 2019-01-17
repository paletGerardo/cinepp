package net.itinajero.app.service;

import net.itinajero.app.model.Horario;

import java.util.Date;
import java.util.List;

public interface IHorariosServices {
    List<Horario> buscarporIdPelicula(int idPelicula, Date fecha);
}
