package juego.controladoresIntro;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerIntro implements Initializable {

    @FXML private Label fraseLabel;
    private final List<String> frases = List.of(
            "Del tratado de la Traición.",
            "Un castigo por la rebelión, cada distrito ofrecerá un varón\n" +
                    " y una mujer de entre 12 y 18 años en una `Cosecha´ pública.",
            "Dichos tributos serán entregados a la custodia del Capitolio.",
            "Para ser transferidos a una arena pública donde pelearán a muerte,\n" +
                    " hasta que sólo prevalezca un ganador.",
            "Desde ese momento y para siempre, esta festividad será conocida como ",
            "Los Juegos del Hambre"
    );

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        Platform.runLater(() -> {
            fraseLabel.getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if(e.getCode() == KeyCode.ENTER) {
                    if(seq != null) seq.stop();
                    irASeleccionarPersonaje();
                }
            });
        });
        mostrarFrases(0);
    }
    private SequentialTransition seq;
    private void mostrarFrases(int index){
        if(index >= frases.size()) {
            irASeleccionarPersonaje();
            return;
        }
        fraseLabel.setText(frases.get(index));
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5),  fraseLabel);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        seq = new  SequentialTransition(fadeIn, pause,  fadeOut);
        seq.setOnFinished(e -> {
            mostrarFrases(index + 1);
        });
        seq.play();
    }
    private void irASeleccionarPersonaje() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/intro/seleccion_personaje.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fraseLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 1221, 720));
        } catch(Exception ex){
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la vista de elección de personajes");
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
