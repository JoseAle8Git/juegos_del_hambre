package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPeetaInicio implements Initializable {
    @FXML private VBox caja;
    @FXML private Label texto;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DialogoPeetaDAO dao = new DialogoPeetaDAO();
        String frase = dao.obtenerTextoPeeta(1); // suponiendo que el id 1 es el que quieres mostrar
        texto.setText(frase);
    }

    @FXML private void empezarPeeta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/Flashback.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) caja.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
