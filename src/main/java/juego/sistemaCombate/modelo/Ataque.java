package juego.sistemaCombate.modelo;

/* Esta clase representa lo que un ataque puede hacer: su nombre,
*  daño, probabilidad de acierto y efecto especial */

public class Ataque {

    private String nombre;
    private int danoBase;
    private double probAcierto;
    private EfectoEspecial efecto;

    public Ataque(String nombre, int danoBase, double probAcierto, EfectoEspecial efecto) {
        this.nombre = nombre;
        this.danoBase = danoBase;
        this.probAcierto = probAcierto;
        this.efecto = efecto;
    }

    // Getters
    public int getDanoBase() {
        return danoBase;
    }

    public double getProbAcierto() {
        return probAcierto;
    }

    public EfectoEspecial getEfecto() {
        return efecto;
    }

    public String getNombre() {
        return nombre;
    }

    // Calcula el daño final
    public int calcularDano(boolean esCritico, double modificadorCondiciones) {
        double dano = danoBase * modificadorCondiciones;
        if (esCritico) {
            dano *= 1.5;
        }
        return (int) Math.round(dano);
    }
}
