package juego.sistemaCombate.modelo;

/* Representa al rol de combate de un personaje: normal. */

public class Normal extends ClaseCombate {

    public Normal() {
        super("Normal", 120, 0.10);
        ataques.add(new Ataque("Golpe básico", 35, 0.95, EfectoEspecial.NINGUNO));
        ataques.add(new Ataque("Patada segadora", 40, 0.90, EfectoEspecial.FALLAR_SIGUIENTE_ATAQUE));
        ataques.add(new Ataque("Empujón revisado", 40, 0.85, EfectoEspecial.REDUCIR_DANO_SIGUIENTE_ATAQUE));
        ataques.add(new Ataque("Postura defensiva", 0, 1.00, EfectoEspecial.AUMENTAR_ESCUDO_TEMPORAL));
    }

    @Override
    public boolean haceCounterA(ClaseCombate otra) {
        return false;
    }

    @Override
    public boolean recibeCounterDe(ClaseCombate otra) {
        return true;
    }
}
