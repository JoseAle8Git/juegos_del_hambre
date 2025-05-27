package juego.sistemaCombate.modelo;

/* Esta clase define cuánta vida tiene un personaje, cuánto escudo
*  reduce el daño, qué ataques puede usar y a qué hace counter y de
*  cuáles recibe counter. */

import java.util.ArrayList;
import java.util.List;

public abstract class ClaseCombate {

    // Son protected porque sus subclases necesitan acceder a estos atributos
    protected String nombre;
    protected int vidaMaxima;
    protected double escudoPorcentaje;
    protected List<Ataque> ataques;

    public ClaseCombate(String nombre, int vidaMaxima, double escudoPorcentaje) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.escudoPorcentaje = escudoPorcentaje;
        this.ataques = new ArrayList<>();
    }

    // Esto permite que Combate pueda aplicar críticos si hay counter
    // Indica si una clase tiene ventaja sobre otra
    public abstract boolean haceCounterA(ClaseCombate otra);
    // Indica si una clase tiene desventaja sobre otra
    public abstract boolean recibeCounterDe(ClaseCombate otra);

    // Calcula cuanto se bloque del daño gracias al escudo de la clase
    public int calcularEscudo(int danoRecibido) {
        return (int) (danoRecibido * escudoPorcentaje);
    }

    // Getters y setters
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public void setAtaques(List<Ataque> ataques) {
        this.ataques = ataques;
    }

    public String getNombre() {
        return nombre;
    }

    public double getEscudoPorcentaje() {
        return escudoPorcentaje;
    }
}
