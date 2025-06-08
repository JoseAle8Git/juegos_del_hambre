package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;

public class ControllerSalaCamaras2 {

    @FXML
    TextField contrasena;
    JuegoSalvarASam juegoSalvarASam;

    @FXML private void enviar(ActionEvent e) {
        String verificar = "granJuego";
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        if(contrasena.getText().equals(verificar)){
            juegoSalvarASam.setCamarasDesactivadas(true);
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
        } {
            if(!juegoSalvarASam.getGuardiaVivo1()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaCamaras3.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else {
                mostrarAlerta("Error", "Contraseña inválida");
            }
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
