package juego.historiaEliot.controladores.cap6.tributoEliot.juegosDelHambre;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.historiaEliot.mas.TributoEliot;
import juego.ranking.InsertarRanking;

import java.util.ArrayList;
import java.util.List;

public class ControllerCocinarCarne {

    @FXML
    private VBox panelInicio;
    @FXML private HBox panelJuego;
    @FXML private AnchorPane panelAP;

    @FXML private ImageView imgPalo, imgYesca, imgRamitas, imgPiedra, imgAgua;
    @FXML private VBox zonaConstruccion;

    private final List<String> construccionActual = new ArrayList<>();
    private final List<String> ordenCorrecto = List.of("palo", "yesca", "ramitas");

    private TributoEliot  tributoEliot;
    private InsertarRanking insertarRanking;

    @FXML
    public void initialize() {
        tributoEliot = TributoEliot.getInstancia();
        insertarRanking = InsertarRanking.crearInstancia();
        cargarImagen(imgPalo, "palo.png", "palo");
        cargarImagen(imgYesca, "yesca.png", "yesca");
        cargarImagen(imgRamitas, "ramitas.png", "ramitas");
        cargarImagen(imgPiedra, "piedra.png", "piedra");
        cargarImagen(imgAgua, "agua.png", "agua");
    }

    private void cargarImagen(ImageView view, String archivo, String id) {
        try {
            view.setImage(new Image(getClass().getResourceAsStream("/images/" + archivo)));
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + archivo);
        }
        view.setId(id);
        view.setOnDragDetected(event -> {
            Dragboard db = view.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(id);
            db.setContent(content);
            event.consume();
        });
    }

    @FXML
    private void empezarJuego() {
        panelInicio.setVisible(false);
        panelJuego.setVisible(true);
        construccionActual.clear();
        zonaConstruccion.getChildren().clear();
    }

    @FXML
    private void manejarDragOver(DragEvent event) {
        if (event.getGestureSource() != zonaConstruccion && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    @FXML
    private void manejarDrop(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean exito = false;

        if (db.hasString()) {
            String id = db.getString();
            construccionActual.add(id);

            ImageView nuevaImagen = new ImageView(((ImageView) event.getGestureSource()).getImage());
            nuevaImagen.setFitWidth(60);
            nuevaImagen.setFitHeight(60);
            zonaConstruccion.getChildren().add(nuevaImagen);

            if (id.equals("piedra") || id.equals("agua")) {
                mostrarAlerta("¡Has fallado!", "Has usado un material incorrecto: " + id, Alert.AlertType.ERROR);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoEliot/juegosDelHambre/mapaJuegosDelHambre.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista", Alert.AlertType.ERROR);
                }
            } else if (construccionActual.size() == 3) {
                comprobarResultado();
            }

            exito = true;
        }

        event.setDropCompleted(exito);
        event.consume();
    }

    private void comprobarResultado() {
        if (construccionActual.equals(ordenCorrecto)) {
            tributoEliot.getInventarioGeneral().put("Carne cocinada", tributoEliot.getInventarioGeneral().getOrDefault("Carne cocinada", 0) + 1);
            mostrarAlerta("¡Éxito!", "¡Has encendido la hoguera con éxito!", Alert.AlertType.INFORMATION);
            insertarRanking.setPuntos(20);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoEliot/juegosDelHambre/mapaJuegosDelHambre.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) panelAP.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else {
            mostrarAlerta("¡Has fallado!", "El orden no es correcto.", Alert.AlertType.ERROR);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoEliot/juegosDelHambre/mapaJuegosDelHambre.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) panelAP.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        reiniciarJuego();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void reiniciarJuego() {
        construccionActual.clear();
        zonaConstruccion.getChildren().clear();
    }

}
