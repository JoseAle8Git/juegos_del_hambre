package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.Terreno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TerrenoDAO {

    public List<Terreno> getTodos() {
        List<Terreno> terrenos = new ArrayList<>();
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            String query = "SELECT nombre FROM terrenos";
            PreparedStatement ps = conex.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                terrenos.add(Terreno.valueOf(nombre));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return terrenos;
    }
}
