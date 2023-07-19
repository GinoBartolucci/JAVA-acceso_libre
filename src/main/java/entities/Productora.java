package entities;

public class Productora extends Usuario {
    private String cuil;
    private String nombre;
    private String telefono;

    public Productora(int id, String nombre_usuario, String email,String password,
                      String nombre, String cuil, String telefono) {
        super(nombre_usuario, email, password, true);
        this.nombre = nombre;
        this.cuil = cuil;
        this.telefono = telefono;
    }
    public Productora(String nombre_usuario, String email,String password,
            String nombre, String cuil, String telefono) {
		super(nombre_usuario, email, password, true);
		this.nombre = nombre;
		this.cuil = cuil;
		this.telefono = telefono;
	}
    
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCuil() {
        return cuil;
    }
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
}
