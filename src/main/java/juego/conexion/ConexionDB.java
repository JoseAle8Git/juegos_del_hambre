package juego.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instancia;
    private static Connection conexion;

    private static final String USER = "root";
    private static final String PWD = "";
    private static final String URL = "jdbc:MySQL://localhost/";

    private ConexionDB(String nombreDB) throws SQLException {
        conexion = DriverManager.getConnection(URL + nombreDB, USER, PWD);
    }
    public static ConexionDB getInstance(String nombreDB) throws SQLException {
        if(instancia == null || conexion.isClosed()) instancia = new ConexionDB(nombreDB);
        return instancia;
    }
    public Connection getConnection() {
        return conexion;
    }

}
