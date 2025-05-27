package juego.historiaEliot.controladores.cap2.claro;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.controladores.cap2.ControladorCapitulo2;
import juego.sistemaCombate.dao.PersonajeDAO;
import juego.sistemaCombate.logica.Combate;
import juego.sistemaCombate.logica.CondicionesCombateConstructora;
import juego.sistemaCombate.modelo.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerHistoriaEliotCap2_ClaroCombateEliot {

    @FXML private ImageView imagenJugador;
    @FXML private ImageView imagenEnemigo;
    @FXML private Label nombreJugador;
    @FXML private Label nombreEnemigo;
    @FXML private ProgressBar barraVidaJugador;
    @FXML private ProgressBar barraVidaEnemigo;
    @FXML private Label vidaJugadorTexto;
    @FXML private Label vidaEnemigoTexto;
    @FXML private Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4, btnInventario;
    @FXML private AnchorPane panelInventario;
    @FXML private Button btnUsarVendas, btnUsarCarne, btnUsarHojas;
    @FXML private Label mensajeCombate;
    @FXML private Label labelTerreno, labelClima, labelMomento;

    private Jugador jugador;
    private Enemigo enemigo;
    private Combate combate;

    @FXML
    public void initialize() {
        jugador = (Jugador) new PersonajeDAO().cargarPersonajePorNombre("Eliot");
        enemigo = (Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Ray");
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
            try {

            } catch(Exception ex) {

            }
            return;
        }

        PauseTransition espera = new PauseTransition(Duration.seconds(3));
        espera.setOnFinished(e -> ejecutarTurnoEnemigo());
        espera.play();
    }

    private void ejecutarTurnoEnemigo() {
        // El enemigo decide si usar un objeto y se muestra mensaje
        combate.decidirYUsarObjetosIA(mensajeCombate);

        // Esperamos 1.5 segundos para mostrar el mensaje del uso del objeto
        PauseTransition esperaUso = new PauseTransition(Duration.seconds(1.5));
        esperaUso.setOnFinished(e1 -> {
            Ataque ataque = enemigo.elegirAtaque();
            boolean impacto = combate.aplicarAtaque(enemigo, jugador, ataque);
            mensajeCombate.setText(enemigo.getNombre() + " usa " + ataque.getNombre() + (impacto ? " ¡Impacta!" : " ¡Falla!"));

            actualizarVida();

            if (!jugador.estaVivo()) {
                mostrarResultado("Has sido derrotado.");
                try {

                } catch(Exception ex) {

                }
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
            actualizarVida();
        } else {
            mensajeCombate.setText("No te quedan vendas");
        }
    }

    @FXML private void usarCarne() {
        if (!jugador.haConsumidoCarneEsteTurno()) {
            if (jugador.getInventario().usarObjeto(TipoObjeto.CARNE)) {
                jugador.setConsumidoCarneEsteTurno(true);
                mensajeCombate.setText("Comiste carne: aumenta acierto");
            } else {
                mensajeCombate.setText("No te queda carne");
            }
        } else {
            mensajeCombate.setText("Ya has comido carne este turno");
        }
    }

    @FXML private void usarHojas() {
        if (!jugador.haConsumidoHojaEsteTurno()) {
            if (jugador.getInventario().usarObjeto(TipoObjeto.HOJA)) {
                jugador.setConsumidoHojaEsteTurno(true);
                mensajeCombate.setText("Has usado hojas: aumenta defensa");
            } else {
                mensajeCombate.setText("No te quedan hojas");
            }
        } else {
            mensajeCombate.setText("Ya has usado hoja este turno");
        }
    }

    private void mostrarResultado(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado del combate");
        alert.setHeaderText(null);
        alert.setContentText(texto);

        alert.setOnHidden(e -> {
            if (texto.contains("derrotado")) {
                reiniciarCombate(); // Método que debes crear para reiniciar la escena
            } else {
                irASiguienteVista(); // Método que debes crear para cambiar a la siguiente
            }
        });

        alert.show();
    }

    private void reiniciarCombate() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/claro/historiaEliotCap2-ClaroCombateEliot.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) mensajeCombate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }

    private void irASiguienteVista() {
        ControladorCapitulo2 control = ControladorCapitulo2.obtenerObjeto().getControl();
        control.setClaro(true);
        if(control.getClaro() && control.getBosque() && control.getDispensario()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap3/historiaEliotCap3-0.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) mensajeCombate.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/historiaEliotCap2-eleccionCamino.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) mensajeCombate.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
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
