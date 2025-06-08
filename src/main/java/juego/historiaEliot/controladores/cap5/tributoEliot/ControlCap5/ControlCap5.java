package juego.historiaEliot.controladores.cap5.tributoEliot.ControlCap5;

public class ControlCap5 {

    private static ControlCap5 instancia;

    private boolean trajeFuego;

    private ControlCap5() {
        this.trajeFuego = false;
    }

    public static ControlCap5 getInstancia() {
        if (instancia == null) {
            instancia = new ControlCap5();
        }
        return instancia;
    }

    public boolean getTrajeFuego() {
        return trajeFuego;
    }
    public void setTrajeFuego(boolean trajeFuego) {
        this.trajeFuego = trajeFuego;
    }

}
