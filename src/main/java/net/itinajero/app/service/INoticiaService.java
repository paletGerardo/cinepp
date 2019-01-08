package net.itinajero.app.service;

import net.itinajero.app.model.Noticia;
import org.springframework.stereotype.Service;

public interface INoticiaService {
    void guardar(Noticia noticia);
}
