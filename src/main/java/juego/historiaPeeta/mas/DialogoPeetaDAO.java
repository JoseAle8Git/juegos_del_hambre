package juego.historiaPeeta.mas;

import juego.conexion.ConexionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class DialogoPeetaDAO {
    public String obtenerTextoPeeta(int indice) {
        String texto = "";
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs1 = conex.prepareCall("{CALL peeta_obtener_texto(?, ?)}");
            cs1.setInt(1, indice);
            cs1.registerOutParameter(2, Types.VARCHAR);
            cs1.execute();
            texto = cs1.getString(2);
            cs1.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return texto;
    }
}
