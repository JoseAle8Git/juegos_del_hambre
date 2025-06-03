package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGuardia implements Initializable {
    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @FXML private HBox caja;
    @FXML private Label texto1;
    @FXML private Label texto2;
    String textoAlerta = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String frase1 = dao.obtenerTextoPeeta(10);
        String frase2 = dao.obtenerTextoPeeta(11);
        texto1.setText(frase1);
        texto2.setText(frase2);
    }

    @FXML private void juegoGuardia(ActionEvent e) {
        textoAlerta = dao.obtenerTextoPeeta(12);
        alerta("Elección", textoAlerta);
        cambiarDeVista("/view/historiaPeeta/CaraOCruz.fxml");
    }

    @FXML private void saltarJuegoGuardia(ActionEvent e) {
        textoAlerta = dao.obtenerTextoPeeta(13);
        alerta("Elección", textoAlerta);
        cambiarDeVista("/view/historiaPeeta/DiaCosecha.fxml");
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
