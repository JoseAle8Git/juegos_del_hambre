package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;

public class ControllerSalaArchivos {

    @FXML Label distritoSexo;
    @FXML Label nombreSala;
    @FXML Label rolCombate;
    @FXML Button btnAnterior;
    @FXML Button btnSiguiente;
    JuegoSalvarASam juegoSalvarASam;

    public void initialize(){
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        btnAnterior.setDisable(true);
    }

    @FXML private void anterior() {
        if(juegoSalvarASam.getInfoTributos().get(1)){
            juegoSalvarASam.getInfoTributos().set(1, false);
            juegoSalvarASam.getInfoTributos().set(0, true);
            distritoSexo.setText("Tributo Distrito 1 mujer");
            nombreSala.setText("Habitación 8: Glimmer");
            rolCombate.setText("Rol de combate: Asesino");
            btnAnterior.setDisable(true);
        } else if(juegoSalvarASam.getInfoTributos().get(2)){
            juegoSalvarASam.getInfoTributos().set(2, false);
            juegoSalvarASam.getInfoTributos().set(1, true);
            distritoSexo.setText("Tributo Distrito 1 hombre");
            nombreSala.setText("Habitación 7: Marvel");
            rolCombate.setText("Rol de combate: Guerrero");
        } else if (juegoSalvarASam.getInfoTributos().get(3)) {
            juegoSalvarASam.getInfoTributos().set(3, false);
            juegoSalvarASam.getInfoTributos().set(2, true);
            distritoSexo.setText("Tributo Distrito 2 mujer");
            nombreSala.setText("Habitación 6: Clove");
            rolCombate.setText("Rol de combate: Asesino");
        } else if(juegoSalvarASam.getInfoTributos().get(4)) {
            juegoSalvarASam.getInfoTributos().set(4, false);
            juegoSalvarASam.getInfoTributos().set(3, true);
            distritoSexo.setText("Tributo Distrito 2 hombre");
            nombreSala.setText("Habitación 5: Cato");
            rolCombate.setText("Rol de combate: Guerrero");
        } else if(juegoSalvarASam.getInfoTributos().get(5)) {
            juegoSalvarASam.getInfoTributos().set(5, false);
            juegoSalvarASam.getInfoTributos().set(4, true);
            distritoSexo.setText("Tributo Distrito 3 mujer");
            nombreSala.setText("Habitación 4: Gloss");
            rolCombate.setText("Rol de combate: Cazador");
        } else if(juegoSalvarASam.getInfoTributos().get(6)) {
            juegoSalvarASam.getInfoTributos().set(6, false);
            juegoSalvarASam.getInfoTributos().set(5, true);
            distritoSexo.setText("Tributo Distrito 11 hombre");
            nombreSala.setText("Habitación 3: Sam");
            rolCombate.setText("Rol de combate: Arquero");
        } else if(juegoSalvarASam.getInfoTributos().get(7)) {
            juegoSalvarASam.getInfoTributos().set(7, false);
            juegoSalvarASam.getInfoTributos().set(6, true);
            distritoSexo.setText("Tributo Distrito 5 mujer");
            nombreSala.setText("Habitación 2: Comadreja");
            rolCombate.setText("Rol de combate: Asesino");
            btnSiguiente.setDisable(false);
        }
    }

    @FXML private void siguiente() {
        if(juegoSalvarASam.getInfoTributos().get(0)) {
            juegoSalvarASam.getInfoTributos().set(0, false);
            juegoSalvarASam.getInfoTributos().set(1, true);
            distritoSexo.setText("Tributo Distrito 1 hombre");
            nombreSala.setText("Habitación 7: Marvel");
            rolCombate.setText("Rol de combate: Guerrero");
            btnAnterior.setDisable(false);
        } else if(juegoSalvarASam.getInfoTributos().get(1)) {
            juegoSalvarASam.getInfoTributos().set(1, false);
            juegoSalvarASam.getInfoTributos().set(2, true);
            distritoSexo.setText("Tributo Distrito 2 mujer");
            nombreSala.setText("Habitación 6: Clove");
            rolCombate.setText("Rol de combate: Asesino");
        } else if(juegoSalvarASam.getInfoTributos().get(2)) {
            juegoSalvarASam.getInfoTributos().set(2, false);
            juegoSalvarASam.getInfoTributos().set(3, true);
            distritoSexo.setText("Tributo Distrito 2 hombre");
            nombreSala.setText("Habitación 5: Cato");
            rolCombate.setText("Rol de combate: Guerrero");
        } else if(juegoSalvarASam.getInfoTributos().get(3)) {
            juegoSalvarASam.getInfoTributos().set(3, false);
            juegoSalvarASam.getInfoTributos().set(4, true);
            distritoSexo.setText("Tributo Distrito 3 mujer");
            nombreSala.setText("Habitación 4: Gloss");
            rolCombate.setText("Rol de combate: Cazador");
        } else if(juegoSalvarASam.getInfoTributos().get(4)) {
            juegoSalvarASam.getInfoTributos().set(4, false);
            juegoSalvarASam.getInfoTributos().set(5, true);
            distritoSexo.setText("Tributo Distrito 11 hombre");
            nombreSala.setText("Habitación 3: Sam");
            rolCombate.setText("Rol de combate: Arquero");
        } else if(juegoSalvarASam.getInfoTributos().get(5)) {
            juegoSalvarASam.getInfoTributos().set(5, false);
            juegoSalvarASam.getInfoTributos().set(6, true);
            distritoSexo.setText("Tributo Distrito 5 mujer");
            nombreSala.setText("Habitación 2: Comadreja");
            rolCombate.setText("Rol de combate: Asesino");
        } else if(juegoSalvarASam.getInfoTributos().get(6)) {
            juegoSalvarASam.getInfoTributos().set(6, false);
            juegoSalvarASam.getInfoTributos().set(7, true);
            distritoSexo.setText("Tributo Distrito 11 mujer");
            nombreSala.setText("Habitación 1: Luna");
            rolCombate.setText("Rol de combate: Cazador");
            btnSiguiente.setDisable(true);
        }
    }

    @FXML private void salir(ActionEvent e) {
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
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}