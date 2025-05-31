package juego.historiaEliot.controladores.cap5.tributoSam;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.mas.DialogoDAO;

public class ControllerHistoriaEliotCap5Tributo2_0 {

    @FXML
    Label fraseLabelA;
    @FXML
    Button opcionA;

    @FXML private void irAOpcionA(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoSam/historiaEliotCap5Tributo2-1.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }
    private void iniciarEscena() {
        DialogoDAO dialogoDAO = new DialogoDAO();
        String texto = "Mientras el tren comienza a moverse, te quedas mirando por la ventana, con las luces del Distrito 12 desapareciendo en la distancia. Tu rostro está marcado por una mezcla de rabia y dolor.";
        String btnTextoA = dialogoDAO.obtenerTextobtn(156);
        opcionA.setText(btnTextoA);
        escribirTextoConMaquina(texto, fraseLabelA, null);
    }
    private void escribirTextoConMaquina(String texto, Label destino, Runnable onFinish) {
        fraseLabelA.setText("");
        Timeline timeline = new Timeline();
        StringBuilder textoActual = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            final int index = i;
            KeyFrame frame = new KeyFrame(Duration.millis(30 * index), e -> {
                textoActual.append(texto.charAt(index));
                destino.setText(textoActual.toString());
            });
            timeline.getKeyFrames().add(frame);
        }

        timeline.setOnFinished(e -> {
            if (onFinish != null) onFinish.run();
        });

        timeline.play();
    }
    @FXML public void initialize() {
        iniciarEscena();
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
