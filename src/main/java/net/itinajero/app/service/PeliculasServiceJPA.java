package net.itinajero.app.service;

import net.itinajero.app.model.Pelicula;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PeliculasServiceJPA implements IPeliculasService {
    @Override
    public void insertar(Pelicula pelicula) {

    }

    @Override
    public List<Pelicula> buscarTodas() {
        return null;
    }

    @Override
    public Pelicula buscarPorId(int idPelicula) {
        return null;
    }

    @Override
    public List<String> buscarGeneros() {
        // Nota: Esta lista podria ser obtenida de una BD
        List<String> generos = new LinkedList<String>();
        generos.add("Accion");
        generos.add("Aventura");
        generos.add("Clasicas");
        generos.add("Comedia Romantica");
        generos.add("Drama");
        generos.add("Terror");
        generos.add("Infantil");
        generos.add("Accion y Aventura");
        generos.add("Romantica");
        generos.add("Ciencia Ficcion");

        return generos;
    }
}
