package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import net.itinajero.app.service.IDetallesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    private IDetallesService detallesService;

    @Autowired
    private IPeliculasService peliculasService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Pelicula> lista = peliculasService.buscarTodas();
        model.addAttribute("peliculas", lista);
        return "peliculas/listPeliculas";
    }

    @GetMapping("/create")
    public String crear(@ModelAttribute Pelicula pelicula, Model model) {
        return "peliculas/formPelicula";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int idPelicula, Model model){
        Pelicula pelicula = peliculasService.buscarPorId(idPelicula);

        model.addAttribute("pelicula", pelicula);
        return "peliculas/formPelicula";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
                          @RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request
    ) {

        if (result.hasErrors()) {
            //System.out.println("Existieron errores");
            return "peliculas/formPelicula";
        }

        if (!multiPart.isEmpty()) {
            String nombreImagen = Utileria.guardarImagen(multiPart, request);
            pelicula.setImagen(nombreImagen);
        }

        detallesService.insertar(pelicula.getDetalle());
        peliculasService.insertar(pelicula);
        attributes.addFlashAttribute("mensaje", "El registro fue guardado");
        return "redirect:/peliculas/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes){
        //buscar pelicula para eliminar detalle
        Pelicula pelicula = peliculasService.buscarPorId(idPelicula);

        //Elimino pelicula
        peliculasService.eliminar(idPelicula);
        //Elimino los detalles
        detallesService.eliminar(pelicula.getDetalle().getId());
        attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada");
        return "redirect:/peliculas/index";
    }

    @GetMapping(value = "/indexPaginate")
    public String indexPaginacion(Model model, Pageable page){
        Page<Pelicula> peliculas = peliculasService.listaPaginada(page);
        model.addAttribute("peliculas", peliculas);
        return "peliculas/listPeliculas";
    }



    @ModelAttribute(value = "generos")
    public List<String> generos(){
        return peliculasService.buscarGeneros();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
