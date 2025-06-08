package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;
import juego.historiaEliot.mas.DialogoDAO;

public class ControllerSalaCamaras1 {

    @FXML
    Label fraseLabel;
    @FXML
    ImageView imagenPersonaje;
    JuegoSalvarASam juegoSalvarASam;

    @FXML private void irASiguienteInteraccion(ActionEvent e) {
        if(!juegoSalvarASam.getTrajeOficial()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoSam/salvarASam/combateSalaCamaras.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoSam/salvarASam/salaCamaras2.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
        }
    }
    private void iniciarEscena() {
        if(!juegoSalvarASam.getTrajeOficial()) {
            DialogoDAO dialogoDAO = new DialogoDAO();
            String texto = dialogoDAO.obtenerTexto(166);
            escribirTextoConMaquina(texto, fraseLabel, null);
        } else {
            DialogoDAO dialogoDAO = new DialogoDAO();
            String texto = dialogoDAO.obtenerTexto(164);
            escribirTextoConMaquina(texto, fraseLabel, null);
        }
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
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        if(!juegoSalvarASam.getTrajeOficial()) {
            imagenPersonaje.setImage(new Image(getClass().getResourceAsStream("/images/personajeSoldado.png")));
        } else {
            imagenPersonaje.setImage(new Image(getClass().getResourceAsStream("/images/eliotGuardia.png")));
        }
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
