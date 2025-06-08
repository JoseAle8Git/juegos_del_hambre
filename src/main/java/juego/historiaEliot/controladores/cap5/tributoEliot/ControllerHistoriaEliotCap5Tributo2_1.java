package juego.historiaEliot.controladores.cap5.tributoEliot;

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

public class ControllerHistoriaEliotCap5Tributo2_1 implements Initializable {

    @FXML
    private Label fraseLabel;
    private final List<String> frases = List.of(
            "Junto a Luna os subís carruaje, ambos luciendo espectaculares en vuestros trajes.",
            "Los caballos comienzan a moverse, y las luces del Capitolio iluminan el recorrido.",
            "El público está entusiasmado, gritando y vitoreando a los tributos mientras los carruajes avanzan lentamente por la avenida central.",
            "La tensión y el espectáculo son palpables, y sabes que este es tu momento para tomar decisiones importantes que definirán cómo serás percibido."
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostraraFrases(0);
    }
    private void mostraraFrases(int index){
        if(index >= frases.size()){
            irASiguienteVista();
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

    private void irASiguienteVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo2-2.fxml"));
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
