package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ControllerRoperoListo {

    @FXML private void salir(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/historiaEliotMapa.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
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
