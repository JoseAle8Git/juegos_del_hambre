package juego.historiaPeeta.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFinalTren implements Initializable {
    @FXML private Label texto;
    DialogoPeetaDAO dao = new DialogoPeetaDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(23));
    }

    private void cambiarDeVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
