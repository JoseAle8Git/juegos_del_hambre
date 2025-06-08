package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;
import juego.historiaPeeta.mas.Peeta;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEleccionKatniss implements Initializable {

    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @FXML private Label texto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(34));
    }
    @FXML private void peetaKatniss(ActionEvent event) {
        irASiguienteVista("/view/historiaPeeta/FinalPeetaYKatniss.fxml");
    }
    @FXML private void traicion(ActionEvent event) {
        irASiguienteVista("/view/historiaPeeta/FinalPeeta.fxml");
    }
    private void irASiguienteVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent root = loader.load();
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
