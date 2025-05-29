package juego.controladoresIntro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import juego.historiaKatniss.controlador.Cjuego;

import java.io.IOException;

public class ControllerSeleccionPersonaje {

    @FXML private void iniciarHistoriaEliot(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap1/historiaEliotCap1-0.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
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

    @FXML public void iniciarHistoriaKatniss(ActionEvent actionEvent) {
        Cjuego juego = new Cjuego();
        juego.iniciarJuego();
    }

    @FXML private void iniciarHistoriaPeeta(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/Flashback.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
