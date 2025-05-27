package juego.sistemaCombate.modelo;

/* Representa al rol de combate de un personaje: guerrero. */

public class Guerrero extends ClaseCombate {

    public Guerrero() {
        super("Guerrero", 150, 0.25);
        ataques.add(new Ataque("Espada feroz", 30, 0.90, EfectoEspecial.BAJAR_DEFENSA_USUARIO));
        ataques.add(new Ataque("Golpe de escudo", 15, 0.80, EfectoEspecial.PERDER_TURNO));
        ataques.add(new Ataque("Carga salvaje", 25, 0.65, EfectoEspecial.BUFF_PROXIMO_ATAQUE));
        ataques.add(new Ataque("Rugido de guerra", 0, 1.00, EfectoEspecial.REDUCIR_ESCUDO_RIVAL));
    }

    @Override
    public boolean haceCounterA(ClaseCombate otra) {
        return otra instanceof Normal || otra instanceof Cazador;
    }

    @Override
    public boolean recibeCounterDe(ClaseCombate otra) {
        return otra instanceof Arquero;
    }
}
