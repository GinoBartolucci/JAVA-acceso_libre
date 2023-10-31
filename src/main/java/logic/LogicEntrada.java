package logic;
import data.DataEntradas;
import entities.Entrada;
import entities.Show;

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
  public Entrada findById(Entrada e) throws SQLException, ClassNotFoundException {
	  return de.findById(e);
  }
  public Entrada scanearEntrada(Entrada searchEntrada) throws SQLException, ClassNotFoundException {
	  return de.scanearEntrada(searchEntrada);
  }
  public int countEntriesByShowId(Show show) throws SQLException, ClassNotFoundException {
	  return de.countEntriesByShowId(show);
  }
  public boolean codigoExist(Entrada searchEntrada) throws SQLException, ClassNotFoundException {
	  if ( de.codigoExist(searchEntrada) == null ) return false;
	  else return true;
  }
  public LinkedList<Entrada> findByAsistenteId(Entrada e) throws SQLException, ClassNotFoundException {
	  return de.findByAsistenteId(e);
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