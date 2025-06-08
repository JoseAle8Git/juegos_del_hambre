package juego.historiaPeeta.controladores.minijuegos;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.ranking.InsertarRanking;

import java.io.IOException;
import java.util.*;

public class ControllerEntrenamiento {
    @FXML private BorderPane rootPane;
    @FXML private Pane pane;
    @FXML private Label labelPuntuacion;
    @FXML private Button startButton;

    private boolean juegoTerminado = false;

    private static final int ALTO = 720;
    private static final int ANCHO = 1221;
    private static final int DELAY = 950;
    private static final int MAX = 10;

    private Timeline timeline;
    private List<ImageView> imagenes = new ArrayList<>();
    private int imageCounter = 0;
    private int animationCounter = 0;
    private int numClicks = 0;
    private String ruta = "/images/imagesPeeta/diana.png";

    @FXML
    public void initialize() {
        rootPane.setCursor(Cursor.CROSSHAIR);
        startButton.setAlignment(Pos.CENTER);
        labelPuntuacion.setText("Acertados: 0");
    }

    @FXML
    private void comenzar(ActionEvent event) {
        startButton.setVisible(false);
        startButton.setManaged(false);
        imageCounter = 0;
        animationCounter = 0;
        numClicks = 0;
        imagenes.clear();
        pane.getChildren().clear();
        labelPuntuacion.setText("Acertados: 0");

        timeline = new Timeline(new KeyFrame(Duration.millis(DELAY), e -> {
            if (imageCounter < MAX) {
                spawnImagen();
                imageCounter++;
            } else {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(MAX);
        timeline.play();
    }

    private void spawnImagen() {
        Image image = new Image(getClass().getResourceAsStream(ruta));
        ImageView view = new ImageView(image);

        int size = new Random().nextInt(100) + 90;
        view.setFitWidth(size);
        view.setFitHeight(size);

        view.setLayoutY(0);
        view.setLayoutX(Math.random() * (ANCHO - size));

        pane.getChildren().add(view);
        imagenes.add(view);

        view.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            imagenes.remove(view);
            pane.getChildren().remove(view);
            numClicks++;
            labelPuntuacion.setText("Acertados: " + numClicks);

            if (animationCounter == MAX - 1) {
                alerta();
            }
        });

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), view);
        transition.setByY(ALTO);
        transition.setOnFinished(e -> {
            imagenes.remove(view);
            pane.getChildren().remove(view);
            animationCounter++;

            if (animationCounter == MAX) {
                alerta();
            }
        });
        transition.play();
    }

    private void alerta() {
        if (juegoTerminado) return;
        juegoTerminado = true;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin del juego");
            alert.setHeaderText(null);
            if (numClicks > 5) {
                InsertarRanking ranking = InsertarRanking.crearInstancia();
                ranking.setPuntos(20);
                alert.setContentText("¡Has ganado!");
            } else {
                alert.setContentText("Has perdido.");
            }
            alert.showAndWait();
            cambiarVista();
        });
    }

    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/EleccionClase.fxml"));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

