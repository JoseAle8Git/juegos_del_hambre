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
import juego.historiaEliot.controladores.cap5.tributoEliot.ControlCap5.ControlCap5;
import juego.historiaEliot.mas.DialogoDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHistoriaEliotCap5Tributo2_3B implements Initializable {

    ControlCap5 controlCap5;;
    @FXML
    private Label fraseLabel;
    DialogoDAO dialogoDAO;
    private List<String> frases;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialogoDAO = new DialogoDAO();
        frases = List.of(dialogoDAO.obtenerTexto(206));
        controlCap5 = ControlCap5.getInstancia();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo2-4.fxml"));
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
