package juego.ranking;

public class InsertarRanking {

    private static InsertarRanking instancia;

    private String nombreUsuario;
    private String personaje;
    private String finalJuego;
    private int combatesGanados;
    private int puntos;

    private InsertarRanking() {
        this.nombreUsuario = "";
        this.personaje = "";
        this.finalJuego = "";
        this.combatesGanados = 0;
        this.puntos = 0;
    }

    public static InsertarRanking crearInstancia() {
        if(instancia == null) instancia = new InsertarRanking();
        return instancia;
    }
    public static InsertarRanking getInstancia() {
        return instancia;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public String getPersonaje() {
        return personaje;
    }
    public String getFinalJuego() {
        return finalJuego;
    }
    public int getCombatesGanados() {
        return combatesGanados;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }
    public void setFinalJuego(String finalJuego) {
        this.finalJuego = finalJuego;
    }
    public void setCombatesGanados() {
        this.combatesGanados++;
    }
    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

}
