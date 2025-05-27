package juego.sistemaCombate.modelo;

import java.util.List;
import java.util.Random;

public class Enemigo extends Personaje {

    private boolean consumidoCarneEsteTurno = false;
    private boolean consumidoHojaEsteTurno = false;

    private static final Random random = new Random();

    public Enemigo(String nombre, ClaseCombate clase) {
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

    public void setConsumidoCarneEsteTurno(boolean valor) {
        this.consumidoCarneEsteTurno = valor;
    }

    public void setConsumidoHojaEsteTurno(boolean valor) {
        this.consumidoHojaEsteTurno = valor;
    }
}
