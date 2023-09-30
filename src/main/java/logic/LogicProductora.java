package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataProductora;
import entities.Productora;

public class LogicProductora {
	  private DataProductora dp;

	  public LogicProductora() {
		dp = new DataProductora();
	  }
	  public LinkedList<Productora> getAll() throws SQLException, ClassNotFoundException {		  
	    return dp.getAll();
	  }
	  public void findById(Productora p) throws SQLException, ClassNotFoundException {
		dp.findById(p);
	  }
	  public void getByEmail(Productora p) throws SQLException, ClassNotFoundException {
		dp.getByEmail(p);
	  }
	  public void create (Productora p) throws SQLException, ClassNotFoundException {
		dp.create(p);
	  }
	  public void update (Productora p) throws SQLException, ClassNotFoundException {
		dp.update(p);
	  }
	  public void delete (Productora p) throws SQLException, ClassNotFoundException {
		dp.delete(p);
	  }
}