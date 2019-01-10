package net.itinajero.app.controller;

import net.itinajero.app.model.Banner;
import net.itinajero.app.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/banners/")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @GetMapping("/index")
    public String mostrarIndex(Model model){
        List<Banner> banners = bannerService.buscarTodos();
        model.addAttribute("banners", banners);
        return "banners/listBanners";
    }

    @GetMapping("/create")
    public String crear(){

        return "banners/formBanner";
    }

    @PostMapping("/save")
    public String guardar(Banner banner, @RequestParam MultipartFile multipartFile){

        bannerService.insertar(banner);
        return "redirect:/banners/index";
    }
}
