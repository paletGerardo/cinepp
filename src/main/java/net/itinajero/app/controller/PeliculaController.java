package net.itinajero.app.controller;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculaService;
import net.itinajero.app.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    public String guardar(Pelicula pelicula,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          @RequestParam("archivoImagen") MultipartFile multipart,
                          HttpServletRequest request){ //BindingResult captura el error del binding

        if (result.hasErrors()){
            System.out.println("errores");
        }

        if (!multipart.isEmpty()){
            String nombreImagen = Utileria.guardarImagen(multipart, request);
            pelicula.setImagen(nombreImagen);
        }

        System.out.println("elementos antes de cargar la lista " + servicePelicula.buscarTodas().size());
        servicePelicula.insertar(pelicula);
        System.out.println("elementos despues de cargar la lista " + servicePelicula.buscarTodas().size());

        redirectAttributes.addFlashAttribute("mensaje", "La pelicula se cargo de manera correcta");

        System.out.println("Se recibio la pelicula " + pelicula);
        //return "peliculas/formPelicula";

        return "redirect:/peliculas/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

    }
}
