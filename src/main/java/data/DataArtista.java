package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Artista;

public class DataArtista {
	public LinkedList<Artista> getAll() throws SQLException, ClassNotFoundException  {
        LinkedList<Artista> ListaArtistas = new LinkedList<Artista>();
        ResultSet rs =null;
        Statement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Artistas");
            if(rs != null) {
                while(rs.next()) {
                	Artista p = new Artista(rs.getInt("id"),rs.getString("nombre"));
                    ListaArtistas.add(p);
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
        return ListaArtistas;
    }

    public void findOne(Artista searchArtista) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM Artistas WHERE id = ?");
            stmt.setInt(1, searchArtista.getId());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                searchArtista.setNombre(rs.getString("nombre"));
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

    public void create(Artista createArtista) throws SQLException, ClassNotFoundException  {
        PreparedStatement stmt= null;
        ResultSet keyResultSet=null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                            "insert into Artistas(nombre) values(?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );
            stmt.setString(1, createArtista.getNombre());
            stmt.executeUpdate();

            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createArtista.setId(keyResultSet.getInt(1));
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

    public void update(Artista updateArtista) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE Artistas SET nombre = ? WHERE id = ?"
                    );
            stmt.setString(1, updateArtista.getNombre());
            stmt.setInt(2, updateArtista.getId());
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

    public void delete(Artista deleteArtista) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "DELETE FROM Artistas WHERE id = ?"
                    );
            stmt.setInt(1, deleteArtista.getId());
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
