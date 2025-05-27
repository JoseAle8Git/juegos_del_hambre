package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonajeDAO {

    private final ClaseCombateDAO claseDAO = new ClaseCombateDAO();

    public Personaje cargarPersonajePorNombre(String nombre) {
        Personaje personaje = null;

        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            String query = "SELECT p.*, c.nombre AS clase_nombre " +
                    "FROM personajes p JOIN clases_combate c ON p.id_clase = c.id " +
                    "WHERE p.nombre = ?";
            PreparedStatement ps = conex.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String claseNombre = rs.getString("clase_nombre");
                String rol = rs.getString("rol");

                ClaseCombate clase = claseDAO.cargarClasePorNombre(claseNombre);

                switch (rol.toLowerCase()) {
                    case "jugador" -> personaje = new Jugador(nombre, clase);
                    case "enemigo" -> personaje = new Enemigo(nombre, clase);
                    case "aliado"  -> personaje = new Aliado(nombre, clase);
                    default -> throw new RuntimeException("Rol no reconocido: " + rol);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return personaje;
    }
}
