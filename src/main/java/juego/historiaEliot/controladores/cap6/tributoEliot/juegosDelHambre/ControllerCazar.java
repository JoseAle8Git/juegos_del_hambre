package juego.historiaEliot.controladores.cap6.tributoEliot.juegosDelHambre;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.controladores.cap6.tributoEliot.controlJuegosDelHambre.ControlJuegosDelHambre;
import juego.historiaEliot.mas.TributoEliot;

import java.io.IOException;
import java.util.Random;

public class ControllerCazar {

    @FXML
    private Pane paneJuego;
    @FXML private Button botonCazar;
    @FXML private Label reglasLabel;
    @FXML private AnchorPane panelAP;

    private final String[] animales = {"conejo.png", "pajaro.png", "ciervo.png"};
    private int intentos = 0;
    private int acierto = 0;
    private final int maxIntentos = 5;
    private final Random random = new Random();
    private ControlJuegosDelHambre  controlJuegosDelHambre;

    public void initialize() {
        panelAP.setCursor(new ImageCursor(
                new Image(getClass().getResourceAsStream("/images/mira.png")),
                16, 16
        ));
        controlJuegosDelHambre = ControlJuegosDelHambre.getInstancia();
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            setBackground("cornocopia");
        } else if(controlJuegosDelHambre.isZonaSur()) {
            setBackground("sur");
        } else if(controlJuegosDelHambre.isZonaOeste()) {
            setBackground("oeste");
        } else if(controlJuegosDelHambre.isZonaNorte()) {
            setBackground("norte");
        } else if (controlJuegosDelHambre.isZonaEste()) {
            setBackground("este");
        }
        reglasLabel.setText(
                "Reglas:\n" +
                        "1. Debes hacer clic sobre el animal mientras se mueve.\n" +
                        "2. Solo si aciertas el clic, se considera cazado.\n" +
                        "3. Repite esto 5 veces."
        );
        botonCazar.setOnAction(this::iniciarJuego);
    }

    private void iniciarJuego(ActionEvent e) {
        reglasLabel.setVisible(false);
        botonCazar.setVisible(false);

        if (paneJuego == null) {
            paneJuego = new Pane();
        }

        lanzarAnimal();
    }

    private void lanzarAnimal() {
        if (intentos >= maxIntentos) {
            irAVistaFinal();
            return;
        }

        String ruta = animales[random.nextInt(animales.length)];
        ImageView animal = new ImageView(new Image(getClass().getResourceAsStream("/images/" + ruta)));
        animal.setFitWidth(100);
        animal.setFitHeight(100);

        double startY = 150 + random.nextDouble() * 400;
        boolean izquierdaADerecha = random.nextBoolean();

        animal.setLayoutY(startY);
        animal.setLayoutX(izquierdaADerecha ? -100 : 1221);

        TranslateTransition movimiento = new TranslateTransition(Duration.seconds(3), animal);
        movimiento.setFromX(0);
        movimiento.setToX(izquierdaADerecha ? 1400 : -1400);

        if (!paneJuego.getChildren().contains(animal)) {
            paneJuego.getChildren().add(animal);
        }

        animal.setOnMouseClicked(event -> {
            movimiento.stop();
            paneJuego.getChildren().remove(animal);
            intentos++;
            acierto++;
            lanzarAnimal();
        });

        movimiento.setOnFinished(ev -> {
            if (paneJuego.getChildren().contains(animal)) {
                paneJuego.getChildren().remove(animal);
                intentos++;
                lanzarAnimal();
            }
        });

        movimiento.play();
    }

    private void irAVistaFinal() {
        TributoEliot tributoEliot = TributoEliot.getInstancia();
        if(acierto == 5) {
            tributoEliot.getInventarioGeneral().put("Carne cruda", tributoEliot.getInventarioGeneral().getOrDefault("Carne cruda", 0) + 1);
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoEliot/juegosDelHambre/mapaJuegosDelHambre.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) panelAP.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
        }
    }

    private void setBackground(String lugar) {
        panelAP.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image(getClass().getResource("/images/" + lugar + ".png").toExternalForm()),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
                        )
                )
        );
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
