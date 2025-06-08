package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;

public class ControllerCuartoLlaves {

    @FXML Label texto;
    JuegoSalvarASam juegoSalvarASam;

    public void initialize(){
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
    }

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

    @FXML private void buscar() {
        juegoSalvarASam.setLlaves(true);
        texto.setText("Has encontrado las llaves que abren las habitaciones de los tributos");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
