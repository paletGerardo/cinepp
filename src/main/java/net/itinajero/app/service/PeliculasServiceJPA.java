package net.itinajero.app.service;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculasServiceJPA implements IPeliculasService {

    @Autowired
    private PeliculasRepository repository;

    @Override
    public void insertar(Pelicula pelicula) {
        repository.save(pelicula);
    }

    @Override
    public List<Pelicula> buscarTodas() {
        return repository.findAll();
    }

    @Override
    public Pelicula buscarPorId(int idPelicula) {
        Optional<Pelicula> pelicula = repository.findById(idPelicula);
        if (pelicula.isPresent()) {
            return pelicula.get();
        }
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

    @Override
    public void eliminar(int idPelicula) {
        repository.deleteById(idPelicula);
    }

    @Override
    public Page<Pelicula> listaPaginada(Pageable page) {

        return repository.findAll(page);
    }
}
