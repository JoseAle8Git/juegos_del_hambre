package juego.historiaPeeta.controladores;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCaraOCruzDerrota implements Initializable {

    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @FXML private Label texto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(24));
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> cambiarDeVista());
        delay.play();
    }

    private void cambiarDeVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/EleccionTributo1.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
