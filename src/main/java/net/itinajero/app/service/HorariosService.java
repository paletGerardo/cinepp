package net.itinajero.app.service;

import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HorariosService implements IHorariosService {

    @Autowired
    private HorariosRepository repository;

    @Override
    public List<Horario> listarHorarios(int idPelicula, Date fecha) {
        return repository.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
    }

    @Override
    public List<Horario> buscarTodos() {
        return repository.findAll();
    }

}
