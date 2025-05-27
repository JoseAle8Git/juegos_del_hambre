package juego.controladoresMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerReglas {

    @FXML private AnchorPane contenedorReglas;
    @FXML private void juego(ActionEvent event) {
        contenedorReglas.getChildren().clear();
        contenedorReglas.getChildren().add(setContenido(
                "Elección de personajes",
                "Al empezar a jugar se debe tomar una elección muy importante que influirá completamente en el juego.",
                "Se dará la opción a elegir 3 personajes.",
                "Dependiendo del personaje elegido la historia será completamente diferente en la aterradora Panem.",
                0,
                20,
                20
        ));
        contenedorReglas.getChildren().add(setContenido(
                "Historia conversacional",
                "El funcionamiento del juego es el de una historia en la que se van tomando decisiones.",
                "Dependiendo de la decisiones tomadas se abrira un camino de la historia u otra.",
                "El juego contiene al menos 5 finales totalmente distintos por cada personaje del juego, o quiezas hayan más...",
                150,
                20,
                20
        ));
        contenedorReglas.getChildren().add(setContenido(
                "Historia",
                "Nuestra historia comienza en un país llamado Panem con una situción política muy especial y complicada.",
                "Nuestro camino comienza en el distrito 12. Un distrito muy pobre que se encarga de suministrar carbón al Capitolio.",
                "Nuestros personajes se encontraran en el situación donde ya tienen 16 años y pueden ser seleccionados para los Juegos del Hambre y...",
                300,
                0,
                0
        ));
    }
    @FXML private void sistemaPuntos(ActionEvent e) {
        contenedorReglas.getChildren().clear();
        contenedorReglas.getChildren().add(setContenido(
                "Sistema de puntuación",
                "Los puntos en el juego sirven para ganar prestigio y posicionarte en el top del ranking del juego",
                "Cada partida que juegues será valorada principalmente por puntos.",
                "Dependiendo de las elecciones que tomes en el juego terminaras con cierta puntuación.",
                0,
                20,
                20
        ));
        contenedorReglas.getChildren().add(setContenido(
                "¿Cómo conseguir los puntos en el juego?",
                "Hay muachas maneras de conseguir puntos en este juego, ya sea ganando combates, resolviendo puzzles...",
                "Cada vez que consigas un logro destacable en el juego se irán acumulando puntos.",
                "Pero cuidado, pueden haber ciertas situaciones que te puedan llevar a perder puntos, asi que cuidado con tus desiciones.",
                150,
                20,
                20
        ));
        contenedorReglas.getChildren().add(setContenido(
                "Puntos por objetivos",
                "Ganando un combate se conseguirán 20 puntos. Tomando desiciones moralmente correctas se conseguirán 5puntos.",
                "Resolviendo puzzles fáciles se conseguirán 7 puntos, intermedios 10 puntos y difícles 20 puntos.",
                "Tomar desiciones que compliquen tu historia pueden llegar a restar hasta 10 puntos por una muy mala desición.",
                300,
                20,
                20
        ));
    }
    @FXML private void sistemaCombate(ActionEvent e) {
        contenedorReglas.getChildren().clear();
        AnchorPane contenedorLogos = new AnchorPane();
        AnchorPane.setLeftAnchor(contenedorLogos, 30.0);
        AnchorPane.setTopAnchor(contenedorLogos, 25.0);
        AnchorPane.setRightAnchor(contenedorLogos, 50.0);
        ImageView imagen = new ImageView();
        Image img = new Image(getClass().getResource("/images/counters.png").toExternalForm());
        imagen.setImage(img);
        imagen.setFitHeight(400);
        imagen.setFitWidth(400);
        contenedorLogos.getChildren().add(imagen);
        contenedorReglas.getChildren().add(contenedorLogos);
        contenedorReglas.getChildren().add(setContenido(
                "Combate",
                "Cada clase distinta tiene 4 tipos de atauqe distintos.",
                "Cada clase tiene una defensa distinta.",
                "Solo se puede atacar una vez por turno.",
                0,
                10,
                420
        ));
        contenedorReglas.getChildren().add(setContenido(
                "Inventario",
                "Las vendas sirven para curarse la vida.",
                "La carne sirve para aumentar la probabilidad de acierto en ese turno",
                "Las hojas sirven para aumentar la defensa.",
                150,
                10,
                420
        ));
        contenedorReglas.getChildren().add(setContenido(
                "Elementos externos",
                "El clima, el terreno y el momento del día pueden influir en el combate.",
                "Dependiendo de en que condiciones se este luchando se penalizará o bonificará",
                "Sabiendo todo esto, planea bien tu estrategia de combate. Te podría salvar la vida.",
                300,
                10,
                420
        ));
    }
    @FXML private void volverAMenu(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista menú");
        }
    }
    private VBox setContenido(String titulo, String mensaje1, String mensaje2, String mensaje3, double top, double right, double left) {
        VBox contenido = new VBox();
        contenido.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(contenido, top);
        AnchorPane.setLeftAnchor(contenido, left);
        AnchorPane.setRightAnchor(contenido, right);
        Label title = new Label(titulo);
        title.getStyleClass().add("titulo");
        Label linea1 = new Label(mensaje1);
        linea1.getStyleClass().add("contenido");
        Label linea2 = new Label(mensaje2);
        linea2.getStyleClass().add("contenido");
        Label linea3 = new Label(mensaje3);
        linea3.getStyleClass().add("contenido");
        contenido.getChildren().addAll(title ,linea1, linea2, linea3);
        return contenido;
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
