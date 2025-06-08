package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;
import juego.historiaEliot.mas.TributoSam;
import juego.sistemaCombate.modelo.TipoObjeto;

public class ControllerSalaMedica2 {

    JuegoSalvarASam juegoSalvarASam;
    TributoSam tributoSam;

    @FXML private void salir(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/cap6/tributoSam/salvarASam/historiaEliotMapa.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "Error al mostrar la siguiente vista");
        }
    }
    @FXML private void obtener(ActionEvent e) {
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        TributoSam.crearInstancia();
        tributoSam = TributoSam.getInstancia();
        if(juegoSalvarASam.getNumVendas() > 0) {
            if(tributoSam.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad() < 5) {
                tributoSam.getInventarioCombate().getObjeto(TipoObjeto.VENDA).setCantidad();
                juegoSalvarASam.setNumVendas();
            } else {
                mostrarAlerta("Limite de vendas", "No puedes llevar más de 5 vendas en el inventario");
            }
        } else {
            mostrarAlerta("Sin vendas", "No quedan más vendas");
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
