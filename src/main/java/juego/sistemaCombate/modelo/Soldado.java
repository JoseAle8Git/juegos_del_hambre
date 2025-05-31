package juego.sistemaCombate.modelo;

/* Representa al rol de combate de un personaje: soldado. */

public class Soldado extends ClaseCombate {

    public Soldado() {
        super("Soldado", 160, 0.25);
        ataques.add(new Ataque("Disparo preciso", 50, 0.75, EfectoEspecial.CRITICO_ADICIONAL));
        ataques.add(new Ataque("Granada aturdidora", 55, 0.50, EfectoEspecial.PERDER_TURNO));
        ataques.add(new Ataque("Bayoneta", 60, 0.70, EfectoEspecial.IGNORAR_ESCUDO));
        ataques.add(new Ataque("Cobertura defensiva", 0, 1.00, EfectoEspecial.DUPLICAR_ESCUDO_TEMPORAL));
    }

    @Override
    public boolean haceCounterA(ClaseCombate otra) {
        return otra.getNombre().equalsIgnoreCase("Normal");
    }

    @Override
    public boolean recibeCounterDe(ClaseCombate otra) {
        return false;
    }
}
