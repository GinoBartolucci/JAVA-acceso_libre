package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataCiudad;
import entities.Ciudad;

public class LogicCiudad {
	  private DataCiudad dc;

	  public LogicCiudad() {
		dc = new DataCiudad();
	  }
	  public LinkedList<Ciudad> getAll() throws SQLException, ClassNotFoundException {		  
	    return dc.getAll();
	  }
	  public void findById(Ciudad c) throws SQLException, ClassNotFoundException {
		dc.findById(c);
	  }
	  public void create (Ciudad c) throws SQLException, ClassNotFoundException {
		dc.create(c);
	  }
	  public void update (Ciudad c) throws SQLException, ClassNotFoundException {
		dc.update(c);
	  }
	  public void delete (Ciudad c) throws SQLException, ClassNotFoundException {
		dc.delete(c);
	  }
}
