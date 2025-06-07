package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import juego.historiaPeeta.mas.DialogoPeetaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInicioJuegos implements Initializable {
    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @FXML Label texto1;
    @FXML Label texto2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto1.setText(dao.obtenerTextoPeeta(31));
        texto2.setText(dao.obtenerTextoPeeta(32));
    }

    @FXML private void arena(ActionEvent event) {

    }

    @FXML private void bosque(ActionEvent event) {

    }

    @FXML private void cueva(ActionEvent event) {

    }
}
