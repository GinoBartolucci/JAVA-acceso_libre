package entities;

public class Usuario {
    private int id;
    private String nombre_usuario;
    private String email;
    private String password;
    private boolean productora;

    public Usuario(int id, String nombre_usuario, String email, String password,
                   boolean productora) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.password = password;
        this.productora = productora;
    }
    public Usuario(String nombre_usuario, String email, String password,
                   boolean productora) {
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.password = password;
        this.productora = productora;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setProductora(boolean productora) {
        this.productora = productora;
    }
    public boolean getProductora() {
        return productora;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
