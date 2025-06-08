package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

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

public class ControllerFinal2 implements Initializable {

    @FXML
    private Label fraseLabel;
    private final List<String> frases = List.of(
            "Una vez escapado del Capitolio junto a Sam y Luna os dirigisteis hacia el Norte llegando hacia un Distrito nuevo.",
            "Este Distrito es el 13, que se pensaba que no existía. Os unís a este Distrito que es un sector de resistencia contra el Capitolio.",
            "Unos años después creas una familia junto con Luna a la par que luchas junto con Sam contra el Capitolio liderando la resistencia.",
            "Fin"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostraraFrases(0);
    }
    private void mostraraFrases(int index){
        if(index >= frases.size()){
            irAFinal1();
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

    private void irAFinal1() {
        InsertarRanking ranking = InsertarRanking.crearInstancia();
        ranking.setFinalJuego("Escapas del Capitolio, creas una familia con Luna y lideras la resistencia junto con Sam contra el Capitolio");
        RankingDAO.insertarRanking(ranking);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) fraseLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
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
