package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;
import juego.historiaEliot.mas.TributoSam;
import juego.ranking.InsertarRanking;
import juego.ranking.RankingDAO;
import juego.sistemaCombate.dao.PersonajeDAO;
import juego.sistemaCombate.logica.Combate;
import juego.sistemaCombate.logica.CondicionesCombateConstructora;
import juego.sistemaCombate.modelo.*;

import java.util.List;

public class ControllerCombateSalaCamaras {

    @FXML
    private ImageView imagenJugador, imagenEnemigo;
    @FXML private Label nombreJugador, nombreEnemigo;
    @FXML private ProgressBar barraVidaJugador, barraVidaEnemigo;
    @FXML private Label vidaJugadorTexto, vidaEnemigoTexto;
    @FXML private Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4, btnInventario;
    @FXML private AnchorPane panelInventario;
    @FXML private Button btnUsarVendas;
    @FXML private Label mensajeCombate;
    @FXML private Label labelTerreno, labelClima, labelMomento;

    private Jugador jugador;
    private Enemigo enemigo;
    private Combate combate;

    @FXML
    public void initialize() {
        TributoSam.crearInstancia();
        TributoSam tributo = TributoSam.getInstancia();
        jugador = new Jugador(tributo.getNombre(), tributo.getClase());
        jugador.setVidaActual(tributo.getVidaActual());
        jugador.setInventario(tributo.getInventarioCombate());

        enemigo = (Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Soldado");
        CondicionesCombate condiciones = new CondicionesCombateConstructora().generarAleatorias();
        combate = new Combate(jugador, enemigo, null, condiciones);

        inicializarVista();
        mostrarCondicionesExternas(condiciones);
        mostrarInicioCombate();
    }

    private void inicializarVista() {
        imagenJugador.setImage(new Image("/images/" + jugador.getNombre().toLowerCase() + ".png"));
        imagenEnemigo.setImage(new Image("/images/" + enemigo.getNombre().toLowerCase() + ".png"));
        nombreJugador.setText(jugador.getNombre());
        nombreEnemigo.setText(enemigo.getNombre());

        List<Ataque> ataques = jugador.getClase().getAtaques();
        btnAtaque1.setText(ataques.get(0).getNombre());
        btnAtaque2.setText(ataques.get(1).getNombre());
        btnAtaque3.setText(ataques.get(2).getNombre());
        btnAtaque4.setText(ataques.get(3).getNombre());

        actualizarVida();
        panelInventario.setVisible(false);
    }

    private void mostrarCondicionesExternas(CondicionesCombate condiciones) {
        labelTerreno.setText("Terreno: " + condiciones.getTerreno());
        labelClima.setText("Clima: " + condiciones.getClima());
        labelMomento.setText("Momento: " + condiciones.getMomento());
    }

    private void mostrarInicioCombate() {
        String mensaje = (combate.isTurnoJugador() ? "¡Empieza " + jugador.getNombre() : "¡Empieza " + enemigo.getNombre()) + "!";
        mensajeCombate.setText(mensaje);
        bloquearBotones();
        PauseTransition espera = new PauseTransition(Duration.seconds(3));
        espera.setOnFinished(e -> {
            mensajeCombate.setText("");
            if (!combate.isTurnoJugador()) ejecutarTurnoEnemigo();
            else desbloquearBotones();
        });
        espera.play();
    }

    private void actualizarVida() {
        barraVidaJugador.setProgress((double) jugador.getVidaActual() / jugador.getVidaMaxima());
        barraVidaEnemigo.setProgress((double) enemigo.getVidaActual() / enemigo.getVidaMaxima());
        vidaJugadorTexto.setText(jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
        vidaEnemigoTexto.setText(enemigo.getVidaActual() + "/" + enemigo.getVidaMaxima());
    }

    private void bloquearBotones() {
        btnAtaque1.setDisable(true);
        btnAtaque2.setDisable(true);
        btnAtaque3.setDisable(true);
        btnAtaque4.setDisable(true);
        btnInventario.setDisable(true);
    }

    private void desbloquearBotones() {
        btnAtaque1.setDisable(false);
        btnAtaque2.setDisable(false);
        btnAtaque3.setDisable(false);
        btnAtaque4.setDisable(false);
        btnInventario.setDisable(false);
    }

    @FXML public void usarAtaque1() { ejecutarAtaque(0); }
    @FXML public void usarAtaque2() { ejecutarAtaque(1); }
    @FXML public void usarAtaque3() { ejecutarAtaque(2); }
    @FXML public void usarAtaque4() { ejecutarAtaque(3); }

    private void ejecutarAtaque(int index) {
        bloquearBotones();
        Ataque ataque = jugador.getClase().getAtaques().get(index);
        boolean impacto = combate.aplicarAtaque(jugador, enemigo, ataque);
        mensajeCombate.setText(jugador.getNombre() + " usa " + ataque.getNombre() + (impacto ? " ¡Impacta!" : " ¡Falla!"));

        actualizarVida();
        if (!enemigo.estaVivo()) {
            mostrarResultado("¡Has ganado el combate!");
            return;
        }

        PauseTransition espera = new PauseTransition(Duration.seconds(3));
        espera.setOnFinished(e -> ejecutarTurnoEnemigo());
        espera.play();
    }

    private void ejecutarTurnoEnemigo() {
        combate.decidirYUsarObjetosIA(mensajeCombate);

        PauseTransition esperaUso = new PauseTransition(Duration.seconds(1.5));
        esperaUso.setOnFinished(e1 -> {
            Ataque ataque = enemigo.elegirAtaque();
            boolean impacto = combate.aplicarAtaque(enemigo, jugador, ataque);
            mensajeCombate.setText(enemigo.getNombre() + " usa " + ataque.getNombre() + (impacto ? " ¡Impacta!" : " ¡Falla!"));

            actualizarVida();

            if (!jugador.estaVivo()) {
                mostrarResultado("Has sido derrotado.");
                return;
            }

            jugador.avanzarTurno();
            enemigo.avanzarTurno();
            jugador.resetConsumiblesTurno();

            PauseTransition limpiar = new PauseTransition(Duration.seconds(2));
            limpiar.setOnFinished(ev -> {
                mensajeCombate.setText("");
                desbloquearBotones();
            });
            limpiar.play();
        });
        esperaUso.play();
    }

    @FXML private void abrirInventario() { panelInventario.setVisible(true); }
    @FXML private void cerrarInventario() { panelInventario.setVisible(false); }

    @FXML private void usarVendas() {
        if (jugador.getInventario().usarObjeto(TipoObjeto.VENDA)) {
            jugador.curar(30);
            actualizarVida();
            mensajeCombate.setText("Usaste una venda");
        } else {
            mensajeCombate.setText("No te quedan vendas");
        }
    }

    private void mostrarResultado(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado del combate");
        alert.setHeaderText(null);
        alert.setContentText(texto);

        alert.setOnHidden(e -> {
            if (texto.contains("derrotado")) {
                InsertarRanking ranking = InsertarRanking.crearInstancia();
                ranking.setFinalJuego("Muerte");
                RankingDAO.insertarRanking(ranking);
                mostrarAlerta("Game over", "Has sido derrotado");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) mensajeCombate.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else {
                irASiguienteVista();
            }
        });

        alert.show();
    }

    private void irASiguienteVista() {
        InsertarRanking ranking = InsertarRanking.crearInstancia();
        ranking.crearInstancia();
        ranking.setPuntos(30);
        ranking.setCombatesGanados();
        JuegoSalvarASam juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        juegoSalvarASam.setGuardiaVivo1(true);
        TributoSam.getInstancia().setVidaActual(jugador.getVidaActual());
        TributoSam.getInstancia().setInventarioCombate(jugador.getInventario());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoSam/salvarASam/salaCamaras2.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) mensajeCombate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
