package logic;
import data.DataShow;
import entities.Show;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogicShow {
  
  private DataShow ds;

  public LogicShow() {
    ds = new DataShow();
  }
  public LinkedList<Show> getAll() throws SQLException, ClassNotFoundException {
    return ds.getAll();
  }
  public void findById(Show s) throws SQLException, ClassNotFoundException {
	  ds.findById(s);
  }
  public void create (Show s) throws SQLException, ClassNotFoundException {
	  ds.create(s);
  }
  public void update (Show s) throws SQLException, ClassNotFoundException {
	  ds.update(s);
  }
  public void delete (Show s) throws SQLException, ClassNotFoundException {
	  ds.delete(s);
  }
}