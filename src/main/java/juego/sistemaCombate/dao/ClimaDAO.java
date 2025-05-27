package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.Clima;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClimaDAO {

    public List<Clima> getTodos() {
        List<Clima> climas = new ArrayList<>();
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs = conex.prepareCall("{CALL obtener_todos_los_climas()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                climas.add(Clima.valueOf(rs.getString("nombre")));
            }
            rs.close();
            cs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return climas;
    }
}
