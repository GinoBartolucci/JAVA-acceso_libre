package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Ciudad;
import entities.Lugar;
import entities.Provincia;

public class DataLugar {
	public LinkedList<Lugar> getAll() throws SQLException, ClassNotFoundException  {
		LinkedList<Lugar> ListaLugares = new LinkedList<Lugar>();
		ResultSet rs =null;
	    Statement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM lugares "
            		+ "inner join ciudades c on ciudad_id = c.id "
            		+ "inner join provincias p on c.provincia_id = p.id");
            if(rs != null) {
                while(rs.next()) {
                	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
                	Ciudad c = new Ciudad(rs.getInt("c.id"), rs.getString("c.nombre"), p);
                	Lugar l = new Lugar(rs.getInt("id"),rs.getString("nombre"),rs.getString("direccion"),
                			rs.getInt("capacidad"),c);
                    ListaLugares.add(l);
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
        return ListaLugares;
	}
    public void findById(Lugar searchLugar) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM lugares inner join ciudades c on ciudad_id = c.id "
                    		+ "inner join provincias p on c.provincia_id = p.id WHERE id = ?");
            stmt.setInt(1, searchLugar.getId());

            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
            	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
            	Ciudad c = new Ciudad(rs.getInt("c.id"), rs.getString("c.nombre"), p);            	
                searchLugar.setNombre(rs.getString("nombre"));
                searchLugar.setDireccion(rs.getString("direccion"));
                searchLugar.setCapacidad(rs.getInt("capacidad"));
                searchLugar.setCiudad(c);
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
    }

    public void create(Lugar createLugar) throws SQLException, ClassNotFoundException  {
        PreparedStatement stmt= null;
        ResultSet keyResultSet=null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                            "insert into lugares(nombre,direccion,capacidad,ciudad_id) values(?,?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );
            stmt.setString(1, createLugar.getNombre());
            stmt.setString(2, createLugar.getDireccion());
            stmt.setInt(3, createLugar.getCapacidad());
            stmt.setInt(4, createLugar.getCiudad().getId());

            stmt.executeUpdate();

            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createLugar.setId(keyResultSet.getInt(1));
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

    public void update(Lugar updateLugar) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE lugares SET nombre = ?,direccion = ?,"
                            + "capacidad = ?,ciudad_id = ? WHERE id = ?"
                    );
            stmt.setString(1, updateLugar.getNombre());
            stmt.setString(2, updateLugar.getDireccion());
            stmt.setInt(3, updateLugar.getCapacidad());
            stmt.setInt(4, updateLugar.getCiudad().getId());
            stmt.setInt(5, updateLugar.getId());
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

    public void delete( Lugar deleteLugar) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "DELETE FROM lugares WHERE id = ?"
                    );
            stmt.setInt(1, deleteLugar.getId());
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
