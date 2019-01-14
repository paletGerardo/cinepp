package net.itinajero.app.service;

import java.util.List;

import net.itinajero.app.model.Pelicula;

public interface IPeliculaService {

	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
	void insertar(Pelicula pelicula);

	List<String> buscarGeneros();
}
