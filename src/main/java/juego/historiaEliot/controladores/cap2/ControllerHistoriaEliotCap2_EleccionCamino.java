package juego.historiaEliot.controladores.cap2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ControllerHistoriaEliotCap2_EleccionCamino {

    private ControladorCapitulo2 control = ControladorCapitulo2.obtenerObjeto().getControl();

    @FXML
    private void irAElClaro(ActionEvent e) {
        try {
            if(!control.getClaro()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/claro/historiaEliotCap2-Claro.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/claro/historiaEliotCap2-ClaroNO.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }
    @FXML
    private void irAElDispensario(ActionEvent e) {
        try {
            if(!control.getDispensario()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/dispensario/historiaEliotCap2-Dispensario.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/dispensario/historiaEliotCap2-DispensarioNO.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }
    @FXML
    private void irAElBosque(ActionEvent e) {
        try {
            if(!control.getBosque()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/bosque/historiaEliotCap2-Bosque.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/bosque/historiaEliotCap2-BosqueNO.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
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

}
