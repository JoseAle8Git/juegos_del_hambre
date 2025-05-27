package juego.sistemaCombate.dao;

import juego.conexion.ConexionDB;
import juego.sistemaCombate.modelo.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class PersonajeDAO {

    private final ClaseCombateDAO claseDAO = new ClaseCombateDAO();

    public Personaje cargarPersonajePorNombre(String nombre) {
        Personaje personaje = null;

        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs = conex.prepareCall("{CALL obtener_personaje_por_nombre(?)}");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();

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
            rs.close();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personaje;
    }
}
