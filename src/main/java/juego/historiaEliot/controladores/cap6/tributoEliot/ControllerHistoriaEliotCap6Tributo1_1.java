package juego.historiaEliot.controladores.cap6.tributoEliot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoEliot.controlJuegosDelHambre.ControlJuegosDelHambre;

public class ControllerHistoriaEliotCap6Tributo1_1 {

    @FXML private Button btnJugar;
    @FXML private Button btnSi;
    @FXML private Button btnNo;

    ControlJuegosDelHambre  controlJuegosDelHambre;

    @FXML public void initialize() {
        controlJuegosDelHambre = ControlJuegosDelHambre.getInstancia();
        btnJugar.setDisable(true);
    }

    @FXML private void hacerAlianza(ActionEvent e) {
        controlJuegosDelHambre.setLunaAliada(true);
        btnJugar.setDisable(false);
    }
    @FXML private void noHacerAlianza(ActionEvent e) {
        controlJuegosDelHambre.setLunaAliada(false);
        btnJugar.setDisable(false);
    }
    @FXML private void jugar(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoEliot/juegosDelHambre/mapaJuegosDelHambre.fxml"));
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
