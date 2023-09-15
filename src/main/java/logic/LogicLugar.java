package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataLugar;
import entities.Lugar;

public class LogicLugar {
	
	  private DataLugar dl;

	  public LogicLugar() {
		dl = new DataLugar();
	  }
	  public LinkedList<Lugar> getAll() throws SQLException, ClassNotFoundException {		  
	    return dl.getAll();
	  }
	  public void findById(Lugar l) throws SQLException, ClassNotFoundException {
		dl.findById(l);
	  }
	  public void create (Lugar l) throws SQLException, ClassNotFoundException {
		dl.create(l);
	  }
	  public void update (Lugar l) throws SQLException, ClassNotFoundException {
		dl.update(l);
	  }
	  public void delete (Lugar l) throws SQLException, ClassNotFoundException {
		dl.delete(l);
	  }

}
