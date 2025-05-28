package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.Terreno;

import java.sql.CallableStatement;
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
            CallableStatement cs = conex.prepareCall("{CALL obtener_todos_los_terrenos()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                terrenos.add(Terreno.valueOf(nombre));
            }
            rs.close();
            cs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return terrenos;
    }
}
