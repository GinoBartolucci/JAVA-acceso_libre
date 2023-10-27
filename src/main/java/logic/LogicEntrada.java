package logic;
import data.DataEntradas;
import entities.Entrada;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogicEntrada {
  
  private DataEntradas de;

  public LogicEntrada() {
    de = new DataEntradas();
  }
  public LinkedList<Entrada> getAll() throws SQLException, ClassNotFoundException {
    return de.getAll();
  }
  public void findById(Entrada e) throws SQLException, ClassNotFoundException {
	  de.findById(e);
  }
  public void create (Entrada e) throws SQLException, ClassNotFoundException {
	  de.create(e);
  }
  public void update (Entrada e) throws SQLException, ClassNotFoundException {
	  de.update(e);
  }
  public void delete (int id) throws SQLException, ClassNotFoundException {
	  de.delete(id);
  }
}