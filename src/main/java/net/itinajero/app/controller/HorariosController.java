package net.itinajero.app.controller;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/horarios")
public class HorariosController {

    @Autowired
    IPeliculaService peliculaService;

    @GetMapping(value = "/create")
    public String crear(@ModelAttribute Horario horario, Model model ) {
        List<Pelicula> listaPeliculas = peliculaService.buscarTodas();
        model.addAttribute("peliculas", listaPeliculas);
        return "horarios/formHorario";
    }

    /**
     * Metodo para guardar el registro del Horario
     * @param horario
     * @param model
     * @return
     */
    @PostMapping(value = "/save")
    public String guardar(@ModelAttribute Horario horario, BindingResult result, Model model) {

        if (result.hasErrors()){
            List<Pelicula> listaPeliculas = peliculaService.buscarTodas();
            model.addAttribute("peliculas", listaPeliculas);
            return "horarios/formHorario";
        }

        System.out.println("Guardando el objeto Horario: " + horario);
        return "redirect:/horarios/create";
    }

    /**
     * Personalizamos el Data Binding para todas las propiedades de tipo Date
     * @param binder
     */
    @InitBinder("horario")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
