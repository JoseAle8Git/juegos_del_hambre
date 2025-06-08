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
import juego.ranking.InsertarRanking;

import java.io.IOException;

public class ControllerSeleccionPersonaje {

    @FXML private void iniciarHistoriaEliot(ActionEvent e) {
        try {
            InsertarRanking ranking = InsertarRanking.crearInstancia();
            ranking.setPersonaje("Eliot");
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

    @FXML
    public void iniciarHistoriaKatniss(ActionEvent actionEvent) {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);

        new Thread(() -> {
            Cjuego juego = new Cjuego();
            juego.iniciarJuego();
        }).start();
    }

    @FXML private void iniciarHistoriaPeeta(ActionEvent actionEvent) {
        try {
            InsertarRanking ranking = InsertarRanking.crearInstancia();
            ranking.setPersonaje("Peeta");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/PeetaInicio.fxml"));
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/FinalKatniss.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
