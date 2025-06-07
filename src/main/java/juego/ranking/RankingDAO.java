package juego.ranking;

import juego.conexion.ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;

public class RankingDAO {

    public static void insertarRanking(InsertarRanking datos) {
        try {
            Connection conex = ConexionDB.getInstance("tu_basededatos").getConnection();

            CallableStatement stmt = conex.prepareCall("{call insertarRanking(?, ?, ?, ?, ?)}");
            stmt.setString(1, datos.getNombreUsuario());
            stmt.setString(2, datos.getPersonaje());
            stmt.setString(3, datos.getFinalJuego());
            stmt.setInt(4, datos.getCombatesGanados());
            stmt.setInt(5, datos.getPuntos());

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
