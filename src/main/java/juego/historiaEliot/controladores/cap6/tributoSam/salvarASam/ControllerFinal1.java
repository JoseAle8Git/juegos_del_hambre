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

public class ControllerFinal1 implements Initializable {

    @FXML
    private Label fraseLabel;
    private final List<String> frases = List.of(
            "Una vez escapado del Capitolio con tu hermano Sam lograsteis salir del Panem dirigiendoos hacia el Sur.",
            "Una vez llegasteis a salir del País pudisteis llevar una vida normal en otro país.",
            "En Panem esta escapada solo cabreó al Capitolio y provocó más dureza en el país, ya que esta situación dejó en ridículo al Capitolio y al presidente Snow.",
            "Ya de grandes trabajas junto a tu hermano para intentar arreglar la situación de Panem desde las Nacionaes Unidas y ...",
            "Fin"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostraraFrases(0);
    }
    private void mostraraFrases(int index){
        if(index >= frases.size()){
            irAFinal2();
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

    private void irAFinal2() {
        InsertarRanking ranking = InsertarRanking.crearInstancia();
        ranking.setFinalJuego("Escapar del Capitolio junto con tu hermano Sam");
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
