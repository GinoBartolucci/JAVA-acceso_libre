package entities;

public class Show {
	private int id;
	private String nombre;
	private float precio;
	private int fecha;
	private int lugar_id;
	private int productora_id;
	private int artista_id;
	
	public Show(int id, String nombre, float precio, int fecha, int lugar_id, int productora_id, int artista_id) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.lugar_id = lugar_id;
		this.productora_id = productora_id;
		this.artista_id = artista_id;
	}	
	public Show(String nombre, float precio, int fecha, int lugar_id, int productora_id, int artista_id) {
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.lugar_id = lugar_id;
		this.productora_id = productora_id;
		this.artista_id = artista_id;
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
	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}
	public int getLugar_id() {
		return lugar_id;
	}
	public void setLugar_id(int lugar_id) {
		this.lugar_id = lugar_id;
	}
	public int getProductora_id() {
		return productora_id;
	}
	public void setProductora_id(int productora_id) {
		this.productora_id = productora_id;
	}
	public int getArtista_id() {
		return artista_id;
	}
	public void setArtista_id(int artista_id) {
		this.artista_id = artista_id;
	}
}
