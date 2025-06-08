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

public class ControllerCaminoAJuegos implements Initializable {

    @FXML private Label texto;
    DialogoPeetaDAO dao = new DialogoPeetaDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(27));
        PauseTransition pause = new PauseTransition(Duration.seconds(6));
        pause.setOnFinished(event -> cambiarVista());
        pause.play();
    }
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/PrevioAJuegos.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
