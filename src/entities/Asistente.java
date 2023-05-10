package entities;

public class Asistente extends Usuario{
    public Asistente(int id, String nombre_usuario, String email, String password) {
        super(id, nombre_usuario, email, password, false);
    }
    public Asistente(String nombre_usuario, String email, String password) {
        super(nombre_usuario, email, password, false);
    }
}
