package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static DbConnector instancia;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String host = "localhost";
    private String port = "3306";
    private String user = "java";
    private String pass = "java";
    //private String user = "root";
    //private String pass = "genius123";
    private String db = "venta_entradas";
    private String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
    private int conectados = 0;
    private Connection conn=null;

    private DbConnector() throws ClassNotFoundException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    public static DbConnector getInstancia() throws ClassNotFoundException {
        if (instancia == null) {
            instancia = new DbConnector();
        }
        return instancia;
    }

    public Connection getConn() throws SQLException {
        try {
            if(conn==null || conn.isClosed()) {
                conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, pass);
                conectados=0;
            }
        } catch (SQLException e) {
            throw e;
        }
        conectados++;
        return conn;
    }

    public void releaseConn() throws SQLException {
        conectados--;
        try {
            if (conectados<=0) {
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
