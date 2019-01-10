package net.itinajero.app.model;

import java.util.Date;

public class Pelicula {

    private int id;
    private String titulo;
    private String clasificacion;
    private int duracion;
    private String genero;
    private String imagen = "cinema.jpg";
    private Date fechaEstreno;
    private String estatus = "activa";

    private Detalle detalle;

    public Pelicula() {
        System.out.println("constructor de detalle.............................................");
    }

    public Pelicula(int id, String titulo, String clasificacion, int duracion, String genero, String imagen, Date fechaEstreno, String estatus, Detalle detalle) {
        this.id = id;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
        this.genero = genero;
        this.imagen = imagen;
        this.fechaEstreno = fechaEstreno;
        this.estatus = estatus;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", duracion=" + duracion +
                ", genero='" + genero + '\'' +
                ", imagen='" + imagen + '\'' +
                ", fechaEstreno=" + fechaEstreno +
                ", estatus='" + estatus + '\'' +
                ", detalle=" + detalle +
                '}';
    }
}