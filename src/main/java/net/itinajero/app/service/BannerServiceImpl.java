package net.itinajero.app.service;

import net.itinajero.app.model.Banner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BannerServiceImpl implements IBannerService {

    private List<Banner> lista = null;

    public BannerServiceImpl() {
        lista = new LinkedList<Banner>();

        Banner banner1 = new Banner(1, "titulo-1", new Date(), "slide1", "estatus-1");
        Banner banner2 = new Banner(2, "titulo-2", new Date(), "slide2", "estatus-2");
        Banner banner3= new Banner(3, "titulo-3", new Date(), "slide3", "estatus-3");
        Banner banner4 = new Banner(4, "titulo-4", new Date(), "slide4", "estatus-4");
        Banner banner5 = new Banner(5, "titulo-5", new Date(), "slide5", "estatus-5");
        Banner banner6 = new Banner(6, "titulo-6", new Date(), "slide5", "estatus-6");
        Banner banner7 = new Banner(7, "titulo-7", new Date(), "slide5", "estatus-7");

        lista.add(banner1);
        lista.add(banner2);
        lista.add(banner3);
        lista.add(banner4);
        lista.add(banner5);
        lista.add(banner6);
        lista.add(banner7);
    }

    @Override
    public void insertar(Banner banner) {
        lista.add(banner);

    }

    @Override
    public List<Banner> buscarTodos() {
        return lista;
    }
}
