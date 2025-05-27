package juego.historiaEliot.mas;

import juego.conexion.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DialogoDAO {

    public String obtenerTexto(int indice) {
        String texto = "";
        String Query1 = "SELECT texto FROM dialogos WHERE id = ?";
        try {
            Connection conex = ConexionDB.getInstance("comabte_juego").getConnection();
            PreparedStatement ps1 =  conex.prepareStatement(Query1);
            ps1.setInt(1, indice);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                texto = rs1.getString("texto");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return texto;
    }
    public String obtenerTextobtn(int indice) {
        String texto = "";
        String Query1 = "SELECT btn_texto FROM dialogos WHERE id = ?";
        try {
            Connection conex = ConexionDB.getInstance("comabte_juego").getConnection();
            PreparedStatement ps1 =  conex.prepareStatement(Query1);
            ps1.setInt(1, indice);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                texto = rs1.getString("btn_texto");
            }
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
