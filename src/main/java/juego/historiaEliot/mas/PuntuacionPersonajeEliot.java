package juego.historiaEliot.mas;

public class PuntuacionPersonajeEliot {

    private static PuntuacionPersonajeEliot instancia;

    private int puntos;

    private PuntuacionPersonajeEliot() {
        this.puntos = 0;
    }

    public static PuntuacionPersonajeEliot getPuntuacionPersonajeEliot() {
        if(instancia == null) instancia = new PuntuacionPersonajeEliot();
        return instancia;
    }

    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

}
