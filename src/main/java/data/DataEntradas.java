package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Entrada;

public class DataEntradas {
	 public LinkedList<Entrada> getAll() throws SQLException, ClassNotFoundException  {
	        LinkedList<Entrada> ListaEntradas = new LinkedList<Entrada>();
	        ResultSet rs =null;
	        Statement stmt = null;
	        try {
	            stmt = DbConnector.getInstancia().getConn().createStatement();
	            rs = stmt.executeQuery("SELECT * FROM entradas");
	            if(rs != null) {
	                while(rs.next()) {
	                	Entrada p = new Entrada(rs.getInt("id"), rs.getInt("asistente_id"), rs.getInt("show_id"),
	                			rs.getString("codigo"), rs.getString("nombre"), rs.getString("apellido"), 
	                			rs.getString("tipo_doc"), rs.getString("documento"), rs.getBoolean("validez"));
	                	ListaEntradas.add(p);
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
	        return ListaEntradas;
	    }

    public Entrada findById(Entrada searchEntrada) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Entrada entrada = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM entradas WHERE asistente_id = ? AND show_id = ?");
            stmt.setInt(1, searchEntrada.getAsistente_id());
            stmt.setInt(2, searchEntrada.getShow_id());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                entrada = new Entrada(rs.getInt("asistente_id"), rs.getInt("show_id"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipo_doc"), rs.getString("documento"), rs.getBoolean("validez") );
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
        return entrada;
    }
    public Entrada scanearEntrada(Entrada searchEntrada) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Entrada entrada = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM entradas WHERE documento = ? AND codigo = ?");
            stmt.setString(1, searchEntrada.getDocumento());
            stmt.setString(2, searchEntrada.getCodigo());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                entrada = new Entrada(rs.getInt("asistente_id"), rs.getInt("show_id"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipo_doc"), rs.getString("documento"), rs.getBoolean("validez") );
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
        return entrada;
    }
    
    public Entrada codigoExist(Entrada searchEntrada) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Entrada entrada = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM entradas WHERE codigo = ?");
            stmt.setString(1, searchEntrada.getCodigo());
            rs = stmt.executeQuery();
            if(rs != null && rs.next()){
                entrada = new Entrada(rs.getInt("asistente_id"), rs.getInt("show_id"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipo_doc"), rs.getString("documento"), rs.getBoolean("validez") );
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
        return entrada;
    }
    
    public LinkedList<Entrada> findByAsistenteId(Entrada searchEntrada) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        LinkedList<Entrada> entradas = new LinkedList<Entrada>();
        try {
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement("SELECT * FROM entradas WHERE asistente_id = ?");
            stmt.setInt(1, searchEntrada.getAsistente_id());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Entrada entrada = new Entrada(
                    rs.getInt("asistente_id"),
                    rs.getInt("show_id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("tipo_doc"),
                    rs.getString("documento"),
                    rs.getBoolean("validez")
                );
                entradas.add(entrada);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (Exception e) {
                throw e;
            }
        }
        return entradas;
    }

    public void create(Entrada createEntrada) throws SQLException, ClassNotFoundException  {
        PreparedStatement stmt= null;
        ResultSet keyResultSet=null;
        try {
            stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                            "insert into entradas(asistente_id, show_id, codigo, nombre, apellido,"
                            + " tipo_doc, documento, validez) values(?, ?, ?, ?, ?, ?, ?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );
            stmt.setInt(1, createEntrada.getAsistente_id());
            stmt.setInt(2, createEntrada.getShow_id());
            stmt.setString(3, createEntrada.getCodigo());
            stmt.setString(4, createEntrada.getNombre());
            stmt.setString(5, createEntrada.getApellido());
            stmt.setString(6, createEntrada.getTipo_doc());
            stmt.setString(7, createEntrada.getDocumento());
            stmt.setBoolean(8, createEntrada.isValidez());
            stmt.executeUpdate();

            keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                createEntrada.setId(keyResultSet.getInt(1));
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

    public void update(Entrada updateEntrada) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        try{
            stmt = DbConnector.getInstancia().getConn()
                    .prepareStatement(
                            "UPDATE entradas SET codigo = ?, nombre = ?,"
                            + "apellido = ?,tipo_doc = ?, documento = ?, validez = ?  WHERE asistente_id = ? AND show_id = ?"
                    );
            
            stmt.setString(1, updateEntrada.getCodigo());
            stmt.setString(2, updateEntrada.getNombre());
            stmt.setString(3, updateEntrada.getApellido());
            stmt.setString(4, updateEntrada.getTipo_doc());
            stmt.setString(5, updateEntrada.getDocumento());
            stmt.setBoolean(6, updateEntrada.isValidez());
            stmt.setInt(7, updateEntrada.getAsistente_id());
            stmt.setInt(8, updateEntrada.getShow_id());
            
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
                            "DELETE FROM entradas WHERE id = ?"
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
