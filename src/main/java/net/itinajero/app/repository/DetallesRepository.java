package net.itinajero.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.itinajero.app.model.Detalle;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesRepository extends JpaRepository<Detalle, Integer> {

}
