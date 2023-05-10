import data.*;
import entities.*;

public class Main {
    public static void main(String[] args) {
        DataAsistente da = new DataAsistente();
        Asistente a = new Asistente(0, "nombre_usuario2",
                "email@email.com", "password");
        da.create(a);
        System.out.println(a.getId() + " " + a.getNombre_usuario());
        System.out.println("Hello world!");
    }
}