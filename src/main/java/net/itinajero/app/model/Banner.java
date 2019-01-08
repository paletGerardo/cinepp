package net.itinajero.app.model;

import java.util.Date;

public class Banner {

    private int id;
    private String titulo;
    private Date fecha;
    private String archivo;
    private String estatus;

    public Banner() {
    }

    public Banner(int id, String titulo, Date fecha, String archivo, String estatus) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.archivo = archivo;
        this.estatus = estatus;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", fecha=" + fecha +
                ", archivo='" + archivo + '\'' +
                ", estatus='" + estatus + '\'' +
                '}';
    }
}
