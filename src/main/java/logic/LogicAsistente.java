package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataAsistente;
import entities.Asistente;

public class LogicAsistente {
	  private DataAsistente da;

	  public LogicAsistente() {
		da = new DataAsistente();
	  }
	  public LinkedList<Asistente> getAll() throws SQLException, ClassNotFoundException {		  
	    return da.getAll();
	  }
	  public void findById(Asistente a) throws SQLException, ClassNotFoundException {
		da.findById(a);
	  }
	  public void getByEmail(Asistente a) throws SQLException, ClassNotFoundException {
		da.getByEmail(a);
	  }
	  public void create (Asistente a) throws SQLException, ClassNotFoundException {
		da.create(a);
	  }
	  public void update (Asistente a) throws SQLException, ClassNotFoundException {
		da.update(a);
	  }
	  public void delete (Asistente a) throws SQLException, ClassNotFoundException {
		da.delete(a);
	  }
}
