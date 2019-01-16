package net.itinajero.app.service;

import java.util.List;

import net.itinajero.app.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPeliculasService {

	void insertar(Pelicula pelicula); 
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
	List<String> buscarGeneros();
	void eliminar(int idPelicula);

	Page<Pelicula> listaPaginada(Pageable page);
}
