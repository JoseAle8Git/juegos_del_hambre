package juego.historiaPeeta.controladores;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFlashback implements Initializable {

    @FXML private VBox caja;
    @FXML private StackPane contenedor;
    @FXML private Label titulo;
    @FXML private Label texto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DialogoPeetaDAO dao = new DialogoPeetaDAO();
        String texto1 = dao.obtenerTextoPeeta(2);
        String texto2 = dao.obtenerTextoPeeta(3);
        titulo.setText(texto1);
        texto.setText(texto2);
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> cambiarDeVista());
        delay.play();
    }

    private void cambiarDeVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/FlashbackEleccion.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) caja.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
