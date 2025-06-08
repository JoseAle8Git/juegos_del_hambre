package juego.historiaPeeta.controladores.finales;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaPeeta.mas.DialogoPeetaDAO;
import juego.ranking.InsertarRanking;
import juego.ranking.RankingDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFinalPeetaYKatniss implements Initializable {
    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @FXML private Label texto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InsertarRanking ranking = InsertarRanking.crearInstancia();
        ranking.setFinalJuego("Final Peeta y Katniss");
        RankingDAO.insertarRanking(ranking);
        texto.setText(dao.obtenerTextoPeeta(35));
        PauseTransition pause = new PauseTransition(Duration.seconds(6));
        pause.setOnFinished(event -> cambiarVista());
        pause.play();
    }
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) texto.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
