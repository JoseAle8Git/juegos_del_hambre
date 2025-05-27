package juego.historiaEliot.mas;

public class Dialogo {

    private int id;
    private String personaje;
    private int capitulo;
    private int escena;
    private String tipo;
    private String texto;
    private String btnTexto;

    public Dialogo(int id, String personaje, int capitulo, int escena, String tipo, String texto,  String btnTexto) {
        this.id = id;
        this.personaje = personaje;
        this.capitulo = capitulo;
        this.escena = escena;
        this.tipo = tipo;
        this.texto = texto;
        this.btnTexto = btnTexto;
    }

    public int getId() { return id; }
    public String getPersonaje() { return personaje; }
    public int getCapitulo() { return capitulo; }
    public int getEscena() { return escena; }
    public String getTipo() { return tipo; }
    public String getTexto() { return texto; }
    public String getBtnTexto() { return btnTexto; }

    public void setId(int id) { this.id = id; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }
    public void setCapitulo(int capitulo) { this.capitulo = capitulo; }
    public void setEscena(int escena) { this.escena = escena; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setTexto(String texto) { this.texto = texto; }
    public void setBtnTexto(String btnTexto) { this.btnTexto = btnTexto; }

}
