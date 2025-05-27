package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.MomentoDia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MomentoDiaDAO {

    public List<MomentoDia> getTodos() {
        List<MomentoDia> momentos = new ArrayList<>();
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            String query = "SELECT * FROM momentos_dia";
            PreparedStatement ps = conex.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                momentos.add(MomentoDia.valueOf(rs.getString("nombre")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return momentos;
    }
}
