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
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class ControllerRegister {

    @FXML private TextField registroNombreUsuario;
    @FXML private PasswordField registroContrasena;
    @FXML private PasswordField registroConfirmarContrasena;

    private static final String NOMBRE_DB = "combate_juego";

    @FXML private void handleRegistro(ActionEvent e) throws SQLException {
        String usuario = registroNombreUsuario.getText().trim();
        String contrasena = registroContrasena.getText().trim();
        String confirmarContrasena = registroConfirmarContrasena.getText().trim();

        if(usuario.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Completa todos los campos.");
            return;
        }

        if(!contrasena.equals(confirmarContrasena)) {
            mostrarAlerta("Contraseñas no coinciden", "La contraseña y su confirmación no son iguales.");
            return;
        }

        try {
            Connection conex = ConexionDB.getInstance(NOMBRE_DB).getConnection();

            CallableStatement cs1 = conex.prepareCall("{CALL usuario_existe(?, ?)}");
            cs1.setString(1, usuario);
            cs1.registerOutParameter(2, Types.INTEGER);
            cs1.execute();
            int existe = cs1.getInt(2);
            cs1.close();
            if (existe > 0) {
                mostrarAlerta("Usuario existente", "Ese nombre de usuario ya está registrado.");
                return;
            }

            String hash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

            CallableStatement cs2 = conex.prepareCall("{CALL insertar_usuario(?, ?)}");
            cs2.setString(1, usuario);
            cs2.setString(2, hash);
            cs2.executeUpdate();
            cs2.close();

            mostrarAlerta("Registro exitoso", "Usuario creado correctamente. Inicia sesión.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error de conexión", "No se pudo registrar el usuario");
        }
    }
    @FXML private void irALogin(ActionEvent e) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de login.");
        }
    }
    private void mostrarAlerta(String titulo, String mensage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensage);
        alert.showAndWait();
    }

}
