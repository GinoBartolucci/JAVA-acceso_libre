package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Artista;
import entities.Ciudad;
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
	            rs = stmt.executeQuery("SELECT * FROM shows"
	            		+ "inner join artistas a on artista_id = a.id"
	            		+ "inner join lugares l on lugar_id = a.id"
	            		+ "inner join ciudades c on ciudad_id = c.id "
	            		+ "inner join provincias p on c.provincia_id = p.id");
	            if(rs != null) {
	                while(rs.next()) {
	                	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
	                	Ciudad c = new Ciudad(rs.getInt("c.id"), rs.getString("c.nombre"), p);
	                	Lugar l = new Lugar(rs.getInt("l.id"),rs.getString("l.nombre"),rs.getString("l.direccion"),
	                			rs.getInt("capacidad"),c);
	                	Artista a = new Artista(rs.getInt("a.id"), rs.getString("a.nombre"));
	                	Show s = new Show(rs.getInt("id"),rs.getString("nombre"), rs.getFloat("precio"),rs.getInt("fecha")
	                			,l ,rs.getInt("productora_id"), a);
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
	                    .prepareStatement("SELECT * FROM shows "
	    	            		+ "inner join artistas a on artista_id = a.id"
	    	            		+ "inner join lugares l on lugar_id = a.id"
	    	            		+ "inner join ciudades c on ciudad_id = c.id "
	    	            		+ "inner join provincias p on c.provincia_id = p.id"
	                    		+ "WHERE id = ?");
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
	                searchShow.setFecha(rs.getInt("fecha"));
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

	    public void create(Show createShow) throws SQLException, ClassNotFoundException  {
	        PreparedStatement stmt= null;
	        ResultSet keyResultSet=null;
	        try {
	            stmt=DbConnector.getInstancia().getConn().
	                    prepareStatement(
	                            "insert into Show(nombre, precio, fecha, lugar_id, productora_id,"
	                            + " artista_id) values(?, ?, ?, ?, ?, ?)",
	                            PreparedStatement.RETURN_GENERATED_KEYS
	                    );
	            stmt.setString(1, createShow.getNombre());
	            stmt.setFloat(2, createShow.getPrecio());
	            stmt.setInt(3, createShow.getFecha());
	            stmt.setInt(4, createShow.getLugar().getId());
	            stmt.setInt(5, createShow.getProductora_id());
	            stmt.setInt(6, createShow.getArtista().getId());
	            stmt.executeUpdate();

	            keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                createShow.setId(keyResultSet.getInt(1));
	            }
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

	    public void update(Show updateShow) throws SQLException, ClassNotFoundException {
	        PreparedStatement stmt = null;
	        try{
	            stmt = DbConnector.getInstancia().getConn()
	                    .prepareStatement(
	                            "UPDATE shows SET nombre = ?, precio = ?, fecha = ?, lugar_id = ?, "
	                            + "productora_id = ?, artista_id = ? WHERE id = ?"
	                    );
	            stmt.setString(1, updateShow.getNombre());
	            stmt.setFloat(2, updateShow.getPrecio());
	            stmt.setInt(3, updateShow.getFecha());
	            stmt.setInt(4, updateShow.getLugar().getId());
	            stmt.setInt(5, updateShow.getProductora_id());
	            stmt.setInt(6, updateShow.getArtista().getId());
	            stmt.setInt(7, updateShow.getId());
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

	    public void delete(Show deleteShow) throws SQLException, ClassNotFoundException {
	        PreparedStatement stmt = null;
	        try{
	            stmt = DbConnector.getInstancia().getConn()
	                    .prepareStatement(
	                            "DELETE FROM shows WHERE id = ?"
	                    );
	            stmt.setInt(1, deleteShow.getId());
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
