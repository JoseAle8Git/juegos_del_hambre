package juego.historiaEliot.controladores.cap2.dispensario;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.controladores.cap2.ControladorCapitulo2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHistoriaEliotCap2_Dispensario implements Initializable {

    private ControladorCapitulo2 control = ControladorCapitulo2.obtenerObjeto().getControl();

    @FXML
    private Label fraseLabel;
    private final List<String> frases = List.of(
            "Acabas de llegar al puesto de hierbas donde tu amiga, Luna, suele ayudar a la gente del distrito con remedios caseros. Luna es una joven observadora y tranquila que ha aprendido sobre plantas medicinales de su madre. En un distrito tan pobre como el 12, las hierbas locales y remedios básicos son esenciales para la supervivencia."
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        control.setDispensario(true);
        mostraraFrases(0);
    }
    private void mostraraFrases(int index){
        if(index >= frases.size()){
            irASiguienteVista();
            return;
        }
        fraseLabel.setText(frases.get(index));
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(5.5));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), fraseLabel);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        SequentialTransition seq = new SequentialTransition(fadeIn, pause, fadeOut);
        seq.setOnFinished(e -> {
            mostraraFrases(index + 1);
        });
        seq.play();
    }

    private void irASiguienteVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap2/dispensario/historiaEliotCap2-Dispensario1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fraseLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 1221, 720));
        } catch(Exception ex){
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
