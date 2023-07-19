package entities;

public class Ciudad {
    private int id;
    private String nombre;
    private int idProvincia;

    public Ciudad(String nombre, int idProvincia) {
        this.nombre = nombre;
        this.idProvincia = idProvincia;
    }
    public Ciudad(int id, String nombre, int idProvincia) {
        this.id = id;
        this.nombre = nombre;
        this.idProvincia = idProvincia;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return nombre; // + " (" + idProvincia + ")";
    }
}
