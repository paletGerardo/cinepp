package net.itinajero.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import net.itinajero.app.model.Pelicula;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServiceImp implements IPeliculaService {

	private List<Pelicula> lista = null;

	public PeliculaServiceImp() {
		System.out.println("creando una instancia de peliculas");
		SimpleDateFormat formaterr = new SimpleDateFormat("dd-MM-yyyy");

		try {
			lista = new LinkedList<Pelicula>();

			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("Power Ranger");
			pelicula1.setDuracion(124);
			pelicula1.setClasificacion("atp");
			pelicula1.setGenero("terror");
			pelicula1.setImagen("estreno1.png");
			pelicula1.setFechaEstreno(formaterr.parse("12-03-1983"));

			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("La Bella y La Bestia");
			pelicula2.setDuracion(124);
			pelicula2.setClasificacion("atp");
			pelicula2.setGenero("accion");
			pelicula2.setImagen("estreno2.png");
			pelicula2.setEstatus("inactiva");
			pelicula2.setFechaEstreno(formaterr.parse("14-02-2018"));

			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Contratiempo");
			pelicula3.setDuracion(134);
			pelicula3.setClasificacion("atp");
			pelicula3.setGenero("accion");
			pelicula3.setImagen("estreno3.png");
			pelicula3.setEstatus("inactiva");
			pelicula2.setFechaEstreno(formaterr.parse("14-02-2018"));

			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("Kong: La Isla Calavera");
			pelicula4.setDuracion(124);
			pelicula4.setClasificacion("atp");
			pelicula4.setGenero("accion");
			pelicula4.setImagen("estreno4.png");
			pelicula4.setEstatus("inactiva");
			pelicula2.setFechaEstreno(formaterr.parse("14-02-2018"));

			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);

			System.out.println(lista);

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Pelicula> buscarTodas() {
		return lista;
	}

	public Pelicula buscarPorId(int idPelicula) {

		for (Pelicula p : lista){
			if (p.getId() == idPelicula){
				return  p;
			}
		}
		return null;
	}

	@Override
	public void insertar(Pelicula pelicula) {
		lista.add(pelicula);
	}
}
