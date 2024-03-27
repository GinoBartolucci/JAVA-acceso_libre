package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Asistente;
import entities.Productora;

public class DataProductora {
    public LinkedList<Productora> getAll() throws SQLException, ClassNotFoundException{
        LinkedList<Productora> ListaProductoras = new LinkedList<Productora>();
        ResultSet rs = null;
        Statement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuarios where productora = 1");
            if(rs != null){
                while(rs.next()){
                    Productora p = new Productora(rs.getInt("id"),
                            rs.getString("nombre_usuario"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("cuil"),
                            rs.getString("telefono"));
                    ListaProductoras.add(p);
                }
            }
        }catch (SQLException e){
            throw e;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                throw e;
            }
        }
        return ListaProductoras;
    }
    
    public void findById(Productora searchProductora) throws SQLException, ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "SELECT * FROM usuarios WHERE id = ? and productora = 1"
            );
            stmt.setInt(1, searchProductora.getId());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
            	searchProductora.setNombre_usuario(rs.getString("nombre_usuario"));
            	searchProductora.setEmail(rs.getString("email"));
            	searchProductora.setPassword(rs.getString("password"));
            	searchProductora.setNombre(rs.getString("nombre"));
            	searchProductora.setCuil(rs.getString("cuil"));
            	searchProductora.setTelefono(rs.getString("telefono"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                throw e;
            }
        }
    }
    public void getByEmail(Productora searchProductora) throws SQLException, ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ? and productora = 1");
            stmt.setString(1, searchProductora.getEmail());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
            	searchProductora.setId(rs.getInt("id"));
            	searchProductora.setNombre_usuario(rs.getString("nombre_usuario"));
            	searchProductora.setEmail(rs.getString("email"));
            	searchProductora.setPassword(rs.getString("password"));
            	searchProductora.setNombre(rs.getString("nombre"));
            	searchProductora.setCuil(rs.getString("cuil"));
            	searchProductora.setTelefono(rs.getString("telefono"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                throw e;
            }
        }
    }
    public void create(Productora createProductora) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        ResultSet keyResultSet=null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "INSERT INTO usuarios (nombre_usuario, email, " +
                            "password,productora,cuil,telefono,nombre) VALUES(?,?,?,1,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, createProductora.getNombre_usuario());
            stmt.setString(2, createProductora.getEmail());
            stmt.setString(3, createProductora.getPassword());
            stmt.setString(4, createProductora.getCuil());
            stmt.setString(5, createProductora.getTelefono());
            stmt.setString(6, createProductora.getNombre());
            stmt.executeUpdate();
            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	createProductora.setId(keyResultSet.getInt(1));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            try{
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                throw e;
            }
        }
    }
    public void update(Productora updateProductora) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE usuarios SET nombre_usuario = ?, email=?, cuil=?, telefono=?,"
                            + "nombre=? WHERE id = ? and productora = 1"
                    );
            stmt.setString(1, updateProductora.getNombre_usuario());
            stmt.setString(2, updateProductora.getEmail());
            stmt.setString(3, updateProductora.getCuil());
            stmt.setString(3, updateProductora.getTelefono());
            stmt.setString(3, updateProductora.getNombre());
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

    public void delete(Productora deleteProductora) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "DELETE FROM usuarios WHERE id = ? and productora = 1"
                    );
            stmt.setInt(1, deleteProductora.getId());
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
