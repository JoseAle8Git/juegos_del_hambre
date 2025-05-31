package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;
import juego.historiaEliot.mas.PuntuacionPersonajeEliot;
import juego.ranking.InsertarRanking;

public class ControllerSalaMapa {

    @FXML private AnchorPane root;
    @FXML private ImageView imagenFondo;
    @FXML private ImageView objeto1, objeto2, objeto3, objeto4, objeto5;
    @FXML private Button btnSalir;

    JuegoSalvarASam juegoSalvarASam;

    public void initialize() {
        imagenFondo.setImage(new Image(getClass().getResourceAsStream("/images/salaMapa.png")));

        configurarObjeto(objeto1, "/images/barriles.png", false);
        configurarObjeto(objeto2, "/images/maleta.png", false);
        configurarObjeto(objeto3, "/images/mapa.png", true);
        configurarObjeto(objeto4, "/images/cajaConMapa.png", false);
        configurarObjeto(objeto5, "/images/cajonera.png", false);

        btnSalir.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/historiaEliotMapa.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
        });
    }
    private void configurarObjeto(ImageView objeto, String rutaImagen, boolean tieneMapa) {
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        objeto.setImage(new Image(getClass().getResourceAsStream(rutaImagen)));
        objeto.setFitWidth(150);
        objeto.setFitHeight(150);
        objeto.setPreserveRatio(true);
        objeto.setOnMouseClicked((MouseEvent e) -> {
            if(tieneMapa && !juegoSalvarASam.getMapaCompleto()) {
                InsertarRanking ranking = InsertarRanking.crearInstancia();
                ranking.setPuntos(10);
                juegoSalvarASam.setMapaCompleto(true);
                mostrarMensaje("¡Has encontrado el mapa!");
                objeto.setStyle("-fx-effect: dropshadow(gaussian, green, 20, 0.5, 0, 0)");
            } else {
                mostrarMensaje("Nada interesante aquí!!!");
                objeto.setStyle("-fx-effect: dropshadow(gaussian, red, 20, 0.5, 0, 0)");
            }
        });
    }
    private void mostrarMensaje(String texto) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(texto);
        alerta.showAndWait();
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
