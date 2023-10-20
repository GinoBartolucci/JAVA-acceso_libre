package entities;

import java.util.Date;

public class Show {
	private int id;
	private String nombre;
	private float precio;
	private Date fecha;
	private Lugar lugar;
	private int productora_id;
	private Artista artista;
	
	public Show(int id, String nombre, float precio, Date fecha, Lugar lugar, int productora_id, Artista artista) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.lugar = lugar;
		this.productora_id = productora_id;
		this.artista = artista;
	}	
	public Show(String nombre, float precio, Date fecha, Lugar lugar, int productora_id, Artista artista) {
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.lugar = lugar;
		this.productora_id = productora_id;
		this.artista = artista;
	}
	public Show() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Lugar getLugar() {
		return lugar;
	}
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	public int getProductora_id() {
		return productora_id;
	}
	public void setProductora_id(int productora_id) {
		this.productora_id = productora_id;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
}
