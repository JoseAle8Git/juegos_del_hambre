package juego.historiaEliot.controladores.cap2.bosque;

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
import juego.historiaEliot.controladores.cap2.ControladorCapitulo2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHistoriaEliotCap2_Bosque3FinalA implements Initializable {

    private ControladorCapitulo2 control = ControladorCapitulo2.obtenerObjeto().getControl();

    @FXML
    private Label fraseLabel;
    private final List<String> frases = List.of(
            "Miras a tu padre por encima del hombro y te retiras."
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarFrases(0);
    }

    private void mostrarFrases(int index) {
        if(index >= frases.size()){
            siguienteInteraccion();
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
            mostrarFrases(index + 1);
        });
        seq.play();
    }
    private void siguienteInteraccion() {
        control.setBosque(true);
        if(control.getBosque() && control.getDispensario() && control.getClaro()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap3/historiaEliotCap3-0.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) fraseLabel.getScene().getWindow();
                stage.setScene(new Scene(root, 1221, 720));
            } catch(Exception ex){
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista historiaEliotCap2-0");
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/historiaEliotCap2-eleccionCamino.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) fraseLabel.getScene().getWindow();
                stage.setScene(new Scene(root, 1221, 720));
            } catch(Exception ex){
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista historiaEliotCap2-0");
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
