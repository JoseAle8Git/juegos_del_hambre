package juego.historiaEliot.controladores.cap1.mercado;

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

public class ControllerHistoriaEliotCap1_Mercado1 implements Initializable {

    @FXML Label fraseLabel;
    private final List<String> frases = List.of(
            "Decides dirigirte al mercado. Al llegar, ves a tu padre discutiendo el precio de unas papas con un vendedor. Cuando lo ves, tu padre se despide del comerciante y se vuelve hacia ti con una sonrisa cansada."
    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarFrases(0);
    }
    private void mostrarFrases(int index) {
        if(index >= frases.size()){
            irAHistoriaEliotCap1Mercado2();
            return;
        }
        fraseLabel.setText(frases.get(index));
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        SequentialTransition seq = new SequentialTransition(fadeIn, pause, fadeOut);
        seq.setOnFinished(e -> {
            mostrarFrases(index + 1);
        });
        seq.play();
    }
    private void irAHistoriaEliotCap1Mercado2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap1/mercado/historiaEliotCap1-Mercado2.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fraseLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 1221, 720));
        } catch(Exception ex){
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista historiaEliotCap1-2");
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
