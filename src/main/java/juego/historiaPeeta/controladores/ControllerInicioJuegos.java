package juego.historiaPeeta.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import juego.historiaPeeta.mas.DialogoPeetaDAO;
import juego.historiaPeeta.mas.Peeta;
import juego.sistemaCombate.modelo.Personaje;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInicioJuegos implements Initializable {
    DialogoPeetaDAO dao = new DialogoPeetaDAO();
    @FXML Label texto1;
    @FXML Label texto2;
    @FXML Button boton1;
    @FXML Button boton2;
    @FXML Button boton3;
    @FXML HBox caja;
    Peeta peeta = Peeta.getInstancia();
    boolean[] zonas = peeta.getZonasVisitadas();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button boton4 = new Button("Continuar");
        int i = 0;
        peeta.getZonasVisitadas();
        System.out.println(peeta.getZonasVisitadas()[0]);
        texto1.setText(dao.obtenerTextoPeeta(31));
        texto2.setText(dao.obtenerTextoPeeta(32));
        for (boolean z : zonas) {
            if (z) {
                texto1.setVisible(false);
                i++;
            }
        }
        if (peeta.getZonasVisitadas()[0]) boton1.setDisable(true);
        if (peeta.getZonasVisitadas()[1]) boton2.setDisable(true);
        if (peeta.getZonasVisitadas()[2]) boton3.setDisable(true);

        if (i==3) {
            caja.getChildren().clear();
            caja.getChildren().add(boton4);
            boton4.setOnAction((ActionEvent event) -> cambiarVista("/view/historiaPeeta/CombateCato.fxml"));
        }
    }

    @FXML private void bosque(ActionEvent event) {
        zonas[0] = true;
        peeta.setZonasVisitadas(zonas);
        cambiarVista("/view/historiaPeeta/MinijuegoCaza.fxml");
    }

    @FXML private void arena(ActionEvent event) {
        zonas[1] = true;
        peeta.setZonasVisitadas(zonas);
        cambiarVista("/view/historiaPeeta/Arena.fxml");
    }

    @FXML private void cueva(ActionEvent event) {
        zonas[2] = true;
        peeta.setZonasVisitadas(zonas);
        cambiarVista("/view/historiaPeeta/Cueva.fxml");
    }

    private void cambiarVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent newRoot = loader.load();

            // Obtener el Stage desde cualquier nodo del FXML actual
            Stage stage = (Stage) texto2.getScene().getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
