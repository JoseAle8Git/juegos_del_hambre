package juego.historiaPeeta.controladores.minijuegos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.ranking.InsertarRanking;

import java.io.IOException;
import java.util.Random;

public class ControllerMinijuegoLlave {
    @FXML private VBox vbox;
    private Label label;
    private int oportunidades;
    private int taquillaCorrecta;
    private boolean finJuego = false;
    private boolean llave = false;

    @FXML
    private void comenzar(ActionEvent actionEvent) {
        taquillaCorrecta = numAletorio();
        oportunidades = 6;
        finJuego = false;
        vbox.getChildren().clear();
        label = new Label("Oportunidades restantes: 6");
        label.getStyleClass().add("label");
        vbox.getChildren().add(label);
        vbox.setSpacing(20);

        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox, hbox2);

        Button btn = new Button("Continuar");
        btn.setOnAction(event -> cambiarDeVista());

        Image taquillaCerrada = new Image(getClass().getResourceAsStream("/images/imagesPeeta/taquillaCerrada.png"));
        Image taquillaAbierta = new Image(getClass().getResourceAsStream("/images/imagesPeeta/taquillaAbierta.png"));
        Image taquillaLlave = new Image(getClass().getResourceAsStream("/images/imagesPeeta/taquillaLlave.png"));

        // Guardamos referencias para poder desactivar todas
        ImageView[] taquillas = new ImageView[10];

        for (int i = 0; i < 10; i++) {
            final int index = i;
            ImageView iv = new ImageView(taquillaCerrada);
            iv.setFitWidth(96);
            iv.setFitHeight(378);
            iv.getStyleClass().add("taquilla");
            taquillas[i] = iv;

            iv.setOnMouseClicked(event -> {
//                if (finJuego) {
//                    alerta("Final", "El juego ya ha finalizado.");
//                    return;
//                }

                iv.setImage(taquillaAbierta);
                iv.setDisable(true);

                if (index == taquillaCorrecta) {
//                    alerta("¡Ganaste!", "Has encontrado la taquilla correcta.");
                    taquillas[taquillaCorrecta].setImage(taquillaLlave);
                    llave = true;
                    label.setVisible(false);
                    finJuego = true;
                } else {
                    if (oportunidades == 1) {
                        oportunidades--;
                        label.setVisible(false);
//                        alerta("¡Perdiste!", "Se acabaron tus intentos.");
                        finJuego = true;
                    } else {
                        oportunidades--;
//                        alerta("Intento fallido", "Te quedan " + oportunidades + " intentos.");
                    }
                    label.setText("Oportunidades restantes: " + oportunidades);
                }

                if (finJuego) {
                    // Desactivar todas las taquillas
                    for (ImageView img : taquillas) {
                        img.setDisable(true);
                    }

                    // Mostrar la taquilla correcta si perdiste
//                    if (oportunidades <= 0 && taquillas[taquillaCorrecta] != null) {
//                        taquillas[taquillaCorrecta].setImage(taquillaAbierta);
//                    }

                    // Añadir el botón de continuar
                    if (!hbox2.getChildren().contains(btn)) {
                        hbox2.getChildren().add(btn);
                    }
                }
            });
            hbox.getChildren().add(iv);
        }
    }

    private int numAletorio() {
        Random rand = new Random();
        return rand.nextInt(10);
    }
    private void alerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void cambiarDeVista() {
        String vista;
        if (llave) {
            InsertarRanking ranking = InsertarRanking.crearInstancia();
            ranking.setPuntos(20);
            vista = "/view/historiaPeeta/FinalTren.fxml";
        } else{
            vista = "/view/historiaPeeta/CombateGuardia.fxml";
        }
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
