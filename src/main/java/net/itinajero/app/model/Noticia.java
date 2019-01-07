package net.itinajero.app.model;

import java.util.Date;

public class Noticia {

	private int id;
	private String titulo;
	private String detalles;
	private Date fecha = new Date();
	private String estatus;

	public Noticia() {
		this.estatus = "activa";
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

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", detalles=" + detalles + ", fecha=" + fecha + ", estatus="
				+ estatus + "]";
	}


}
