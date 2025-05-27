package juego.historiaEliot.controladores.cap2;

/* Controla la lógica de los caminos del Capítulo 2 */

public class ControladorCapitulo2 {

    private static ControladorCapitulo2 control;
    private boolean bosque;
    private boolean claro;
    private boolean dispensario;

    private ControladorCapitulo2() {
        this.bosque = false;
        this.claro = false;
        this.dispensario = false;
    }

    public static ControladorCapitulo2 obtenerObjeto() {
        if(control == null) control = new ControladorCapitulo2();
        return control;
    }
    public ControladorCapitulo2 getControl() {
        return control;
    }

    public boolean getBosque() {
        return bosque;
    }
    public void setBosque(boolean bosque) {
        this.bosque = bosque;
    }
    public boolean getClaro() {
        return claro;
    }
    public void setClaro(boolean claro) {
        this.claro = claro;
    }
    public boolean getDispensario() {
        return dispensario;
    }
    public void setDispensario(boolean dispensario) {
        this.dispensario = dispensario;
    }

}
