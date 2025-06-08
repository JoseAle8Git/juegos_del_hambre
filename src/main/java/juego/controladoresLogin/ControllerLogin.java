package juego.controladoresLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import juego.conexion.ConexionDB;
import juego.ranking.InsertarRanking;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ControllerLogin {

    @FXML private TextField entradaNombreUsuario;
    @FXML private PasswordField entradaContrasena;
    private static final String NOMBREDB = "combate_juego";
    @FXML private void handleLogin(ActionEvent e) {
        String usuario = entradaNombreUsuario.getText().trim();
        String contrasena = entradaContrasena.getText().trim();

        if(usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor completa todos los campos");
            return;
        }
        try {
            Connection conex = ConexionDB.getInstance(NOMBREDB).getConnection();
            CallableStatement cs = conex.prepareCall("{CALL obtener_contrasena_por_usuario(?)}");
            cs.setString(1, usuario);
            ResultSet result = cs.executeQuery();
            if(result.next()) {
                String contrasenaHashAlmacenada = result.getString("contrasena");
                if(BCrypt.checkpw(contrasena, contrasenaHashAlmacenada)) {
                    try {
                        InsertarRanking ranking = InsertarRanking.crearInstancia();
                        ranking.setNombreUsuario(usuario);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
<<<<<<< HEAD
                        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap4/tributoEliot/historiaEliotCap4Tributo2-6.fxml"));
=======
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/historiaEliotMapa.fxml"));
>>>>>>> origin/main
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch(Exception ex) {
                        ex.printStackTrace();
                        mostrarAlerta("Error", "No se pudo cargar la vista del menú.");
                    }
                } else {
                    mostrarAlerta("Contraseña incorrecta", "La contraseña introducida no es válida");
                }
            } else {
                mostrarAlerta("Usuario no encontrado", "No existe ningún usuario con ese nombre.");
            }
            result.close();
            cs.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error de conexión", "No se pudo conectar con la base de datos.");
        }

    }
    @FXML private void irARegistro(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/register.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de registro.");
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
