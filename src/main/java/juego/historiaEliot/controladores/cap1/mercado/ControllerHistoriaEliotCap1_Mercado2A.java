package juego.historiaEliot.controladores.cap1.mercado;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.mas.DialogoDAO;

public class ControllerHistoriaEliotCap1_Mercado2A {

    @FXML Label fraseLabel;

    @FXML private void irASegundaInteraccion(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap1/mercado/historiaEliotCap1-Mercado2RespuestaA.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }private void iniciarEscena() {
        DialogoDAO dialogoDAO = new DialogoDAO();
        String texto = dialogoDAO.obtenerTexto(1);
        escribirTextoConMaquina(texto, fraseLabel, null);
    }
    private void escribirTextoConMaquina(String texto, Label destino, Runnable onFinish) {
        fraseLabel.setText("");
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
