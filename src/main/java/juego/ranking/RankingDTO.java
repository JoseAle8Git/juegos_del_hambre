package juego.ranking;

import javafx.beans.property.*;

public class RankingDTO {

    private final SimpleIntegerProperty posicion;
    private final SimpleStringProperty nombreUsuario;
    private final SimpleStringProperty personaje;
    private final SimpleStringProperty finalJuego;
    private final SimpleIntegerProperty combatesGanados;
    private final SimpleIntegerProperty puntos;

    public RankingDTO(int posicion, String nombreUsuario, String personaje, String finalJuego, int combatesGanados, int puntos) {
        this.posicion = new SimpleIntegerProperty(posicion);
        this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
        this.personaje = new SimpleStringProperty(personaje);
        this.finalJuego = new SimpleStringProperty(finalJuego);
        this.combatesGanados = new SimpleIntegerProperty(combatesGanados);
        this.puntos = new SimpleIntegerProperty(puntos);
    }

    public int getPosicion() { return posicion.get(); }
    public String getNombreUsuario() { return nombreUsuario.get(); }
    public String getPersonaje() { return personaje.get(); }
    public String getFinalJuego() { return finalJuego.get(); }
    public int getCombatesGanados() { return combatesGanados.get(); }
    public int getPuntos() { return puntos.get(); }

}
