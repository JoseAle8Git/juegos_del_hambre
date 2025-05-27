package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaseCombateDAO {

    public ClaseCombate cargarClasePorNombre(String nombreClase) {
        ClaseCombate clase = null;
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs1 = conex.prepareCall("{CALL obtener_clase_por_nombre(?, ?)}");
            cs1.setString(1, nombreClase);
            cs1.registerOutParameter(2, Types.INTEGER);
            cs1.execute();

            int idClase = cs1.getInt(2);
            cs1.close();

            clase = switch (nombreClase.toUpperCase()) {
                case "NORMAL" -> new Normal();
                case "SOLDADO" -> new Soldado();
                case "GUERRERO" -> new Guerrero();
                case "ARQUERO" -> new Arquero();
                case "CAZADOR" -> new Cazador();
                case "ASESINO" -> new Asesino();
                default -> throw new RuntimeException("Clase desconocida: " + nombreClase);
            };

            // Llamada al procedimiento para obtener los ataques de la clase
            CallableStatement cs2 = conex.prepareCall("{CALL obtener_ataques_por_clase(?)}");
            cs2.setInt(1, idClase);
            ResultSet rs2 = cs2.executeQuery();

            List<Ataque> ataques = new ArrayList<>();
            while (rs2.next()) {
                String nombreAtaque = rs2.getString("nombre");
                int dano = rs2.getInt("dano_base");
                double prob = rs2.getDouble("prob_acierto");
                EfectoEspecial efecto = EfectoEspecial.valueOf(rs2.getString("efecto_especial"));
                ataques.add(new Ataque(nombreAtaque, dano, prob, efecto));
            }

            clase.setAtaques(ataques);

            rs2.close();
            cs2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clase;
    }
}
