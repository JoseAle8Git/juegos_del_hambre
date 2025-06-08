package juego.historiaPeeta.controladores.minijuegos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;
import juego.ranking.InsertarRanking;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerCaraOCruz implements Initializable {
    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(14));
    }

    private static final int CARA = 0;
    private static final int CRUZ = 1;

    @FXML private Label texto;
    @FXML private VBox vbox;

    @FXML public void cara(ActionEvent e) {
        alerta("Cara", "Has elegido cara");
        resultado(CARA);
    }

    @FXML public void cruz(ActionEvent e) {
        alerta("Cruz", "Has elegido cruz");
        resultado(CRUZ);
    }
    private int caraOCruz() {
        Random rand = new Random();
        int num = rand.nextInt(2);
        return num;
    }
    private void alerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void resultado(int num) {
        String rutaImagen = "";
        int numRandom = caraOCruz();
        if (numRandom == CARA) {
            rutaImagen = "/images/imagesPeeta/cara.png";
        } else {
            rutaImagen = "/images/imagesPeeta/cruz.png";
        }
        vbox.getChildren().clear();
        Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitHeight(300);
        vistaImagen.preserveRatioProperty().set(true);
        Button boton = new Button("Continuar");
        boton.setOnAction(actionEvent -> {
            String resultado = "";
            if (num == numRandom) {
                InsertarRanking ranking = InsertarRanking.crearInstancia();
                ranking.setPuntos(10);
                resultado = "Has ganado!!!";
                cambiarDeVista("/view/historiaPeeta/CaraOCruzVictoria.fxml");
            } else {
                resultado = "Has perdido!!!";
                cambiarDeVista("/view/historiaPeeta/CaraOCruzDerrota.fxml");
            }
            alerta("Resultado", resultado);
        });
        vbox.setSpacing(20);
        vbox.getChildren().addAll(vistaImagen, boton);
    }
    private void cambiarDeVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
