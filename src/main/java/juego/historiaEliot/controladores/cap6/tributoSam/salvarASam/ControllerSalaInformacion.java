package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

public class ControllerSalaInformacion {

    @FXML private ToggleButton switch1, switch2, switch3, switch4, switch5, switch6, switch7, switch8;
    @FXML private Button btnComprobar, btnReglas, btnCerrarReglas;
    @FXML private VBox panelReglas;
    @FXML private Label lblResultado, lblPassword;

    private final List<Boolean> combinacionCorrecta = Arrays.asList(true, false, true, false, true, false, true, false);

    @FXML
    public void initialize() {
        btnComprobar.setOnAction(e -> comprobarCombinacion());
        btnReglas.setOnAction(e -> panelReglas.setVisible(true));
        btnCerrarReglas.setOnAction(e -> panelReglas.setVisible(false));
    }

    private void comprobarCombinacion() {
        List<Boolean> actual = Arrays.asList(
                switch1.isSelected(), switch2.isSelected(), switch3.isSelected(), switch4.isSelected(),
                switch5.isSelected(), switch6.isSelected(), switch7.isSelected(), switch8.isSelected()
        );

        if (actual.equals(combinacionCorrecta)) {
            mostrarPasswordAnimado();
            lblResultado.setText("");
        } else {
            long activos = actual.stream().filter(b -> b).count();
            if (activos > combinacionCorrecta.stream().filter(b -> b).count()) {
                lblResultado.setText("Demasiados interruptores activos.");
            } else {
                lblResultado.setText("Combinación incorrecta. Prueba otra.");
            }
        }
    }

    private void mostrarPasswordAnimado() {
        lblPassword.setText("Contraseña: granJuego");
        lblPassword.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.seconds(2), lblPassword);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML private void salir(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/historiaEliotMapa.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
