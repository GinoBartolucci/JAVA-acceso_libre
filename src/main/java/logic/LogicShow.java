package logic;
import data.DataShow;
import entities.Show;

import java.sql.Date;
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
  public LinkedList<Show> getByCiudad(int id) throws SQLException, ClassNotFoundException {
	    return ds.getByCiudad(id);
	  }
  public void findById(Show s) throws SQLException, ClassNotFoundException {
	  ds.findById(s);
  }
  public void create (String nombre, Float precio, String fecha, int lugarId, int productoraId, int artistaId) throws SQLException, ClassNotFoundException {
	  ds.create(nombre, precio, fecha, lugarId, productoraId, artistaId);
  }
  public void update ( String nombre, Float precio, String fecha, int lugarId, int productoraId, int artistaId, int id) throws SQLException, ClassNotFoundException {
	  ds.update(nombre, precio, fecha, lugarId, productoraId, artistaId, id);
  }
  public void delete (int id) throws SQLException, ClassNotFoundException {
	  ds.delete(id);
  }
}