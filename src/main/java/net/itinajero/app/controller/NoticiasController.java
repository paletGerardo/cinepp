package net.itinajero.app.controller;

import net.itinajero.app.service.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Noticia;

@Controller
@RequestMapping(value = "/noticias")
public class NoticiasController {

    @Autowired
	private INoticiaService serviceNoticia;

	@GetMapping(value = "/create")
	public String crear() {
		return "formNoticia";
	}

	@PostMapping(value = "/save")
	public String guardar(Noticia noticia) {


		serviceNoticia.guardar(noticia);
		return "formNoticia";
	}
}
