package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerFlashbackEleccion implements Initializable {
    @FXML private HBox botones;
    @FXML private Button boton1;
    @FXML private Button boton2;
    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    String textoAlerta = "";

    @FXML private void noAyudar(ActionEvent event) {
        textoAlerta = dao.obtenerTextoPeeta(6);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Decisión");
        alerta.setHeaderText(null);
        alerta.setContentText("Decides no ayudar a la chica, Katniss recordará tu elección");
        alerta.showAndWait();
        cambiarDeVista();
    }

    @FXML private void ayudar(ActionEvent event) {
        textoAlerta = dao.obtenerTextoPeeta(7);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Decisión");
        alerta.setHeaderText(null);
        alerta.setContentText(textoAlerta);
        alerta.showAndWait();
        cambiarDeVista();
    }

    private void cambiarDeVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/DiaCosecha.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) botones.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String texto1 = dao.obtenerTextoPeeta(4);
        String texto2 = dao.obtenerTextoPeeta(5);
        boton1.setText(texto1);
        boton2.setText(texto2);
    }
}


