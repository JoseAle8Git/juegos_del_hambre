package juego.historiaEliot.controladores.cap1.puzlesPrueba;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControllerPuzlePrueba1 {

    // Nodos
    @FXML
    private Label instruccionesLabel;
    @FXML
    private Label patronLabel;
    @FXML
    private HBox secuenciaBox;
    @FXML
    private HBox opcionesBox;
    @FXML
    private Button continuarButton;
    @FXML
    private VBox juegoBox;
    @FXML
    private VBox reglasBox;
    @FXML
    private Button jugarButton;

    // Lista de letras
    private List<String> letras = List.of("A", "B", "C", "D");
    // Lista aleatoria generada
    private List<String> secuenciaCorrecta = new ArrayList<>();
    // Lista que introduce el jugador para comparar con la generada aleatoriamente
    private List<String> secuenciaUsuario = new ArrayList<>();
    // Índice para comparar posiciones de la lista
    private int indiceActual = 0;
    private Random random = new Random();

    @FXML
    public void initialize() {
        mostrarReglas();
    }

    // Muestra el panel de reglas y oculta el del juego
    private void mostrarReglas() {
        reglasBox.setOpacity(0);
        reglasBox.setVisible(true);
        reglasBox.setManaged(true);
        juegoBox.setVisible(false);
        juegoBox.setManaged(false);

        // Genera una transición suave de 0 de opacidad a 1
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), reglasBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    // Al presionar el botón jugar se ejecuta
    @FXML
    private void empezarJuego() {
        // Oculta el panel de reglas con una transición suave de 1 de opacidad hasta 0
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), reglasBox);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            reglasBox.setVisible(false);
            reglasBox.setManaged(false);
            juegoBox.setVisible(true);
            juegoBox.setManaged(true);
            juegoBox.setOpacity(0);

            FadeTransition fadeInJuego = new FadeTransition(Duration.millis(500), juegoBox);
            fadeInJuego.setFromValue(0);
            fadeInJuego.setToValue(1);
            fadeInJuego.play();

            // Activa la animación de aparición del panel de juego
            iniciarJuego();
        });
        fadeOut.play();
    }

    private void iniciarJuego() {
        // Resetea el botón de continuar, las secuencias, las cajas visuales y el índice actual
        continuarButton.setVisible(false);
        secuenciaCorrecta.clear();
        secuenciaUsuario.clear();
        secuenciaBox.getChildren().clear();
        opcionesBox.getChildren().clear();
        patronLabel.setText("");
        indiceActual = 0;

        // Genera un patrón de entre 3 y 6 caracteres
        int longitud = random.nextInt(4) + 3;
        for (int i = 0; i < longitud; i++) {
            secuenciaCorrecta.add(letras.get(random.nextInt(letras.size())));
        }

        // Muestra la secuencia al jugador
        mostrarSecuencia();
    }

    private void mostrarSecuencia() {
        // Resetea la etiqueta superior y limpia las cajas visuales
        instruccionesLabel.setText("Observa la secuencia...");
        secuenciaBox.getChildren().clear();
        patronLabel.setText("");

        int delay = 1;

        // Por cada letra generada se crea una etiqueta para mostrarña
        for (int i = 0; i < secuenciaCorrecta.size(); i++) {
            String color = secuenciaCorrecta.get(i);
            Label label = new Label(color);
            label.setVisible(false);
            secuenciaBox.getChildren().add(label);

            PauseTransition pause = new PauseTransition(Duration.seconds(i + delay));
            int finalI = i;
            pause.setOnFinished(e -> {
                secuenciaBox.getChildren().get(finalI).setVisible(true);
                patronLabel.setText(patronLabel.getText() + secuenciaCorrecta.get(finalI));
            });
            pause.play();
        }

        // Crea una pausa entre cada letra generada de un segundo
        PauseTransition espera = new PauseTransition(Duration.seconds(secuenciaCorrecta.size() + delay + 1));
        espera.setOnFinished(e -> mostrarOpciones());
        espera.play();
    }

    private void mostrarOpciones() {
        // Cambia la secuencia, limpia las cajas visuales y vacía el patrón mostrado
        instruccionesLabel.setText("Repite la secuencia en orden");
        secuenciaBox.getChildren().clear();
        opcionesBox.getChildren().clear();
        patronLabel.setText(""); // Borra la secuencia

        // Crea los botones para reproducir la secuencia
        for (String color : letras) {
            Button btn = new Button(color);
            btn.setOpacity(0); // Animación controlada por JavaFX
            btn.setOnAction(e -> manejarRespuesta(color));
            opcionesBox.getChildren().add(btn);

            // Efecto suave de aparición
            FadeTransition fadeInBtn = new FadeTransition(Duration.millis(400), btn);
            fadeInBtn.setFromValue(0);
            fadeInBtn.setToValue(1);
            fadeInBtn.play();
        }
    }

    private void manejarRespuesta(String colorSeleccionado) {
        // Añade la letra pulsada por el jugador a su secuencia
        secuenciaUsuario.add(colorSeleccionado);

        // Si el jugador se equivoca, lo informa y lo manda de nuevo a las reglas
        if (!colorSeleccionado.equals(secuenciaCorrecta.get(indiceActual))) {
            instruccionesLabel.setText("¡Fallaste! Volviendo a las reglas...");
            volverAReglasConRetraso();
            return;
        }

        indiceActual++;
        // Si el jugador acierta toda la secuencia, se muestra el botón para continuar
        if (indiceActual == secuenciaCorrecta.size()) {
            instruccionesLabel.setText("¡Correcto! Has memorizado la secuencia.");
            continuarButton.setVisible(true);
        }
    }

    // Se espera dos segundos y se vuelve a las reglas del minijuego
    private void volverAReglasConRetraso() {
        PauseTransition espera = new PauseTransition(Duration.seconds(2));
        espera.setOnFinished(e -> mostrarReglas());
        espera.play();
    }

    // Carga la siguiente vista
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
