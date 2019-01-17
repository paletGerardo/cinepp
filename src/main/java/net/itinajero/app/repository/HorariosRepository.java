package net.itinajero.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.itinajero.app.model.Horario;

import java.util.Date;
import java.util.List;

@Repository
public interface HorariosRepository extends JpaRepository<Horario, Integer> {

    public List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date fecha);

}
