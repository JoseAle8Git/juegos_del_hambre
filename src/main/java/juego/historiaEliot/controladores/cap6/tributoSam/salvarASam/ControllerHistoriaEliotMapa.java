package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam.JuegoSalvarASam;
import juego.historiaEliot.mas.PuntuacionPersonajeEliot;
import juego.historiaEliot.mas.TributoSam;
import juego.sistemaCombate.modelo.Soldado;
import juego.sistemaCombate.modelo.TipoObjeto;

public class ControllerHistoriaEliotMapa {

    @FXML private ImageView mapa;
    @FXML private Label tu;
    @FXML private Label vida;
    @FXML private Label vendasRestantes;
    @FXML private Button btnIzquierda;
    @FXML private Button btnDerecha;
    @FXML private Button btnAbajo;
    @FXML private Button btnArriba;
    @FXML private Button btnSalir;
    @FXML private HBox botonesHabitaciones;
    @FXML private ProgressBar barraVida;
    @FXML private CheckBox salvarASam;
    @FXML private CheckBox salvarALuna;

    private Button btnSalaCamaras;
    private Button btnCuartoInformzacion;
    private Button btnSalonMapa;
    private Button btnRopero;
    private Button btnSalaMedica;
    private Button btnCuartoLlaves;
    private Button btnSalaArchivos;
    private Button btnSala1;
    private Button btnSala2;
    private Button btnSala3;
    private Button btnSala4;
    private Button btnSala5;
    private Button btnSala6;
    private Button btnSala7;
    private Button btnSala8;

    JuegoSalvarASam juegoSalvarASam;
    PuntuacionPersonajeEliot puntos;
    TributoSam eliot;

    public void initialize(){
        TributoSam.crearInstancia();
        eliot = TributoSam.getInstancia();
        puntos = PuntuacionPersonajeEliot.getPuntuacionPersonajeEliot();
        juegoSalvarASam = JuegoSalvarASam.getJuegoSalvarASam();
        AnchorPane.setTopAnchor(tu, juegoSalvarASam.getCordY());
        AnchorPane.setLeftAnchor(tu, juegoSalvarASam.getCordX());
        if(!juegoSalvarASam.getMapaCompleto()) {
            Image imagen = new Image(getClass().getResourceAsStream("/images/mapaIncompleto.png"));
            mapa.setImage(imagen);
        } else {
            Image imagen = new Image(getClass().getResourceAsStream("/images/mapaCompleto.png"));
            mapa.setImage(imagen);
        }
        if(!juegoSalvarASam.getVistaPrimeraVez()) {
            juegoSalvarASam.setVistaPrimeraVez(true);
            if(juegoSalvarASam.getPos1()) {
                btnDerecha.setDisable(true);
                btnIzquierda.setDisable(true);
                btnAbajo.setDisable(true);
                AnchorPane.setTopAnchor(tu, 430.0);
                AnchorPane.setLeftAnchor(tu, 970.0);
                mostrarBotonesZona1(true);
            }
        }
        if(juegoSalvarASam.getPos4()) {
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(true);
            btnDerecha.setDisable(true);
            mostrarBotonesZona4(true);
        } else if(juegoSalvarASam.getPos7()) {
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(true);
            mostrarBotonesZona7(true);
        } else if(juegoSalvarASam.getPos8()) {
            btnDerecha.setDisable(true);
            btnAbajo.setDisable(true);
            btnArriba.setDisable(true);
            mostrarBotonesZona8(true);
        } else if(juegoSalvarASam.getPos22()) {
            btnAbajo.setDisable(true);
            btnIzquierda.setDisable(true);
            mostrarBotonesZona22(true);
        } else if(juegoSalvarASam.getPos21()) {
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(true);
            btnDerecha.setDisable(true);
            mostrarBotonesZona21(true);
        } else if(juegoSalvarASam.getPos14()) {
            btnAbajo.setDisable(true);
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(true);
            mostrarBotonesZona14(true);
        }
        if(juegoSalvarASam.getSamSalvo()) salvarASam.setSelected(true);
        if(juegoSalvarASam.getLunaSalvo()) salvarALuna.setSelected(true);
        actualizarVida();
        vendasRestantes.setText("Vendas restantes: " + eliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad());
    }

    @FXML private void abrirMenu(ActionEvent actionEvent) {
    }

    @FXML private void usarVenda(ActionEvent actionEvent) {
        if(eliot.getInventarioCombate().usarObjeto(TipoObjeto.VENDA)) {
            eliot.consumirVenda();
            actualizarVida();
            vendasRestantes.setText("Vendas restantes: " + eliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad());
        } else {
            mostrarAlerta("Sin vendas", "No te quedan vendas");
        }
    }

    @FXML private void irHaciaArriba(ActionEvent actionEvent) {
        if(juegoSalvarASam.getPos1()) {
            juegoSalvarASam.setPos1(false);
            juegoSalvarASam.setPos2(true);
            btnArriba.setDisable(true);
            btnAbajo.setDisable(false);
            btnIzquierda.setDisable(false);
            AnchorPane.setTopAnchor(tu, 285.0);
            mostrarBotonesZona1(false);
        } else if(juegoSalvarASam.getPos3()) {
            juegoSalvarASam.setPos3(false);
            juegoSalvarASam.setPos4(true);
            btnIzquierda.setDisable(true);
            btnDerecha.setDisable(true);
            btnArriba.setDisable(true);
            btnAbajo.setDisable(false);
            AnchorPane.setTopAnchor(tu, 225.0);
            mostrarBotonesZona4(true);
        } else if(juegoSalvarASam.getPos5()) {
            juegoSalvarASam.setPos5(false);
            juegoSalvarASam.setPos6(true);
            btnDerecha.setDisable(true);
            AnchorPane.setTopAnchor(tu, 225.0);
        } else if(juegoSalvarASam.getPos6()) {
            juegoSalvarASam.setPos6(false);
            juegoSalvarASam.setPos7(true);
            btnArriba.setDisable(true);
            btnDerecha.setDisable(false);
            AnchorPane.setTopAnchor(tu, 175.0);
            mostrarBotonesZona7(true);
        } else if(juegoSalvarASam.getPos9()) {
            juegoSalvarASam.setPos9(false);
            juegoSalvarASam.setPos5(true);
            AnchorPane.setTopAnchor(tu, 285.0);
        } else if(juegoSalvarASam.getPos12()) {
            juegoSalvarASam.setPos12(false);
            juegoSalvarASam.setPos11(true);
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(false);
            AnchorPane.setTopAnchor(tu, 340.0);
        } else if(juegoSalvarASam.getPos13()) {
            juegoSalvarASam.setPos13(false);
            juegoSalvarASam.setPos12(true);
            btnIzquierda.setDisable(true);
            btnAbajo.setDisable(false);
            AnchorPane.setTopAnchor(tu, 430.0);
        } else if(juegoSalvarASam.getPos15()) {
            juegoSalvarASam.setPos15(false);
            juegoSalvarASam.setPos9(true);
            btnDerecha.setDisable(false);
            AnchorPane.setTopAnchor(tu, 340.0);
        } else if(juegoSalvarASam.getPos16()) {
            juegoSalvarASam.setPos16(false);
            juegoSalvarASam.setPos15(true);
            btnDerecha.setDisable(true);
            btnAbajo.setDisable(false);
            AnchorPane.setTopAnchor(tu, 430.0);
        } else if(juegoSalvarASam.getPos17()) {
            juegoSalvarASam.setPos17(false);
            juegoSalvarASam.setPos21(true);
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(true);
            btnDerecha.setDisable(true);
            btnAbajo.setDisable(false);
            mostrarBotonesZona21(true);
            AnchorPane.setTopAnchor(tu, 450.0);
        } else if(juegoSalvarASam.getPos18()) {
            juegoSalvarASam.setPos18(false);
            juegoSalvarASam.setPos19(true);
            btnIzquierda.setDisable(true);
            btnAbajo.setDisable(false);
            AnchorPane.setTopAnchor(tu, 450.0);
        } else if(juegoSalvarASam.getPos19()) {
            juegoSalvarASam.setPos19(false);
            juegoSalvarASam.setPos20(true);
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(false);
            AnchorPane.setTopAnchor(tu, 395.0);
        } else if(juegoSalvarASam.getPos22()) {
            juegoSalvarASam.setPos22(false);
            juegoSalvarASam.setPos10(true);
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(false);
            btnAbajo.setDisable(false);
            mostrarBotonesZona22(false);
            AnchorPane.setTopAnchor(tu, 340.0);
        }
    }

    @FXML private void irHaciaAbajo(ActionEvent actionEvent) {
        if(juegoSalvarASam.getPos2()) {
            juegoSalvarASam.setPos2(false);
            juegoSalvarASam.setPos1(true);
            btnArriba.setDisable(false);
            btnIzquierda.setDisable(true);
            btnAbajo.setDisable(true);
            AnchorPane.setTopAnchor(tu, 430.0);
            mostrarBotonesZona1(true);
        } else if(juegoSalvarASam.getPos4()) {
            juegoSalvarASam.setPos4(false);
            juegoSalvarASam.setPos3(true);
            btnAbajo.setDisable(true);
            btnArriba.setDisable(false);
            btnIzquierda.setDisable(false);
            btnDerecha.setDisable(false);
            AnchorPane.setTopAnchor(tu, 285.0);
            mostrarBotonesZona4(false);
        } else if(juegoSalvarASam.getPos5()) {
            if(juegoSalvarASam.getCamarasDesactivadas()) {
                juegoSalvarASam.setPos5(false);
                juegoSalvarASam.setPos9(true);
                AnchorPane.setTopAnchor(tu, 340.0);
            } else {
                mostrarAlerta("No se puede pasar", "Para pasar a esta zona debes de desactivar las cámaras primero.");
            }
        } else if(juegoSalvarASam.getPos6()) {
            juegoSalvarASam.setPos6(false);
            juegoSalvarASam.setPos5(true);
            btnDerecha.setDisable(false);
            AnchorPane.setTopAnchor(tu, 285.0);
        } else if(juegoSalvarASam.getPos7()) {
            juegoSalvarASam.setPos7(false);
            juegoSalvarASam.setPos6(true);
            btnDerecha.setDisable(true);
            btnArriba.setDisable(false);
            AnchorPane.setTopAnchor(tu, 225.0);
            mostrarBotonesZona7(false);
        } else if(juegoSalvarASam.getPos9()) {
            juegoSalvarASam.setPos9(false);
            juegoSalvarASam.setPos15(true);
            btnDerecha.setDisable(true);
            AnchorPane.setTopAnchor(tu, 430.0);
        } else if(juegoSalvarASam.getPos10()) {
            juegoSalvarASam.setPos10(false);
            juegoSalvarASam.setPos22(true);
            btnIzquierda.setDisable(true);
            btnAbajo.setDisable(true);
            btnArriba.setDisable(false);
            mostrarBotonesZona22(true);
            AnchorPane.setTopAnchor(tu, 390.0);
        } else if(juegoSalvarASam.getPos11()) {
            juegoSalvarASam.setPos11(false);
            juegoSalvarASam.setPos12(true);
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(false);
            AnchorPane.setTopAnchor(tu, 430.0);
        } else if(juegoSalvarASam.getPos12()) {
            juegoSalvarASam.setPos12(false);
            juegoSalvarASam.setPos13(true);
            btnAbajo.setDisable(true);
            btnIzquierda.setDisable(false);
            AnchorPane.setTopAnchor(tu, 560.0);
        } else if(juegoSalvarASam.getPos15()) {
            juegoSalvarASam.setPos15(false);
            juegoSalvarASam.setPos16(true);
            btnAbajo.setDisable(true);
            btnDerecha.setDisable(false);
            AnchorPane.setTopAnchor(tu, 495.0);
        } else if(juegoSalvarASam.getPos19()) {
            juegoSalvarASam.setPos19(false);
            juegoSalvarASam.setPos18(true);
            btnAbajo.setDisable(true);
            btnIzquierda.setDisable(false);
            AnchorPane.setTopAnchor(tu, 495.0);
        } else if(juegoSalvarASam.getPos20()) {
            juegoSalvarASam.setPos20(false);
            juegoSalvarASam.setPos19(true);
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(false);
            AnchorPane.setTopAnchor(tu, 450.0);
        } else if(juegoSalvarASam.getPos21()) {
            juegoSalvarASam.setPos21(false);
            juegoSalvarASam.setPos17(true);
            btnAbajo.setDisable(true);
            btnDerecha.setDisable(false);
            btnIzquierda.setDisable(false);
            btnArriba.setDisable(false);
            mostrarBotonesZona21(false);
            AnchorPane.setTopAnchor(tu, 495.0);
        }
    }

    @FXML private void irHaciaLaDerecha(ActionEvent actionEvent) {
        if(juegoSalvarASam.getPos3()) {
            juegoSalvarASam.setPos3(false);
            juegoSalvarASam.setPos2(true);
            btnDerecha.setDisable(true);
            btnArriba.setDisable(true);
            btnAbajo.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 970.0);
        } else if(juegoSalvarASam.getPos5()) {
            juegoSalvarASam.setPos5(false);
            juegoSalvarASam.setPos3(true);
            btnIzquierda.setDisable(false);
            btnAbajo.setDisable(true);
            AnchorPane.setLeftAnchor(tu, 585.0);
        } else if(juegoSalvarASam.getPos7()) {
            juegoSalvarASam.setPos7(false);
            juegoSalvarASam.setPos8(true);
            btnAbajo.setDisable(true);
            btnIzquierda.setDisable(false);
            btnDerecha.setDisable(true);
            AnchorPane.setLeftAnchor(tu, 585.0);
            mostrarBotonesZona7(false);
            mostrarBotonesZona8(true);
        } else if(juegoSalvarASam.getPos9()) {
            juegoSalvarASam.setPos9(false);
            juegoSalvarASam.setPos10(true);
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 585.0);
        } else if(juegoSalvarASam.getPos10()) {
            juegoSalvarASam.setPos10(false);
            juegoSalvarASam.setPos11(true);
            btnDerecha.setDisable(true);
            AnchorPane.setLeftAnchor(tu, 890.0);
        } else if(juegoSalvarASam.getPos14()) {
            juegoSalvarASam.setPos14(false);
            juegoSalvarASam.setPos13(true);
            btnDerecha.setDisable(true);
            btnArriba.setDisable(false);
            btnIzquierda.setDisable(false);
            mostrarBotonesZona14(false);
            AnchorPane.setLeftAnchor(tu, 890.0);
        } else if(juegoSalvarASam.getPos16()) {
            juegoSalvarASam.setPos16(false);
            juegoSalvarASam.setPos17(true);
            btnIzquierda.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 550.0);
        } else if(juegoSalvarASam.getPos17()) {
            juegoSalvarASam.setPos17(false);
            juegoSalvarASam.setPos18(true);
            btnDerecha.setDisable(true);
            AnchorPane.setLeftAnchor(tu, 815.0);
        } else if(juegoSalvarASam.getPos22()) {
            juegoSalvarASam.setPos22(false);
            juegoSalvarASam.setPos20(true);
            btnArriba.setDisable(true);
            btnDerecha.setDisable(true);
            btnIzquierda.setDisable(false);
            btnAbajo.setDisable(false);
            mostrarBotonesZona22(false);
            AnchorPane.setLeftAnchor(tu, 815.0);
        }

    }

    @FXML private void irHaciaLaIzquierda(ActionEvent actionEvent) {
        if(juegoSalvarASam.getPos2()) {
            juegoSalvarASam.setPos2(false);
            juegoSalvarASam.setPos3(true);
            btnArriba.setDisable(false);
            btnDerecha.setDisable(false);
            btnAbajo.setDisable(true);
            AnchorPane.setLeftAnchor(tu, 585.0);
        } else if(juegoSalvarASam.getPos3()) {
            juegoSalvarASam.setPos3(false);
            juegoSalvarASam.setPos5(true);
            btnIzquierda.setDisable(true);
            btnAbajo.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 215.0);
        } else if(juegoSalvarASam.getPos8()) {
            juegoSalvarASam.setPos8(false);
            juegoSalvarASam.setPos7(true);
            btnIzquierda.setDisable(true);
            btnAbajo.setDisable(false);
            btnDerecha.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 215.0);
            mostrarBotonesZona8(false);
            mostrarBotonesZona7(true);
        } else if(juegoSalvarASam.getPos10()) {
            juegoSalvarASam.setPos10(false);
            juegoSalvarASam.setPos9(true);
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 215.0);
        } else if(juegoSalvarASam.getPos11()) {
            juegoSalvarASam.setPos11(false);
            juegoSalvarASam.setPos10(true);
            btnDerecha.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 585.0);
        } else if(juegoSalvarASam.getPos13()) {
            juegoSalvarASam.setPos13(false);
            juegoSalvarASam.setPos14(true);
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(true);
            btnDerecha.setDisable(false);
            mostrarBotonesZona14(true);
            AnchorPane.setLeftAnchor(tu, 560.0);
        } else if(juegoSalvarASam.getPos17()) {
            juegoSalvarASam.setPos17(false);
            juegoSalvarASam.setPos16(true);
            btnIzquierda.setDisable(true);
            AnchorPane.setLeftAnchor(tu, 215.0);
        } else if(juegoSalvarASam.getPos18()) {
            juegoSalvarASam.setPos18(false);
            juegoSalvarASam.setPos17(true);
            btnDerecha.setDisable(false);
            AnchorPane.setLeftAnchor(tu, 550.0);
        } else if(juegoSalvarASam.getPos20()) {
            juegoSalvarASam.setPos20(false);
            juegoSalvarASam.setPos22(true);
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(false);
            btnAbajo.setDisable(true);
            btnDerecha.setDisable(false);
            mostrarBotonesZona22(true);
            AnchorPane.setLeftAnchor(tu, 580.0);
        }
    }

    private void actualizarVida() {
        barraVida.setProgress((double) eliot.getVidaActual() / 160);
        vida.setText(eliot.getVidaActual() + "/" + 160);
        vendasRestantes.setText("Vendas restantes: " + eliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad());
    }

    private void mostrarBotonesZona4(Boolean activar) {
        if(activar) {
            btnSalaCamaras = new Button();
            btnCuartoInformzacion = new Button();
            if(!juegoSalvarASam.getMapaCompleto()) {
                btnSalaCamaras.setText("Entrar sala izquierda");
                btnCuartoInformzacion.setText("Entrar sala derecha");
            } else {
                btnSalaCamaras.setText("Sala cámaras");
                btnCuartoInformzacion.setText("Cuarto de información");
            }
            btnSalaCamaras.getStyleClass().add("btnAbrirMenu");
            btnCuartoInformzacion.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(585.0);
            juegoSalvarASam.setCordY(225.0);
            btnSalaCamaras.setOnAction(e -> {
                if(!juegoSalvarASam.getCamarasDesactivadas()) {
                    if(!juegoSalvarASam.getOficialSalaCamaras()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaCamaras1.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaCamaras2.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    }
                } else {
                    mostrarAlerta("Acceso denegado", "Ya has desactivado las cámaras");
                }
            });
            btnCuartoInformzacion.setOnAction(e -> {
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaInformacion.fxml"));
                   Scene scene = new Scene(loader.load());
                   Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                   stage.setScene(scene);
                   stage.show();
               } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
               }
            });
            if(!botonesHabitaciones.getChildren().contains(btnSalaCamaras)) botonesHabitaciones.getChildren().add(btnSalaCamaras);
            if(!botonesHabitaciones.getChildren().contains(btnCuartoInformzacion)) botonesHabitaciones.getChildren().add(btnCuartoInformzacion);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnSalaCamaras,  btnCuartoInformzacion);
        }
    }
    private void mostrarBotonesZona7(Boolean activar) {
        if(activar) {
            btnSalonMapa = new Button("Entrar sala?");
            btnSalonMapa.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(215.0);
            juegoSalvarASam.setCordY(175.0);
            btnSalonMapa.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaMapa.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            if(!botonesHabitaciones.getChildren().contains(btnSalonMapa)) botonesHabitaciones.getChildren().add(btnSalonMapa);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnSalonMapa);
        }
    }
    private void mostrarBotonesZona8(Boolean activar) {
        if(activar) {
            btnSala1 = new Button("1");
            btnSala2 = new Button("2");
            btnSala3 = new Button("3");
            btnSala4 = new Button("4");
            btnSala5 = new Button("5");
            btnSala6 = new Button("6");
            btnSala7 = new Button("7");
            btnSala8 = new Button("8");
            btnRopero = new Button();
            if(!juegoSalvarASam.getMapaCompleto()) btnRopero.setText("Entrar sala derecha");
            else btnRopero.setText("Ropero");
            btnRopero.getStyleClass().add("btnAbrirMenu");
            btnSala1.getStyleClass().add("btnAbrirMenu");
            btnSala2.getStyleClass().add("btnAbrirMenu");
            btnSala3.getStyleClass().add("btnAbrirMenu");
            btnSala4.getStyleClass().add("btnAbrirMenu");
            btnSala5.getStyleClass().add("btnAbrirMenu");
            btnSala6.getStyleClass().add("btnAbrirMenu");
            btnSala7.getStyleClass().add("btnAbrirMenu");
            btnSala8.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(585.0);
            juegoSalvarASam.setCordY(175.0);
            btnRopero.setOnAction(e -> {
                if(!juegoSalvarASam.getTrajeOficial()) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/ropero1.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch(Exception ex) {
                        ex.printStackTrace();
                        mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                    }
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/roperoListo.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch(Exception ex) {
                        ex.printStackTrace();
                        mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                    }
                }
            });
            btnSala1.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getLunaSalvo()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala1.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Listo", "Ya has salvado a Sam, te espera en la salida");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala2.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getTributoVivo().get(5)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala2.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Sitio visitado", "Aquí no esta Sam ni Luna");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala3.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getSamSalvo()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala3.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Listo", "Ya has salvado a Sam, te espera en la salida");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala4.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getTributoVivo().get(4)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala4.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Sitio visitado", "Aquí no esta Sam ni Luna");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala5.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getTributoVivo().get(3)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala5.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Sitio visitado", "Aquí no esta Sam ni Luna");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala6.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getTributoVivo().get(2)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala6.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Sitio visitado", "Aquí no esta Sam ni Luna");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala7.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getTributoVivo().get(1)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala7.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Sitio visitado", "Aquí no esta Sam ni Luna");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            btnSala8.setOnAction(e -> {
                if(juegoSalvarASam.getLlaves()) {
                    if(!juegoSalvarASam.getTributoVivo().get(0)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/sala8.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        mostrarAlerta("Sitio visitado", "Aquí no esta Sam ni Luna");
                    }
                } else {
                    mostrarAlerta("Objeto necesario", "Encuentra unas llaves para poder abrir esta puerta");
                }
            });
            if(!botonesHabitaciones.getChildren().contains(btnRopero)) botonesHabitaciones.getChildren().add(btnRopero);
            if(!botonesHabitaciones.getChildren().contains(btnSala1)) botonesHabitaciones.getChildren().add(btnSala1);
            if(!botonesHabitaciones.getChildren().contains(btnSala2)) botonesHabitaciones.getChildren().add(btnSala2);
            if(!botonesHabitaciones.getChildren().contains(btnSala3)) botonesHabitaciones.getChildren().add(btnSala3);
            if(!botonesHabitaciones.getChildren().contains(btnSala4)) botonesHabitaciones.getChildren().add(btnSala4);
            if(!botonesHabitaciones.getChildren().contains(btnSala5)) botonesHabitaciones.getChildren().add(btnSala5);
            if(!botonesHabitaciones.getChildren().contains(btnSala6)) botonesHabitaciones.getChildren().add(btnSala6);
            if(!botonesHabitaciones.getChildren().contains(btnSala7)) botonesHabitaciones.getChildren().add(btnSala7);
            if(!botonesHabitaciones.getChildren().contains(btnSala8)) botonesHabitaciones.getChildren().add(btnSala8);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnRopero, btnSala1, btnSala2, btnSala3, btnSala4, btnSala5, btnSala6, btnSala7,  btnSala8);
        }
    }
    private void mostrarBotonesZona22(boolean activar) {
        if(activar) {
            btnSalaMedica = new  Button();
            if(!juegoSalvarASam.getMapaCompleto()) btnSalaMedica.setText("Entrar sala izquierda");
            else btnSalaMedica.setText("Sala médica");
            btnSalaMedica.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(580.0);
            juegoSalvarASam.setCordY(390.0);
            btnSalaMedica.setOnAction(e -> {
                try {
                    if(!juegoSalvarASam.getTrajeOficial() && !juegoSalvarASam.getGuardiaVivo2()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaMedica1.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaMedica2.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            if(!botonesHabitaciones.getChildren().contains(btnSalaMedica)) botonesHabitaciones.getChildren().add(btnSalaMedica);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnSalaMedica);
        }
    }
    private void mostrarBotonesZona14(boolean activar) {
        if(activar) {
            btnCuartoLlaves = new  Button();
            if(!juegoSalvarASam.getMapaCompleto())  btnCuartoLlaves.setText("Entrar sala izquierda");
            else btnCuartoLlaves.setText("Cuarto de llaves");
            btnCuartoLlaves.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(585.0);
            juegoSalvarASam.setCordY(560.0);
            btnCuartoLlaves.setOnAction(e -> {
                try {
                    if(!juegoSalvarASam.getLlaves()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/cuartoLlaves.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        mostrarAlerta("Error", "No hay nada más que hacer en esta sala");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            if(!botonesHabitaciones.getChildren().contains(btnCuartoLlaves)) botonesHabitaciones.getChildren().add(btnCuartoLlaves);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnCuartoLlaves);
        }
    }
    private void mostrarBotonesZona21(boolean activar) {
        if(activar) {
            btnSalaArchivos = new  Button();
            if(!juegoSalvarASam.getMapaCompleto()) btnSalaArchivos.setText("Entrar sala izquierda");
            else btnSalaArchivos.setText("Sala de Archivos");
            btnSalaArchivos.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(580.0);
            juegoSalvarASam.setCordY(450.0);
            btnSalaArchivos.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/salaArchivos.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            if(!botonesHabitaciones.getChildren().contains(btnSalaArchivos)) botonesHabitaciones.getChildren().add(btnSalaArchivos);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnSalaArchivos);
        }
    }
    private void mostrarBotonesZona1(boolean activar) {
        if(activar) {
            btnSalir = new  Button();
            btnSalir.setText("Escapar");
            btnSalir.getStyleClass().add("btnAbrirMenu");
            juegoSalvarASam.setCordX(970.0);
            juegoSalvarASam.setCordY(430.0);
            btnSalir.setOnAction(e -> {
                if(juegoSalvarASam.getSamSalvo()) {
                    if(juegoSalvarASam.getSamSalvo() && juegoSalvarASam.getLunaSalvo()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/final2.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    } else {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoSam/salvarASam/final1.fxml"));
                            Scene scene = new Scene(loader.load());
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                        }
                    }
                } else {
                    mostrarAlerta("Acción necesaria", "Para poder escapar al menos debes de salvar a Sam");
                }
            });
            if(!botonesHabitaciones.getChildren().contains(btnSalir)) botonesHabitaciones.getChildren().add(btnSalir);
        } else {
            botonesHabitaciones.getChildren().removeAll(btnSalir);
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
