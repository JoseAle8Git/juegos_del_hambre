package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.MomentoDia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MomentoDiaDAO {

    public List<MomentoDia> getTodos() {
        List<MomentoDia> momentos = new ArrayList<>();
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs = conex.prepareCall("{CALL obtener_todos_los_momentos_dia()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                momentos.add(MomentoDia.valueOf(rs.getString("nombre")));
            }
            rs.close();
            cs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return momentos;
    }
}
