package data;

import entities.Asistente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DataAsistente {
    public LinkedList<Asistente> getAll(){
        LinkedList<Asistente> ListaAsistentes = new LinkedList<Asistente>();
        ResultSet rs = null;
        Statement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM asistentes");
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
            e.printStackTrace();
            return null;
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ListaAsistentes;
    }
public Asistente findOne(Asistente asistente){
        Asistente a = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "SELECT * FROM usuarios WHERE id = ?"
            );
            stmt.setInt(1, asistente.getId());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                a = new Asistente(rs.getInt("id"),
                        rs.getString("nombre_usuario"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return a;
    }
    public Asistente getByEmail(Asistente asistente){
        Asistente a = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ?");
            stmt.setString(1, asistente.getEmail());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                a = new Asistente(rs.getInt("id"),
                        rs.getString("nombre_usuario"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return a;
    }
    public void create(Asistente createAsistente){
        PreparedStatement stmt = null;
        ResultSet keyResultSet=null;
        try{
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                    "INSERT INTO usuarios (nombre_usuario, email, " +
                            "password) VALUES(?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, createAsistente.getNombre_usuario());
            stmt.setString(2, createAsistente.getEmail());
            stmt.setString(3, createAsistente.getPassword());
            stmt.executeUpdate();
            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createAsistente.setId(keyResultSet.getInt(1));
                //no se puede poner "id" pero si 1
                //Si la base de datos tira un problema se salta un id ????
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
