package juego.historiaEliot.controladores.cap6.tributoSam.salvarASam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    @FXML private HBox botonesHabitaciones;
    @FXML private ProgressBar barraVida;

    private Button btnSalaCamaras;
    private Button btnCuartoInformzacion;
    private Button btnSalonMapa;
    private Button btnRopero;
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
                AnchorPane.setTopAnchor(tu, 430.0);
                AnchorPane.setLeftAnchor(tu, 970.0);
            }
        }
        if(juegoSalvarASam.getPos4()) {
            btnArriba.setDisable(true);
            btnIzquierda.setDisable(true);
            btnDerecha.setDisable(true);
            mostrarBotonesZona4(true);
        }
        if(juegoSalvarASam.getPos7()) {
            btnIzquierda.setDisable(true);
            btnArriba.setDisable(true);
            mostrarBotonesZona7(true);
        }
        if(juegoSalvarASam.getPos8()) {
            btnDerecha.setDisable(true);
            btnAbajo.setDisable(true);
            btnArriba.setDisable(true);
            mostrarBotonesZona8(true);
        }
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
            btnIzquierda.setDisable(false);
            AnchorPane.setTopAnchor(tu, 285.0);
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
        }
    }

    @FXML private void irHaciaAbajo(ActionEvent actionEvent) {
        if(juegoSalvarASam.getPos1()) {
            // Aquí va a ir el final
        } else if(juegoSalvarASam.getPos2()) {
            juegoSalvarASam.setPos2(false);
            juegoSalvarASam.setPos1(true);
            btnArriba.setDisable(false);
            btnIzquierda.setDisable(true);
            AnchorPane.setTopAnchor(tu, 430.0);
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
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala2.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala3.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala4.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala5.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala6.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala7.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            });
            btnSala8.setOnAction(e -> {
                try {

                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
