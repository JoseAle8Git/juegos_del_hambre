package juego.sistemaCombate.modelo;

/* Representa al rol de combate de un personaje: asesino. */

public class Asesino extends ClaseCombate {

    public Asesino() {
        super("Asesino", 90, 0.05);
        ataques.add(new Ataque("Puñalada letal", 45, 0.70, EfectoEspecial.EJECUTAR_SI_VIDA_BAJA));
        ataques.add(new Ataque("Corte doble", 25, 0.90, EfectoEspecial.SIN_DEFENSA_SIGUIENTE_TURNO));
        ataques.add(new Ataque("Veneno silencioso", 30, 0.80, EfectoEspecial.VENENO_3T));
        ataques.add(new Ataque("Humo ninja", 0, 1.00, EfectoEspecial.INMUNE_A_ATAQUE_PROXIMO_TURNO));
    }

    @Override
    public boolean haceCounterA(ClaseCombate otra) {
        return otra instanceof Normal || otra instanceof Arquero;
    }

    @Override
    public boolean recibeCounterDe(ClaseCombate otra) {
        return otra instanceof Cazador;
    }
}
