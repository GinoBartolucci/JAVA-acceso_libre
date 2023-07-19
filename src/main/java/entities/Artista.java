package entities;

public class Artista {
  public int id;
  public String nombre;

  public Artista(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Artista(String nombre) {
    this.nombre = nombre;
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

}
