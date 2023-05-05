package data;

import entities.Provincia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DataProvincia {
    public LinkedList<Provincia> getAll() {
        LinkedList<Provincia> ListaProvincias = new LinkedList<Provincia>();
        ResultSet rs =null;
        Statement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM provincias");
            if(rs != null) {
                while(rs.next()) {
                    Provincia p = new Provincia();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    ListaProvincias.add(p);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ListaProvincias;
    }

    public Provincia findOne(Provincia searchProvincia){
        Provincia p = null ;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM provincias WHERE id = ?");
            stmt.setInt(1, searchProvincia.getId());

            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                p = new Provincia();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public void create(Provincia createProvincia) {
        PreparedStatement stmt= null;
        ResultSet keyResultSet=null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                            "insert into provincia(nombre) values(?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );
            stmt.setString(1, createProvincia.getNombre());
            stmt.executeUpdate();

            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createProvincia.setId(keyResultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void update(Provincia updateProvincia){
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE provincias SET nombre = ? WHERE id = ?"
                    );
            stmt.setString(1, updateProvincia.getNombre());
            stmt.setInt(2, updateProvincia.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Provincia deleteProvincia){
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "DELETE FROM provincias WHERE id = ?"
                    );
            stmt.setInt(1, deleteProvincia.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
