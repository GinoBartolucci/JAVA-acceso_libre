package logic;
import data.DataProvincia;
import entities.Provincia;
import java.sql.SQLException;
import java.util.LinkedList;
public class LogicProvincia {
  
  private DataProvincia dp;

  public LogicProvincia() {
    dp = new DataProvincia();
  }
  public LinkedList<Provincia> getAll() throws SQLException, ClassNotFoundException {
    return dp.getAll();
  }
  public void findById(Provincia p) throws SQLException, ClassNotFoundException {
    dp.findById(p);
  }
  public void create (Provincia p) throws SQLException, ClassNotFoundException {
    dp.create(p);
  }
  public void update (Provincia p) throws SQLException, ClassNotFoundException {
    dp.update(p);
  }
  public void delete (Provincia p) throws SQLException, ClassNotFoundException {
    dp.delete(p);
  }
}
