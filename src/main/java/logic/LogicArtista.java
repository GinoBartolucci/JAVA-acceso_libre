package logic;
import data.DataArtista;
import entities.Artista;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogicArtista {
  
  private DataArtista da;

  public LogicArtista() {
    da = new DataArtista();
  }
  public LinkedList<Artista> getAll() throws SQLException, ClassNotFoundException {
    return da.getAll();
  }
  public void findById(Artista a) throws SQLException, ClassNotFoundException {
    da.findById(a);
  }
  public void create (Artista a) throws SQLException, ClassNotFoundException {
    da.create(a);
  }
  public void update (Artista a) throws SQLException, ClassNotFoundException {
    da.update(a);
  }
  public void delete (Artista a) throws SQLException, ClassNotFoundException {
    da.delete(a);
  }
}
