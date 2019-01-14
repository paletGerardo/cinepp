package net.itinajero.app.repository;

import net.itinajero.app.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
}
