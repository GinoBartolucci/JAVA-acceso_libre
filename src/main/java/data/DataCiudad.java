package data;
import entities.Ciudad;
import entities.Provincia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
public class DataCiudad {
    public LinkedList<Ciudad> getAll() throws SQLException, ClassNotFoundException  {
        LinkedList<Ciudad> ListaCiudades = new LinkedList<Ciudad>();
        ResultSet rs =null;
        Statement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM ciudades "
            		+ "inner join provincias p on provincia_id = p.id");
            if(rs != null) {
                while(rs.next()) {
                	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
                    Ciudad c = new Ciudad(rs.getInt("id"),rs.getString("nombre"),p);
                    ListaCiudades.add(c);
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
        return ListaCiudades;
    }

    public void findById(Ciudad searchCiudad) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM ciudades"
                    		+ "inner join provincias p on provincia_id = p.id"
                    		+ "WHERE id = ?");
            stmt.setInt(1, searchCiudad.getId());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                searchCiudad.setNombre(rs.getString("nombre"));
            	Provincia p = new Provincia(rs.getInt("p.id"), rs.getString("p.nombre"));
                searchCiudad.setProvincia(p);
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

    public void create(Ciudad createCiudad) throws SQLException, ClassNotFoundException  {
        PreparedStatement stmt= null;
        ResultSet keyResultSet=null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                            "insert into ciudades(nombre,provincia_id) values(?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );
            stmt.setString(1, createCiudad.getNombre());
            stmt.setInt(2, createCiudad.getProvincia().getId());
            stmt.executeUpdate();

            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createCiudad.setId(keyResultSet.getInt(1));
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

    public void update(Ciudad updateCiudad) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("UPDATE ciudades SET nombre = ?, provincia_id = ? WHERE id = ?"
                    );
            stmt.setString(1, updateCiudad.getNombre());
            stmt.setInt(2, updateCiudad.getProvincia().getId());
            stmt.setInt(3, updateCiudad.getId());
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

    public void delete(Ciudad deleteCiudad) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "DELETE FROM ciudades WHERE id = ?"
                    );
            stmt.setInt(1, deleteCiudad.getId());
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
