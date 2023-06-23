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
            rs = stmt.executeQuery("SELECT * FROM Ciudades");
            if(rs != null) {
                while(rs.next()) {
                    Ciudad p = new Ciudad();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setIdProvincia(rs.getInt("provincia_id"));
                    ListaCiudades.add(p);
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

    public void findOne(Ciudad searchCiudad) throws SQLException, ClassNotFoundException {
        //Ciudad p = null ;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM Ciudades WHERE id = ?");
            stmt.setInt(1, searchCiudad.getId());

            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                //p = new Ciudad();
                searchCiudad.setNombre(rs.getString("nombre"));
                searchCiudad.setIdProvincia(rs.getInt("provincia_id"));
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

    public void create(Ciudad createCiudad, Provincia provincia) throws SQLException, ClassNotFoundException  {
        PreparedStatement stmt= null;
        ResultSet keyResultSet=null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                            "insert into Ciudad(nombre) values(?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );
            stmt.setString(1, createCiudad.getNombre());
            stmt.setInt(2, provincia.getId());
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

    public void update(Ciudad updateCiudad, Provincia provincia) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE Ciudades SET nombre = ?, provincia_id = ? WHERE id = ?"
                    );
            stmt.setString(1, updateCiudad.getNombre());
            stmt.setInt(2, provincia.getId());
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
                            "DELETE FROM Ciudades WHERE id = ?"
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
