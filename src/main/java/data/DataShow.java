package data;
import logic.LogicLugar;
import logic.LogicArtista;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Artista;
import entities.Ciudad;
import entities.Entrada;
import entities.Productora;
import entities.Lugar;
import entities.Provincia;
import entities.Show;

public class DataShow {
	public LinkedList<Show> getAll() throws SQLException, ClassNotFoundException  {
        LinkedList<Show> ListaShows = new LinkedList<Show>();
        ResultSet rs =null;
        Statement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT s.id, s.nombre, s.precio, s.fecha, s.productora_id, p.id, p.nombre, c.id, c.nombre, l.id, l.nombre, l.direccion, l.capacidad, a.id, a.nombre  FROM shows s "
                    + "inner join artistas a on artista_id = a.id "
                    + "inner join lugares l on lugar_id = l.id "
                    + "inner join ciudades c on ciudad_id = c.id "
                    + "inner join provincias p on c.provincia_id = p.id");
            if(rs != null) {
                while(rs.next()) {
                	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
                	Ciudad c = new Ciudad(rs.getInt("c.id"), rs.getString("c.nombre"), p);
                	Lugar l = new Lugar(rs.getInt("l.id"),rs.getString("l.nombre"),rs.getString("l.direccion"),
                			rs.getInt("l.capacidad"),c);
                	Artista a = new Artista(rs.getInt("a.id"), rs.getString("a.nombre"));
                	Show s = new Show(rs.getInt("s.id"),rs.getString("s.nombre"), rs.getFloat("s.precio"),rs.getTimestamp("s.fecha")
                			,l ,rs.getInt("s.productora_id"), a);
                	ListaShows.add(s);
                }
            }
        }catch (SQLException e) {
            throw e;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e) {
                throw e;
            }
        }
        return ListaShows;
    }
	 public LinkedList<Show> getByCiudad(int id) throws SQLException, ClassNotFoundException  {
	        LinkedList<Show> ListaShows = new LinkedList<Show>();
	        ResultSet rs = null;
	        PreparedStatement stmt = null;
	        try {
	            stmt = DbConnector.getInstancia().getConn()
	                    .prepareStatement("SELECT s.id, s.nombre, s.precio, s.fecha, s.productora_id, p.id, p.nombre, c.id, c.nombre, l.id, l.nombre, l.direccion, l.capacidad, a.id, a.nombre  FROM shows s "
	                            + "inner join artistas a on artista_id = a.id "
	                            + "inner join lugares l on lugar_id = l.id "
	                            + "inner join ciudades c on ciudad_id = c.id "
	                            + "inner join provincias p on c.provincia_id = p.id WHERE c.id = ?");
	            stmt.setInt(1, id);
	            rs = stmt.executeQuery();
	            if(rs != null) {
	                while(rs.next()) {
	                	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
	                	Ciudad c = new Ciudad(rs.getInt("c.id"), rs.getString("c.nombre"), p);
	                	Lugar l = new Lugar(rs.getInt("l.id"),rs.getString("l.nombre"),rs.getString("l.direccion"),
	                			rs.getInt("l.capacidad"),c);
	                	Artista a = new Artista(rs.getInt("a.id"), rs.getString("a.nombre"));
	                	Show s = new Show(rs.getInt("s.id"),rs.getString("s.nombre"), rs.getFloat("s.precio"),rs.getTimestamp("s.fecha")
	                			,l ,rs.getInt("s.productora_id"), a);
	                	ListaShows.add(s);
	                }
	            }
	        }catch (SQLException e) {
	            throw e;
	        }finally {
	            try{
	                if(rs != null) rs.close();
	                if(stmt != null) stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            }catch (Exception e) {
	                throw e;
	            }
	        }
	        return ListaShows;
	    }

	    public void findById(Show searchShow) throws SQLException, ClassNotFoundException {
	        ResultSet rs = null;
	        PreparedStatement stmt = null;
	        try{
	        	stmt = DbConnector.getInstancia().getConn()
	        		    .prepareStatement("SELECT * FROM shows " +
	        		                     "INNER JOIN artistas a ON artista_id = a.id " +
	        		                     "INNER JOIN lugares l ON lugar_id = a.id " +
	        		                     "INNER JOIN ciudades c ON ciudad_id = c.id " +
	        		                     "INNER JOIN provincias p ON c.provincia_id = p.id " +
	        		                     "WHERE id = ?");
	            stmt.setInt(1, searchShow.getId());

	            rs = stmt.executeQuery();
	            if(rs != null && rs.next()){
	            	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
                	Ciudad c = new Ciudad(rs.getInt("c.id"), rs.getString("c.nombre"), p);
                	Lugar l = new Lugar(rs.getInt("l.id"),rs.getString("l.nombre"),rs.getString("l.direccion"),
                			rs.getInt("capacidad"),c);
                	Artista a = new Artista(rs.getInt("a.id"), rs.getString("a.nombre"));
	                searchShow.setNombre(rs.getString("nombre"));
	                searchShow.setPrecio(rs.getFloat("precio"));
	                searchShow.setFecha(rs.getDate("fecha"));
	                searchShow.setLugar(l);
	                searchShow.setProductora_id(rs.getInt("productora_id"));
	                searchShow.setArtista(a);
	            }
	        }catch (SQLException e) {
	            throw e;
	        }finally {
	            try{
	                if(rs != null) rs.close();
	                if(stmt != null) stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            }catch (Exception e) {
	                throw e;
	            }
	        }
	        //return p;
	    }

	    public void create(String nombre, Float precio, String fecha, int lugarId, int productoraId, int artistaId) throws SQLException, ClassNotFoundException  {
	        PreparedStatement stmt= null;
	        ResultSet keyResultSet=null;
	        try {
	            stmt=DbConnector.getInstancia().getConn().
	                    prepareStatement(
	                            "insert into shows(nombre, precio, fecha, lugar_id, productora_id,"
	                            + " artista_id) values(?, ?, ?, ?, ?, ?)",
	                            PreparedStatement.RETURN_GENERATED_KEYS
	                    );
	            stmt.setString(1, nombre);
	            stmt.setFloat(2, precio);
	            stmt.setString(3, fecha);
	            stmt.setInt(4, lugarId);
	            stmt.setInt(5, productoraId);
	            stmt.setInt(6, artistaId);
	            stmt.executeUpdate();


	        } catch (SQLException e) {
	            throw e;
	        } finally {
	            try {
	                if(keyResultSet!=null)keyResultSet.close();
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	                throw e;
	            }
	        }

	    }

	    public void update( String nombre, Float precio, String fecha, int lugarId, int productoraId, int artistaId, int id) throws SQLException, ClassNotFoundException {
	        PreparedStatement stmt = null;
	        try{
	            stmt = DbConnector.getInstancia().getConn()
	                    .prepareStatement(
	                            "UPDATE shows SET nombre = ?, precio = ?, fecha = ?, lugar_id = ?, "
	                            + "productora_id = ?, artista_id = ? WHERE id = ?"
	                    );
	            stmt.setString(1, nombre);
	            stmt.setFloat(2, precio);
	            stmt.setString(3, fecha);
	            stmt.setInt(4, lugarId);
	            stmt.setInt(5, productoraId);
	            stmt.setInt(6, artistaId);
	            stmt.setInt(7, id);
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            throw e;
	        }finally {
	            try{
	                if(stmt != null) stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            }catch (Exception e) {
	                throw e;
	            }
	        }
	    }

	    public void delete(int id) throws SQLException, ClassNotFoundException {
	        PreparedStatement stmt = null;
	        try{
	            stmt = DbConnector.getInstancia().getConn()
	                    .prepareStatement(
	                            "DELETE FROM shows WHERE id = ?"
	                    );
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            throw e;
	        }finally {
	            try{
	                if(stmt != null) stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            }catch (Exception e) {
	                throw e;
	            }
	        }
	    }
}
