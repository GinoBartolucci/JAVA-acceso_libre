package data;

import entities.Asistente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DataAsistente {
    public LinkedList<Asistente> getAll() throws SQLException, ClassNotFoundException{
        LinkedList<Asistente> ListaAsistentes = new LinkedList<Asistente>();
        ResultSet rs = null;
        Statement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuarios where productora = 0");
            if(rs != null){
                while(rs.next()){
                    Asistente a = new Asistente(rs.getInt("id"),
                            rs.getString("nombre_usuario"),
                            rs.getString("email"),
                            rs.getString("password"));
                    ListaAsistentes.add(a);
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
        return ListaAsistentes;
    }
    
    public void findById(Asistente searchAsistente) throws SQLException, ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "SELECT * FROM usuarios WHERE id = ? and productora =0"
            );
            stmt.setInt(1, searchAsistente.getId());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                searchAsistente.setNombre_usuario(rs.getString("nombre_usuario"));
                searchAsistente.setEmail(rs.getString("email"));
                searchAsistente.setPassword(rs.getString("password"));
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
    public void getByEmail(Asistente searchAsistente) throws SQLException, ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ? and productora =0");
            stmt.setString(1, searchAsistente.getEmail());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                searchAsistente.setNombre_usuario(rs.getString("nombre_usuario"));
                searchAsistente.setEmail(rs.getString("email"));
                searchAsistente.setPassword(rs.getString("password"));
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
    public void create(Asistente createAsistente) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        ResultSet keyResultSet=null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "INSERT INTO usuarios (nombre_usuario, email, " +
                            "password,productora) VALUES(?,?,?,0)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, createAsistente.getNombre_usuario());
            stmt.setString(2, createAsistente.getEmail());
            stmt.setString(3, createAsistente.getPassword());
            stmt.executeUpdate();
            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createAsistente.setId(keyResultSet.getInt(1));
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
    public void update(Asistente updateAsistente) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE usuarios SET nombre_usuario = ?, email=? WHERE id = ? and productora = 0"
                    );
            stmt.setString(1, updateAsistente.getNombre_usuario());
            stmt.setString(1, updateAsistente.getEmail());
            stmt.setInt(2, updateAsistente.getId());
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

    public void delete(Asistente deleteAsistente) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "DELETE FROM usuarios WHERE id = ? and productora = 0"
                    );
            stmt.setInt(1, deleteAsistente.getId());
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
