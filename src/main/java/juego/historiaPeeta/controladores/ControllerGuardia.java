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

public class ControllerGuardia {

    @FXML private HBox caja;

    @FXML private void juegoGuardia(ActionEvent e) {
        alerta("Elección", "Has decidido jugar al juego");
        cambiarDeVista("/view/historiaPeeta/CaraOCruz.fxml");
    }

    @FXML private void saltarJuegoGuardia(ActionEvent e) {
        alerta("Elección", "Decides seguir e ignorar al guardia a lo que el guardia te dice que no volverás a tener una oportunidad igual");
        cambiarDeVista("/view/historiaPeeta/FlashbackEleccion.fxml");
    }
    private void cambiarDeVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) caja.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void alerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
