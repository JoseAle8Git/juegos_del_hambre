package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;
import juego.historiaPeeta.mas.Peeta;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEleccionClase implements Initializable {
    DialogoPeetaDAO dao = new DialogoPeetaDAO();

    @FXML private Label texto;

    @FXML private void soldado(ActionEvent actionEvent) {
        cambiarClase("soldado");
    }
    @FXML private void guerrero(ActionEvent actionEvent) {
        cambiarClase("guerrero");
    }
    @FXML private void arquero(ActionEvent actionEvent) {
        cambiarClase("arquero");
    }
    @FXML private void cazador(ActionEvent actionEvent) {
        cambiarClase("cazador");
    }
    @FXML private void asesino(ActionEvent actionEvent) {
        cambiarClase("asesino");
    }

    private void cambiarClase(String clase) {
        Peeta.crearInstancia();
        Peeta peeta = Peeta.getInstancia();
        peeta.setClase(clase);
        alerta("Has elegido la clase " + clase);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(29));
    }
    private void alerta(String frase) {
        int vida = Peeta.getInstancia().getVidaActual();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(frase);
        alerta.showAndWait();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/InicioJuegos.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
