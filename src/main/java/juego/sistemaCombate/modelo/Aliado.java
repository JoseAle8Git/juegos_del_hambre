package juego.sistemaCombate.modelo;

import java.util.List;
import java.util.Random;

public class Aliado extends Personaje {

    private static final Random random = new Random();

    public Aliado(String nombre, ClaseCombate clase) {
        super(nombre, clase);
    }

    @Override
    public Ataque elegirAtaque() {
        List<Ataque> listaAtaques = clase.getAtaques();
        int index = random.nextInt(listaAtaques.size());
        return listaAtaques.get(index);
    }

    @Override
    public boolean esJugador() {
        return false;
    }
}
