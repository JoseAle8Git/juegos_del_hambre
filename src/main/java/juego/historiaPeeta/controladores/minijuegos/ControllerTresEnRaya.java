package juego.historiaPeeta.controladores.minijuegos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.historiaPeeta.controladores.ControllerFlashbackEleccion;
import juego.historiaPeeta.mas.DialogoPeetaDAO;
import juego.ranking.InsertarRanking;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerTresEnRaya implements Initializable {
    @FXML private VBox vbox;
    @FXML private Label texto;
    DialogoPeetaDAO dao = new DialogoPeetaDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texto.setText(dao.obtenerTextoPeeta(30));
    }

    private String[][] tableroLogico = new String[3][3];
    private ImageView[][] tableroGraficamente = new ImageView[3][3];

    private Image imagenVacia;
    private Image imagenJugador;  // "X"
    private Image imagenMaquina;  // "O"

    private boolean juegoTerminado = false;

    @FXML
    public void comenzar(ActionEvent actionEvent) {
        // Cargar imágenes
        imagenVacia = new Image(getClass().getResourceAsStream("/images/imagesPeeta/img.png"));
        imagenJugador = new Image(getClass().getResourceAsStream("/images/imagesPeeta/x.png"));
        imagenMaquina = new Image(getClass().getResourceAsStream("/images/imagesPeeta/circulo.png"));

        vbox.getChildren().clear();
        vbox.setStyle("-fx-background-color: black;");
        vbox.setMaxWidth(500);
        vbox.setMaxHeight(500);
        vbox.setSpacing(10);

        for (int i = 0; i < 3; i++) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            for (int j = 0; j < 3; j++) {
                tableroLogico[i][j] = null;
                ImageView imageView = new ImageView(imagenVacia);
                imageView.getStyleClass().add("imagen");
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                final int fila = i;
                final int columna = j;
                imageView.setOnMouseClicked(event -> {
                    if (juegoTerminado) return;
                    if (tableroLogico[fila][columna] == null) {
                        tableroLogico[fila][columna] = "X";
                        imageView.setImage(imagenJugador);

                        String ganador = comprobarGanador();
                        if (ganador != null) {
                            juegoTerminado = true;
                            if (ganador.equalsIgnoreCase("o")) {
                                alerta("Derrota", "Tu madre ha ganado esta vez");
                            } else  {
                                alerta("Victoria", "Has ganado!!!");
                            }
                            return;
                        } else if (!hayEspaciosDisponibles()) {
                            juegoTerminado = true;
                            alerta("Empate", "Habéis empatado!!!");
                        }
                        turnoMaquina();
                    }
                });
                tableroGraficamente[i][j] = imageView;
                hbox.getChildren().add(imageView);
            }
            vbox.getChildren().add(hbox);
        }

        juegoTerminado = false;
    }

    private void turnoMaquina() {
        if (juegoTerminado) return;

        Random random = new Random();
        int fila, columna;

        // Elegir una casilla vacía al azar
        do {
            fila = random.nextInt(3);
            columna = random.nextInt(3);
        } while (tableroLogico[fila][columna] != null);

        tableroLogico[fila][columna] = "O";
        tableroGraficamente[fila][columna].setImage(imagenMaquina);

        String ganador = comprobarGanador();
        if (ganador != null) {
            juegoTerminado = true;
            if (ganador.equalsIgnoreCase("o")) {
                alerta("Derrota", "Tu madre ha ganado esta vez");
            } else  {
                alerta("Victoria", "Has ganado!!!");
                InsertarRanking ranking = InsertarRanking.crearInstancia();
                ranking.setPuntos(20);
            }
        } else if (!hayEspaciosDisponibles()) {
            juegoTerminado = true;
            InsertarRanking ranking = InsertarRanking.crearInstancia();
            ranking.setPuntos(10);
            alerta("Empate", "Habéis empatado!!!");
        }
    }

    private String comprobarGanador() {
        for (int i = 0; i < 3; i++) {
            // Filas
            if (tableroLogico[i][0] != null &&
                    tableroLogico[i][0].equals(tableroLogico[i][1]) &&
                    tableroLogico[i][0].equals(tableroLogico[i][2])) {
                return tableroLogico[i][0];
            }

            // Columnas
            if (tableroLogico[0][i] != null &&
                    tableroLogico[0][i].equals(tableroLogico[1][i]) &&
                    tableroLogico[0][i].equals(tableroLogico[2][i])) {
                return tableroLogico[0][i];
            }
        }

        // Diagonales
        if (tableroLogico[0][0] != null &&
                tableroLogico[0][0].equals(tableroLogico[1][1]) &&
                tableroLogico[0][0].equals(tableroLogico[2][2])) {
            return tableroLogico[0][0];
        }

        if (tableroLogico[0][2] != null &&
                tableroLogico[0][2].equals(tableroLogico[1][1]) &&
                tableroLogico[0][2].equals(tableroLogico[2][0])) {
            return tableroLogico[0][2];
        }

        return null;
    }

    private boolean hayEspaciosDisponibles() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableroLogico[i][j] == null)
                    return true;
            }
        }
        return false;
    }
    private void alerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        cambiarDeVista();
    }
    private void cambiarDeVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaPeeta/Guardia.fxml"));
            Parent newRoot = loader.load();
            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
