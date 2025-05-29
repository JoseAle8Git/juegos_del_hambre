package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerFlashbackEleccion {
    @FXML private HBox botones;
    @FXML private void ayudar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Decisión");
        alerta.setHeaderText(null);
        alerta.setContentText("Decides ayudar a la chica, Katniss recordará tu elección");
        alerta.showAndWait();
        cambiarDeVista();
    }

    @FXML private void noAyudar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Decisión");
        alerta.setHeaderText(null);
        alerta.setContentText("Decides no ayudar a la chica, Katniss recordará tu elección");
        alerta.showAndWait();
        cambiarDeVista();
    }

    private void cambiarDeVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/Guardia.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) botones.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


