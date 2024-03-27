package entities;


public class Lugar {
	private int id;
	private String nombre;
	private String direccion;
	private Integer capacidad;
	private Ciudad ciudad;
	
	public Lugar() {
		
	}
	public Lugar(int id) {
		super();
		this.id = id;
	}
	
	public Lugar(int id, String nombre, String direccion, int capacidad, Ciudad ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.ciudad = ciudad;
	}	
	public Lugar(String nombre, String direccion, int capacidad, Ciudad ciudad) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.ciudad = ciudad;
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
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
}
