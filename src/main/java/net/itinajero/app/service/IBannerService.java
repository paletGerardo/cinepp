package net.itinajero.app.service;

import net.itinajero.app.model.Banner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBannerService {

    void insertar(Banner banner);
    List<Banner> buscarTodos();
}
