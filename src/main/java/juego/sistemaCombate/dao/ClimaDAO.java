package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.Clima;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClimaDAO {

    public List<Clima> getTodos() {
        List<Clima> climas = new ArrayList<>();
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            String query = "SELECT * FROM climas";
            PreparedStatement ps = conex.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                climas.add(Clima.valueOf(rs.getString("nombre")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return climas;
    }
}
