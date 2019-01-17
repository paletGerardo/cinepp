package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.itinajero.app.model.Horario;
import net.itinajero.app.service.IHorariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {

	@Autowired
	private IHorariosService horariosService;
	
	@Autowired
	private IBannersService serviceBanners;
	
	@Autowired
	private IPeliculasService servicePeliculas;
		
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model model){
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("banners", serviceBanners.buscarTodos()); 
				
		return "home2";
	}
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mostrarPrincipal(Model model) {

		List<String> listaFechas = Utileria.getNextDays(4);

		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("banners", serviceBanners.buscarTodos());
		System.out.println("RETORNA LISTA DE PELICULAS: " + peliculas);
		return "home2";
	}

	@RequestMapping(value = "/detail/{id}/{fecha}",method=RequestMethod.GET)		
	public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
		List<Horario> lista = horariosService.listarHorarios(idPelicula, fecha);

		model.addAttribute("horarios", lista);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		System.out.println("LISTADO DE HORARIO: " + lista);
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		// TODO - Buscar en la base de datos los horarios.		
		
		return "detalle";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	
}
