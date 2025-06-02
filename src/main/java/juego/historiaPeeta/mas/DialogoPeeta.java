package juego.historiaPeeta.mas;

public class DialogoPeeta {
    private int id;
    private String controlador;
    private String texto;

    public DialogoPeeta(int id, String controlador, String texto) {
        this.id = id;
        this.controlador = controlador;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
