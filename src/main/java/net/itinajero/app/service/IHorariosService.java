package net.itinajero.app.service;

import net.itinajero.app.model.Horario;

import java.util.Date;
import java.util.List;

public interface IHorariosService {
    List<Horario> listarHorarios(int idPelicula, Date fecha);
    List<Horario> buscarTodos();
}
