package juego.historiaEliot.controladores.cap5.tributoEliot;

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

public class ControllerHistoriaEliotCap5Tributo1_7 {

    @FXML
    Label fraseLabel;
    @FXML
    Button opcionA;
    @FXML
    Button opcionB;
    @FXML
    Button opcionC;

    @FXML private void irAOpcionA(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo1-7A.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }
    @FXML private void irAOpcionB(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo1-7B.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }
    @FXML private void irAOpcionC(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap5/tributoEliot/historiaEliotCap5Tributo1-7C.fxml"));
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
        String texto = dialogoDAO.obtenerTexto(189);
        String btnTextoA = dialogoDAO.obtenerTextobtn(198);
        String btnTextoB = dialogoDAO.obtenerTextobtn(199);
        String btnTextoC = dialogoDAO.obtenerTextobtn(200);
        opcionA.setText(btnTextoA);
        opcionB.setText(btnTextoB);
        opcionC.setText(btnTextoC);
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
