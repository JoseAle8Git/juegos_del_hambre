package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCaraOCruzVictoriaEleccion implements Initializable {

    DialogoPeetaDAO dao = new DialogoPeetaDAO();

    @FXML private Label texto;
    @FXML private Button boton1;
    @FXML private Button boton2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(16));
        boton1.setText(dao.obtenerTextoPeeta(17));
        boton2.setText(dao.obtenerTextoPeeta(18));
    }

    @FXML private void confiar(ActionEvent event) {
        cambiarVista("/view/historiaPeeta/MinijuegoLlave.fxml");
    }

    @FXML private void desconfiar(ActionEvent event) {
        cambiarVista("/view/historiaPeeta/EleccionTributo2.fxml");
    }

    private void cambiarVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
