package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClaseCombateDAO {

    public ClaseCombate cargarClasePorNombre(String nombreClase) {
        ClaseCombate clase = null;

        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            String query1 = "SELECT * FROM clases_combate WHERE nombre = ?";
            PreparedStatement ps1 = conex.prepareStatement(query1);
            ps1.setString(1, nombreClase);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                int indice = rs1.getInt("id");

                clase = switch (nombreClase.toUpperCase()) {
                    case "NORMAL" -> new Normal();
                    case "SOLDADO" -> new Soldado();
                    case "GUERRERO" -> new Guerrero();
                    case "ARQUERO" -> new Arquero();
                    case "CAZADOR" -> new Cazador();
                    case "ASESINO" -> new Asesino();
                    default -> throw new RuntimeException("Clase desconocida: " + nombreClase);
                };

                String query2 = "SELECT * FROM ataques WHERE id_clase = ?";
                PreparedStatement ps2 = conex.prepareStatement(query2);
                ps2.setInt(1, indice);
                ResultSet rs2 = ps2.executeQuery();

                List<Ataque> ataques = new ArrayList<>();
                while (rs2.next()) {
                    String nombreAtaque = rs2.getString("nombre");
                    int dano = rs2.getInt("dano_base");
                    double prob = rs2.getDouble("prob_acierto");
                    EfectoEspecial efecto = EfectoEspecial.valueOf(rs2.getString("efecto_especial"));
                    ataques.add(new Ataque(nombreAtaque, dano, prob, efecto));
                }

                clase.setAtaques(ataques);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clase;
    }
}
