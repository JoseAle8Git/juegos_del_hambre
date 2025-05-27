package juego.sistemaCombate.modelo;

/* Representa al rol de combate de un personaje: arquero. */

public class Arquero extends ClaseCombate {

    public Arquero() {
        super("Arquero", 100, 0.05);
        ataques.add(new Ataque("Tiro rápido", 10, 0.85, EfectoEspecial.DOBLE_DANO_50));
        ataques.add(new Ataque("Lluvia de flechas", 12, 0.80, EfectoEspecial.SANGRADO));
        ataques.add(new Ataque("Flecha ígnea", 15, 0.80, EfectoEspecial.QUEMADURA));
        ataques.add(new Ataque("Paso ágil", 0, 1.00, EfectoEspecial.AUMENTAR_FALLO_ENEMIGO));
    }

    @Override
    public boolean haceCounterA(ClaseCombate otra) {
        return otra instanceof Normal || otra instanceof Guerrero;
    }

    @Override
    public boolean recibeCounterDe(ClaseCombate otra) {
        return otra instanceof Asesino;
    }
}
