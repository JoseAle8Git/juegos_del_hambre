package juego.historiaEliot.controladores.cap6.tributoSam;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHistoriaEliotCap6Tributo1_3 implements Initializable {

    @FXML
    private Label fraseLabel;
    private final List<String> frases = List.of(
            "Los guardias se alinean en filas mientras el general pasa revisándolos. Eliot mantiene la mirada fija hacia adelante, imitando la postura de los demás.",
            "El oficial se detiene frente a tí, su expresión severa y fría mientras revisa una lista."
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostraraFrases(0);
    }
    private void mostraraFrases(int index){
        if(index >= frases.size()){
            irAHistoriaEliotCap1_1();
            return;
        }
        fraseLabel.setText(frases.get(index));
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        SequentialTransition seq = new SequentialTransition(fadeIn, pause, fadeOut);
        seq.setOnFinished(e -> {
            mostraraFrases(index + 1);
        });
        seq.play();
    }

    private void irAHistoriaEliotCap1_1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoSam/historiaEliotCap6Tributo1-4.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fraseLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 1221, 720));
        } catch(Exception ex){
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
