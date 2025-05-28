package juego.historiaEliot.mas;

import juego.conexion.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DialogoDAO {

    public String obtenerTexto(int indice) {
        String texto = "";
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs1 = conex.prepareCall("{CALL obtener_texto_por_id(?, ?)}");
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
    public String obtenerTextobtn(int indice) {
        String texto = "";
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            CallableStatement cs1 = conex.prepareCall("{CALL obtener_btn_texto_por_id(?, ?)}");
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
    public List<Dialogo> obtenerDialogos(int capitulo, int escena) {
        List<Dialogo> dialogos = new ArrayList<>();
        String query1 = "SELECT * FROM dialogos WHERE capitulo = ? AND escena = ? ORDER BY id ASC";
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            PreparedStatement ps1 = conex.prepareStatement(query1);
            ps1.setInt(1, capitulo);
            ps1.setInt(2, escena);
            ResultSet rs1 =  ps1.executeQuery();
            while(rs1.next()){
                dialogos.add(new Dialogo(
                        rs1.getInt("id"),
                        rs1.getString("personaje"),
                        rs1.getInt("capitulo"),
                        rs1.getInt("escena"),
                        rs1.getString("tipo"),
                        rs1.getString("texto"),
                        rs1.getString("btn_texto")
                ));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return dialogos;
    }
    public List<Dialogo> obtenerDialogosPorTipo(int capitulo, int escena, String tipo) {
        List<Dialogo> dialogos = new ArrayList<>();
        String query1 = "SELECT * FROM dialogos WHERE capitulo = ? AND escena = ? AND tipo = ? ORDER BY id ASC";
        try {
            Connection conex = ConexionDB.getInstance("combate_juego").getConnection();
            PreparedStatement ps1 = conex.prepareStatement(query1);
            ps1.setInt(1, capitulo);
            ps1.setInt(2, escena);
            ps1.setString(3, tipo);
            ResultSet rs1 =  ps1.executeQuery();
            while(rs1.next()){
                dialogos.add(new Dialogo(
                        rs1.getInt("id"),
                        rs1.getString("personaje"),
                        rs1.getInt("capitulo"),
                        rs1.getInt("escena"),
                        rs1.getString("tipo"),
                        rs1.getString("texto"),
                        rs1.getString("btn_texto")
                ));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return dialogos;
    }

}
