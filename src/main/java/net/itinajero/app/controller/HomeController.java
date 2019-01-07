package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculaService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {

	@Autowired
	private IPeliculaService servicePelicula;

	private SimpleDateFormat laFecha = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goHome() {
		return "home";
	}

	//Render de Pagina Principal...
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mostrarPrincipal(Model modelo){
		List<String> listaFecha = Utileria.getNextDays(4);
		System.out.println(listaFecha);
		List<Pelicula> peliculas = servicePelicula.buscarTodas();
		modelo.addAttribute("listaFechas", listaFecha);
		modelo.addAttribute("peliculas", peliculas);
		modelo.addAttribute("fecha", laFecha.format(new Date()));
		return "home";
	}

	//Filtrar peliculas por FECHA...
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model modelo){
		List<String> listaFecha = Utileria.getNextDays(4);
		List<Pelicula> peliculas = servicePelicula.buscarTodas();

		modelo.addAttribute("listaFechas", listaFecha);
		modelo.addAttribute("peliculas", peliculas);
		modelo.addAttribute("fecha", fecha);

		System.out.println("fechas de la oelicula:"  + fecha);
		return "home";
	}

	@RequestMapping(value = "/detail/{id}/{fecha}", method = RequestMethod.GET)
	//PathVariable es notacion propia de Spring para definir cual es el parametro en la URL.
	public String mostrarDetalle(Model modelo, @PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha)
	{
		System.out.println("id de la pelicula:" + idPelicula);
		System.out.println("fecha:" + fecha);

//		String tituloPelicula = "terminator 3, 'El colapso'";
//		Integer duracion = 136;
//		Double precio = 30.00;
//		modelo.addAttribute("titulo", tituloPelicula);
//		modelo.addAttribute("duracion", duracion);
//		modelo.addAttribute("precio", precio);
		return "detalles";
	}

	//Esta es otra forma de recibir parametros por la URL
	@RequestMapping(value = "/detailR", method = RequestMethod.GET)
	public String mostrarDetalleR(Model modelo, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha)
	{
		System.out.println("id de la pelicula:" + idPelicula);
		System.out.println("fecha:" + fecha);

		return "detalles";
	}

}
