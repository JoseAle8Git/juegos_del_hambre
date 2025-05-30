package juego.historiaKatniss.modelo;

import juego.historiaKatniss.modelo.Zona;
import juego.historiaKatniss.vista.Narrador;

import java.util.EnumMap;
import java.util.Random;

public class MapaJuego {
    private EnumMap<Zona, Integer> enemigosPorZona;
    private boolean tieneMapa;

    public MapaJuego(boolean tieneMapa) {
        this.tieneMapa = tieneMapa;
        this.enemigosPorZona = new EnumMap<>(Zona.class);
        generarEnemigos();
    }

    private void generarEnemigos() {
        Random rand = new Random();
        int totalEnemigos = 24;
        int zonasDisponibles = Zona.values().length;

        enemigosPorZona.clear();

        // Inicializar todas las zonas con 0 enemigos
        for (Zona zona : Zona.values()) {
            enemigosPorZona.put(zona, 0);
        }


        while (totalEnemigos > 0) {
            Zona zonaAleatoria = Zona.values()[rand.nextInt(zonasDisponibles)];
            enemigosPorZona.put(zonaAleatoria, enemigosPorZona.get(zonaAleatoria) + 1);
            totalEnemigos--;
        }
    }


    public void mostrarZonasDisponibles() {
        Narrador.mostrar("Zonas disponibles:");
        for (Zona zona : Zona.values()) {
            String info = " - " + zona;
            if (tieneMapa) {
                info += " (Enemigos: " + enemigosPorZona.get(zona) + ")";
            }
            System.out.println(info);
        }
    }

    public int enemigosEnZona(Zona zona) {
        return enemigosPorZona.get(zona);
    }
}
