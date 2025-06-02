package juego.historiaKatniss.controlador;

import juego.historiaKatniss.modelo.Jugador;
import juego.historiaKatniss.vista.Narrador;

import java.util.Random;

public class GestorCombates {
    private Jugador jugador;
    private int combatesRealizados;
    private int tributosRestantes;
    private boolean juegoActivo;
    private Runnable capitulo5Callback;

    public GestorCombates(Jugador jugador, Runnable capitulo5Callback) {
        this.jugador = jugador;
        this.tributosRestantes = 24 - 1; // 23 tributos enemigos (incluye Peeta, no se elimina)
        this.juegoActivo = true;
        this.capitulo5Callback = capitulo5Callback;
    }

    public boolean juegoSigueActivo() {
        return juegoActivo;
    }

    public void iniciarCombatesEnArena() {
        while (combatesRealizados < 5 && tributosRestantes > 4 && juegoActivo) {
            Narrador.separador();
            Narrador.mostrar("✦ Se detecta un tributo enemigo en la zona... ¡prepara el combate!");

            realizarCombate();
            if (!juegoActivo) return;

            eliminarTributosAleatorios();
            combatesRealizados++;

            Narrador.mostrar("✦ Combates realizados: " + combatesRealizados + " / 5");
            Narrador.mostrar("✦ Tributos restantes: " + tributosRestantes);
        }

        if (tributosRestantes == 4 && juegoActivo) {
            Narrador.separador();
            Narrador.mostrar("✦ Solo quedan 4 tributos vivos...");
            Narrador.mostrar("✦ La fase final está a punto de comenzar.");
            capitulo5Callback.run(); // Avanza al Capítulo 5
        }
    }

    private void realizarCombate() {
        boolean ganado = Math.random() < 0.85;

        if (ganado) {
            Narrador.mostrar("✦ Has ganado el combate contra un tributo.");
            jugador.añadirPuntos(20);
        } else {
            Narrador.mostrar("✦ ¡Has perdido el combate!");
            jugador.recibirDaño(30);
            if (jugador.getVida() <= 0) {
                Narrador.mostrar("✦ Has muerto en la arena... El Capitolio te ha vencido.");
                juegoActivo = false;
            }
        }
    }

    private void eliminarTributosAleatorios() {
        int eliminados = new Random().nextInt(3) + 1; // entre 1 y 3
        // Nunca baja de 4
        tributosRestantes = Math.max(4, tributosRestantes - eliminados);
    }
}
