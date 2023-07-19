package entities;

public class Lugar {
	private int id;
	private String nombre;
	private String direccion;
	private int capacidad;
	private int ciudad_id;
	
	public Lugar(int id, String nombre, String direccion, int capacidad, int ciudad_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.ciudad_id = ciudad_id;
	}	
	public Lugar(String nombre, String direccion, int capacidad, int ciudad_id) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.ciudad_id = ciudad_id;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCiudad_id() {
		return ciudad_id;
	}
	public void setCiudad_id(int ciudad_id) {
		this.ciudad_id = ciudad_id;
	}
}
