package juego.sistemaCombate.logica;

import juego.sistemaCombate.dao.PersonajeDAO;
import juego.sistemaCombate.modelo.*;

public class ConstructorDeCombates {

    private final PersonajeDAO personajeDAO = new PersonajeDAO();
    private final CondicionesCombateConstructora condicionesConstructora = new CondicionesCombateConstructora();

    public Combate crearCombate(String nombreJugador, String nombreEnemigo, String nombreAliado) {
        Jugador jugador = (Jugador) personajeDAO.cargarPersonajePorNombre(nombreJugador);
        Enemigo enemigo = (Enemigo) personajeDAO.cargarPersonajePorNombre(nombreEnemigo);

        Aliado aliado = null;
        if (nombreAliado != null && !nombreAliado.trim().isEmpty()) {
            aliado = (Aliado) personajeDAO.cargarPersonajePorNombre(nombreAliado);
        }

        CondicionesCombate condiciones = condicionesConstructora.generarAleatorias();
        return new Combate(jugador, enemigo, aliado, condiciones);
    }
}
