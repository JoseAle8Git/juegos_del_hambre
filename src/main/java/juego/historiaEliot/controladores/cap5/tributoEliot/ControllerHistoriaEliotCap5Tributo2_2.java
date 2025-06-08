package juego.historiaEliot.controladores.cap5.tributoEliot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoEliot.controlJuegosDelHambre.ControlJuegosDelHambre;
import juego.historiaEliot.mas.DialogoDAO;

public class ControllerHistoriaEliotCap5Tributo2_2 {

    @FXML private Button opcionA;
    @FXML private Button opcionB;
    private DialogoDAO dialogoDAO;
    ControlJuegosDelHambre controlJuegosDelHambre;

    public void initialize() {
        dialogoDAO = new DialogoDAO();
        opcionA.setText(dialogoDAO.obtenerTextobtn(203));
        opcionB.setText(dialogoDAO.obtenerTextobtn(204));
    }

    @FXML private void irAOpcionA(ActionEvent e) {
        controlJuegosDelHambre = ControlJuegosDelHambre.getInstancia();
        controlJuegosDelHambre.setProbTrampa(10);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo2-2A.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }
    @FXML private void irAOpcionB(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo2-2B.fxml"));
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
