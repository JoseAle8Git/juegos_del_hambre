package juego.historiaEliot.controladores.cap1.puzlesPrueba;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class ControllerPuzlePrueba2 {

    @FXML private VBox reglasBox;
    @FXML private VBox juegoBox;
    @FXML private Pane zonaJuego;
    @FXML private Button continuarButton;
    @FXML private Label estadoLabel;

    private final int TOTAL_BOTONES = 25;
    private final double TIEMPO_LIMITE = 1.0; // ahora 1 segundo
    private int turnoActual = 0;
    private int aciertos = 0;
    private Random random = new Random();
    private boolean esperandoClick = false;
    private boolean esBotonCorrecto;
    private PauseTransition temporizadorBoton;

    @FXML
    public void initialize() {
        mostrarReglas();
    }

    private void mostrarReglas() {
        reglasBox.setVisible(true);
        reglasBox.setManaged(true);
        juegoBox.setVisible(false);
        juegoBox.setManaged(false);
        continuarButton.setVisible(false);
        estadoLabel.setText("");
    }

    @FXML
    private void empezarJuego() {
        reglasBox.setVisible(false);
        reglasBox.setManaged(false);
        juegoBox.setVisible(true);
        juegoBox.setManaged(true);

        turnoActual = 0;
        aciertos = 0;
        siguienteBoton();
    }

    private void siguienteBoton() {
        if (turnoActual >= TOTAL_BOTONES) {
            estadoLabel.setText("¡Has ganado!");
            continuarButton.setVisible(true);
            return;
        }

        zonaJuego.getChildren().clear();
        esperandoClick = true;

        Button boton = new Button();
        boton.setMinSize(60, 60);
        boton.setMaxSize(60, 60);

        double x = random.nextDouble() * (zonaJuego.getWidth() - 60);
        double y = random.nextDouble() * (zonaJuego.getHeight() - 60);
        boton.setLayoutX(x);
        boton.setLayoutY(y);

        esBotonCorrecto = random.nextBoolean();

        if (esBotonCorrecto) {
            boton.getStyleClass().add("boton-circular");
        } else {
            boton.getStyleClass().add("boton-cuadrado");
        }

        boton.setOnAction(e -> manejarClick());
        zonaJuego.getChildren().add(boton);

        temporizadorBoton = new PauseTransition(Duration.seconds(TIEMPO_LIMITE));
        temporizadorBoton.setOnFinished(e -> {
            if (esperandoClick) {
                if (esBotonCorrecto) {
                    perder();
                } else {
                    esperandoClick = false;
                    turnoActual++;
                    siguienteBoton();
                }
            }
        });
        temporizadorBoton.play();
    }

    private void manejarClick() {
        if (!esperandoClick) return;

        esperandoClick = false;
        temporizadorBoton.stop();

        if (esBotonCorrecto) {
            aciertos++;
            turnoActual++;
            siguienteBoton();
        } else {
            perder();
        }
    }

    private void perder() {
        esperandoClick = false;
        if (temporizadorBoton != null) temporizadorBoton.stop();

        estadoLabel.setText("¡Has perdido! Volviendo a las reglas...");
        zonaJuego.getChildren().clear();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> mostrarReglas());
        delay.play();
    }

    @FXML
    private void continuarJuego() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/historiaEliotCap2-0.fxml"));
            Scene nuevaEscena = new Scene(loader.load(), 1221, 720);
            Stage stage = (Stage) continuarButton.getScene().getWindow();
            stage.setScene(nuevaEscena);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
