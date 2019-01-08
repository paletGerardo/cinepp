package net.itinajero.app.controller;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private IPeliculaService servicePelicula;

    @GetMapping("/index")
    public String indexPeliculas(Model model){
        List<Pelicula> peliculas = servicePelicula.buscarTodas();
        model.addAttribute("peliculas", peliculas);
        return "peliculas/listPeliculas";
    }

    @GetMapping("/create")
    public String crear(){
        return "peliculas/formPelicula";
    }

    @PostMapping("/save")
    public String guardar(Pelicula pelicula, BindingResult result){ //BindingResult captura el error del binding

        if (result.hasErrors()){
            System.out.println("errores");
        }

        for (ObjectError error: result.getAllErrors()){
            System.out.println(error.getDefaultMessage());
        }

        System.out.println("elementos antes de cargar la lista " + servicePelicula.buscarTodas().size());
        servicePelicula.insertar(pelicula);
        System.out.println("elementos despues de cargar la lista " + servicePelicula.buscarTodas().size());

        System.out.println("Se recibio la pelicula " + pelicula);
        return "peliculas/formPelicula";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

    }
}
