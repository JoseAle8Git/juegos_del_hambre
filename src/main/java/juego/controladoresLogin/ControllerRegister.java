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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            String query = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
            PreparedStatement queryCheck = conex.prepareStatement(query);
            queryCheck.setString(1, usuario);
            ResultSet result =  queryCheck.executeQuery();
            result.next();
            if(result.getInt(1) > 0) {
                mostrarAlerta("Usuario existente", "Ese nombre de usuario ya está registrado.");
                result.close();
                queryCheck.close();
                return;
            }
            result.close();
            queryCheck.close();

            String hash = BCrypt.hashpw(contrasena, BCrypt.gensalt());

            String queryIn = "INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?,?)";
            PreparedStatement queryInsert = conex.prepareStatement(queryIn);
            queryInsert.setString(1, usuario);
            queryInsert.setString(2, hash);
            queryInsert.executeUpdate();
            queryInsert.close();

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
