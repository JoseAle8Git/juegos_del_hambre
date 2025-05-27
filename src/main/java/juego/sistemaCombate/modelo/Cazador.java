package juego.sistemaCombate.modelo;

/* Representa al rol de combate de un personaje: cazador. */

public class Cazador extends ClaseCombate {

    public Cazador() {
        super("Cazador", 110, 0.10);
        ataques.add(new Ataque("Flecha penetrante", 20, 0.90, EfectoEspecial.IGNORAR_ESCUDO));
        ataques.add(new Ataque("Trampa de caza", 10, 0.95, EfectoEspecial.INMUNE_EFECTOS_SIGUIENTE_TURNO));
        ataques.add(new Ataque("Tiro de reacción", 15, 0.85, EfectoEspecial.BONUS_DANO_SI_FUE_ATACADO_ANTES));
        ataques.add(new Ataque("Sigilo natural", 0, 1.00, EfectoEspecial.FALLO_60_PROXIMO_TURNO));
    }

    @Override
    public boolean haceCounterA(ClaseCombate otra) {
        return otra instanceof Normal || otra instanceof Asesino;
    }

    @Override
    public boolean recibeCounterDe(ClaseCombate otra) {
        return otra instanceof Guerrero;
    }
}
