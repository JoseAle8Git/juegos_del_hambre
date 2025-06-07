package juego.controladoresMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import juego.conexion.ConexionDB;
import juego.ranking.RankingDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ControllerRanking {

    @FXML private TableView<RankingDTO> tablaRanking;
    @FXML private TableColumn<RankingDTO, Integer> colPosicion;
    @FXML private TableColumn<RankingDTO, String> colUsuario;
    @FXML private TableColumn<RankingDTO, String> colPersonaje;
    @FXML private TableColumn<RankingDTO, String> colFinal;
    @FXML private TableColumn<RankingDTO, Integer> colCombates;
    @FXML private TableColumn<RankingDTO, Integer> colPuntos;

    @FXML
    public void initialize() {
        cargarRanking();
    }

    private void cargarRanking() {
        ObservableList<RankingDTO> lista = FXCollections.observableArrayList();

        try (Connection conn = ConexionDB.getInstance("combate_juego").getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL getTopRanking()}");
            ResultSet rs = cs.executeQuery();

            int posicion = 1;
            while (rs.next()) {
                lista.add(new RankingDTO(
                        posicion++,
                        rs.getString("nombre_usuario"),
                        rs.getString("personaje"),
                        rs.getString("final"),
                        rs.getInt("combates_ganados"),
                        rs.getInt("puntos")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        colPosicion.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPosicion()).asObject());
        colUsuario.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreUsuario()));
        colPersonaje.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPersonaje()));
        colFinal.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFinalJuego()));
        colCombates.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCombatesGanados()).asObject());
        colPuntos.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPuntos()).asObject());

        tablaRanking.setItems(lista);
    }

    @FXML
    private void volverAlMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/menu/menu.fxml"));
            Stage stage = (Stage) tablaRanking.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
