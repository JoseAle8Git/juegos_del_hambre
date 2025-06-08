package juego.historiaEliot.controladores.cap6.tributoEliot.juegosDelHambre;

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
import juego.ranking.InsertarRanking;
import juego.ranking.RankingDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerFinal2JuegosDelHambre implements Initializable {

    @FXML
    private Label fraseLabel;
    private InsertarRanking ranking;

    private final List<String> frases = List.of(
            "Ganas los Juegos del Hambre junto con Luna.",
            "Tuviste una participación buena en los juegos y tomas tu sitio junto a los demás tributos.",
            "En el futuro te dedicas junto a Heymitch a inspirar a los futuros tributos.",
            "Fin"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ranking = InsertarRanking.crearInstancia();
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
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
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
        ranking.setFinalJuego("Ganas los juegos del hambre y continuas tu vida sin cambiar nada");
        RankingDAO.insertarRanking(ranking);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
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
