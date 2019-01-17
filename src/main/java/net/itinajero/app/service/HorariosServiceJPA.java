package net.itinajero.app.service;

import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HorariosServiceJPA implements IHorariosServices {

    @Autowired
    private HorariosRepository repository;


    @Override
    public List<Horario> buscarporIdPelicula(int idPelicula, Date fecha) {
        return repository.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
    }

}
