package net.itinajero.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Noticia;

@Controller
@RequestMapping(value = "/noticias")
public class NoticiasController {

	@GetMapping(value = "/create")
	public String crear() {
		return "formNoticia";
	}

	@PostMapping(value = "/save")
	public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus,
						  @RequestParam("detalles") String detalles) {
		Noticia objNoticia = new Noticia();
		objNoticia.setTitulo(titulo);
		objNoticia.setEstatus(estatus);
		objNoticia.setDetalles(detalles);

		System.out.println(objNoticia);
		return "formNoticia";
	}
}
