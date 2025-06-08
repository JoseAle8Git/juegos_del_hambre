package juego.historiaEliot.controladores.cap6.tributoEliot.juegosDelHambre;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import juego.historiaEliot.controladores.cap6.tributoEliot.controlJuegosDelHambre.ControlJuegosDelHambre;
import juego.historiaEliot.mas.TributoEliot;
import juego.ranking.InsertarRanking;
import juego.ranking.RankingDAO;
import juego.sistemaCombate.modelo.TipoObjeto;

import java.util.Random;

public class ControllerMapaJuegosDelHambre {

    @FXML AnchorPane panelAP;
    @FXML ImageView mapaJuegos;
    @FXML Label zonaActual;
    @FXML Label vendasRestantes;
    @FXML Label vidaPersonaje;
    @FXML Label hambrePersonaje;
    @FXML Label sedPersonaje;
    @FXML Label mensaje;
    @FXML ProgressBar vida;
    @FXML ProgressBar hambre;
    @FXML ProgressBar sed;
    @FXML Button btnUsarVenda;
    @FXML Button btnEste;
    @FXML Button btnNorte;
    @FXML Button btnCornocopia;
    @FXML Button btnSur;
    @FXML Button btnOeste;
    @FXML Button btnCocinarCarne;
    @FXML Button btnHacerVenda;
    @FXML Button btnBuscarObjeto;
    @FXML Button btnComerCarne;
    @FXML Button btnBeberAgua;
    @FXML Button btnCazar;

    ControlJuegosDelHambre  controlJuegosDelHambre;
    TributoEliot  tributoEliot;

    @FXML private void initialize() {
        tributoEliot = TributoEliot.getInstancia();
        controlJuegosDelHambre = ControlJuegosDelHambre.getInstancia();
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            setBackground("cornocopia");
            btnCornocopia.setDisable(true);
            zonaActual.setText("Zona Cornocopia");
        } else if(controlJuegosDelHambre.isZonaSur()) {
            setBackground("sur");
            btnNorte.setDisable(true);
            btnSur.setDisable(true);
            zonaActual.setText("Zona Sur");
        } else if(controlJuegosDelHambre.isZonaOeste()) {
            setBackground("oeste");
            btnEste.setDisable(true);
            btnOeste.setDisable(true);
            zonaActual.setText("Zona Oeste");
        } else if(controlJuegosDelHambre.isZonaNorte()) {
            setBackground("norte");
            btnSur.setDisable(true);
            btnNorte.setDisable(true);
            zonaActual.setText("Zona Norte");
        } else if (controlJuegosDelHambre.isZonaEste()) {
            setBackground("este");
            btnOeste.setDisable(true);
            btnEste.setDisable(true);
            zonaActual.setText("Zona Este");
        }
        mapaJuegos.setImage(new Image(getClass().getResourceAsStream("/images/mapaJuegosDelHambre.png")));
        actualizarVida();
        actualizarHambre();
        actualizarSed();
    }

    @FXML private void usarVendas(ActionEvent e) {
        if(tributoEliot.getInventarioCombate().usarObjeto(TipoObjeto.VENDA)) {
            tributoEliot.consumirVenda();
            actualizarVida();
            vendasRestantes.setText("Vendas restantes: " + tributoEliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad());
        } else {
            mostrarAlerta("Sin vendas", "No te quedan vendas");
        }
    }
    @FXML private void irAEste(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            controlJuegosDelHambre.setZonaCornocopia(false);
            controlJuegosDelHambre.setZonaEste(true);
            btnOeste.setDisable(true);
            btnEste.setDisable(true);
            btnCornocopia.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("este");
            zonaActual.setText("Zona Este");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaNorte()) {
            controlJuegosDelHambre.setZonaNorte(false);
            controlJuegosDelHambre.setZonaEste(true);
            btnEste.setDisable(true);
            btnOeste.setDisable(true);
            btnSur.setDisable(false);
            btnNorte.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("este");
            zonaActual.setText("Zona Este");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaSur()) {
            controlJuegosDelHambre.setZonaSur(false);
            controlJuegosDelHambre.setZonaEste(true);
            btnOeste.setDisable(true);
            btnEste.setDisable(true);
            btnSur.setDisable(false);
            btnNorte.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("este");
            zonaActual.setText("Zona Este");
            pasarDeZonaAZona();
            irACombate();
        }
    }
    @FXML private void irANorte(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            controlJuegosDelHambre.setZonaCornocopia(false);
            controlJuegosDelHambre.setZonaNorte(true);
            btnSur.setDisable(true);
            btnNorte.setDisable(true);
            btnCornocopia.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("norte");
            zonaActual.setText("Zona Norte");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaEste()) {
            controlJuegosDelHambre.setZonaEste(false);
            controlJuegosDelHambre.setZonaNorte(true);
            btnNorte.setDisable(true);
            btnSur.setDisable(true);
            btnEste.setDisable(false);
            btnOeste.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("norte");
            zonaActual.setText("Zona Norte");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaOeste()) {
            controlJuegosDelHambre.setZonaOeste(false);
            controlJuegosDelHambre.setZonaNorte(true);
            btnNorte.setDisable(true);
            btnSur.setDisable(true);
            btnEste.setDisable(false);
            btnOeste.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("norte");
            zonaActual.setText("Zona Norte");
            pasarDeZonaAZona();
            irACombate();
        }

    }
    @FXML private void irACornocopia(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaNorte()) {
            controlJuegosDelHambre.setZonaNorte(false);
            controlJuegosDelHambre.setZonaCornocopia(true);
            btnNorte.setDisable(false);
            btnCornocopia.setDisable(true);
            btnSur.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("cornocopia");
            zonaActual.setText("Zona Cornocopia");
            pasarDeZonaAZona();
            irACombate();
            if(controlJuegosDelHambre.combateFinal() == 8) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateCato.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            }
        } else if(controlJuegosDelHambre.isZonaSur()) {
            controlJuegosDelHambre.setZonaSur(false);
            controlJuegosDelHambre.setZonaCornocopia(true);
            btnNorte.setDisable(false);
            btnSur.setDisable(false);
            btnCornocopia.setDisable(true);
            btnCazar.setDisable(false);
            setBackground("cornocopia");
            zonaActual.setText("Zona Cornocopia");
            pasarDeZonaAZona();
            irACombate();
            if(controlJuegosDelHambre.combateFinal() == 8) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateCato.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            }
        } else if(controlJuegosDelHambre.isZonaEste()) {
            controlJuegosDelHambre.setZonaEste(false);
            controlJuegosDelHambre.setZonaCornocopia(true);
            btnOeste.setDisable(false);
            btnEste.setDisable(false);
            btnCornocopia.setDisable(true);
            btnCazar.setDisable(false);
            setBackground("cornocopia");
            zonaActual.setText("Zona Cornocopia");
            pasarDeZonaAZona();
            irACombate();
            if(controlJuegosDelHambre.combateFinal() == 8) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateCato.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            }
        } else if(controlJuegosDelHambre.isZonaOeste()) {
            controlJuegosDelHambre.setZonaOeste(false);
            controlJuegosDelHambre.setZonaCornocopia(true);
            btnOeste.setDisable(false);
            btnEste.setDisable(false);
            btnCornocopia.setDisable(true);
            btnCazar.setDisable(false);
            setBackground("cornocopia");
            zonaActual.setText("Zona Cornocopia");
            pasarDeZonaAZona();
            irACombate();
            if(controlJuegosDelHambre.combateFinal() == 8) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateCato.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            }
        }
    }
    @FXML private void irASur(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            controlJuegosDelHambre.setZonaCornocopia(false);
            controlJuegosDelHambre.setZonaSur(true);
            btnSur.setDisable(true);
            btnNorte.setDisable(true);
            btnCornocopia.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("sur");
            zonaActual.setText("Zona Sur");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaEste()) {
            controlJuegosDelHambre.setZonaEste(false);
            controlJuegosDelHambre.setZonaSur(true);
            btnNorte.setDisable(true);
            btnSur.setDisable(true);
            btnEste.setDisable(false);
            btnOeste.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("sur");
            zonaActual.setText("Zona Sur");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaOeste()) {
            controlJuegosDelHambre.setZonaOeste(false);
            controlJuegosDelHambre.setZonaSur(true);
            btnNorte.setDisable(true);
            btnSur.setDisable(true);
            btnEste.setDisable(false);
            btnOeste.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("sur");
            zonaActual.setText("Zona Sur");
            pasarDeZonaAZona();
            irACombate();
        }
    }
    @FXML private void irAOeste(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            controlJuegosDelHambre.setZonaCornocopia(false);
            controlJuegosDelHambre.setZonaOeste(true);
            btnOeste.setDisable(true);
            btnEste.setDisable(true);
            btnCornocopia.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("oeste");
            zonaActual.setText("Zona Oeste");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaNorte()) {
            controlJuegosDelHambre.setZonaNorte(false);
            controlJuegosDelHambre.setZonaOeste(true);
            btnOeste.setDisable(true);
            btnEste.setDisable(true);
            btnSur.setDisable(false);
            btnNorte.setDisable(false);
            btnCazar.setDisable(false);
            setBackground("oeste");
            zonaActual.setText("Zona Oeste");
            pasarDeZonaAZona();
            irACombate();
        } else if(controlJuegosDelHambre.isZonaSur()) {
            controlJuegosDelHambre.setZonaSur(false);
            controlJuegosDelHambre.setZonaOeste(true);
            btnSur.setDisable(false);
            btnNorte.setDisable(false);
            btnEste.setDisable(true);
            btnOeste.setDisable(true);
            btnCazar.setDisable(false);
            setBackground("oeste");
            zonaActual.setText("Zona Oeste");
            pasarDeZonaAZona();
            irACombate();
        }
    }
    @FXML private void cocinarCarne(ActionEvent e) {
        if(tributoEliot.getInventarioGeneral().get("Palos") > 0 && tributoEliot.getInventarioGeneral().get("Yesca") > 0) {
            tributoEliot.consumirObjeto("Palos");
            tributoEliot.consumirObjeto("Yesca");
            tributoEliot.consumirObjeto("Palos");
            tributoEliot.consumirObjeto("Yesca");
            tributoEliot.consumirObjeto("Palos");
            tributoEliot.consumirObjeto("Yesca");
            tributoEliot.consumirObjeto("Carne cruda");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/cocinarCarne.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) panelAP.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
        } else {
            mostrarAlerta("Materiales insuficientes", "No tienes suficientes materiales para cocinar la carne");
        }
    }
    @FXML private void hacerVenda(ActionEvent e) {
        if(tributoEliot.getInventarioGeneral().get("Hierba antiséptica") > 0 && tributoEliot.getInventarioGeneral().get("Tela") > 0 && tributoEliot.getInventarioGeneral().get("Cuerda") > 0) {
            tributoEliot.consumirObjeto("Tela");
            tributoEliot.consumirObjeto("Cuerda");
            tributoEliot.consumirObjeto("Hierba antiséptica");
            tributoEliot.consumirObjeto("Tela");
            tributoEliot.consumirObjeto("Cuerda");
            tributoEliot.consumirObjeto("Hierba antiséptica");
            tributoEliot.consumirObjeto("Tela");
            tributoEliot.consumirObjeto("Cuerda");
            tributoEliot.consumirObjeto("Hierba antiséptica");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/hacerVenda.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) panelAP.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
        } else {
            mostrarAlerta("Materiales insuficientes", "No tienes suficientes materiales para hacer una venda");
        }
    }
    @FXML private void buscarObjeto(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaCornocopia() && controlJuegosDelHambre.getCantidadRecursosCornocopia() > 0) {
            Random random = new Random();
            mensaje.setText("");
            int num = random.nextInt(10) + 1;
            if(num == 1) {
                tributoEliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).setCantidad();
                mensaje.setText("1 venda obtenida");
                vendasRestantes.setText("Vendas restantes:" + tributoEliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad());
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 2) {
                tributoEliot.getInventarioCombate().getObjeto(TipoObjeto.HOJA).setCantidad();
                mensaje.setText("1 hoja obtenida");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 3) {
                tributoEliot.getInventarioCombate().getObjeto(TipoObjeto.CARNE).setCantidad();
                mensaje.setText("1 de carne cocinada obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 4) {
                tributoEliot.getInventarioGeneral().put("Agua", tributoEliot.getInventarioGeneral().getOrDefault("Agua", 0) + 1);
                mensaje.setText("1 de agua obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 5) {
                tributoEliot.getInventarioGeneral().put("Palos", tributoEliot.getInventarioGeneral().getOrDefault("Palos", 0) + 1);
                mensaje.setText("1 palo cocinada obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 6) {
                tributoEliot.getInventarioGeneral().put("Yesca", tributoEliot.getInventarioGeneral().getOrDefault("Yesca", 0) + 1);
                mensaje.setText("1 de yesca obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 7) {
                tributoEliot.getInventarioGeneral().put("Cuerda", tributoEliot.getInventarioGeneral().getOrDefault("Cuerda", 0) + 1);
                mensaje.setText("1 de cuerda obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 8) {
                tributoEliot.getInventarioGeneral().put("Tela", tributoEliot.getInventarioGeneral().getOrDefault("Tela", 0) + 1);
                mensaje.setText("1 de tela obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else if(num == 9) {
                tributoEliot.getInventarioGeneral().put("Hierba antiséptica", tributoEliot.getInventarioGeneral().getOrDefault("Hierba antisética", 0) + 1);
                mensaje.setText("1 de hierba antiséptica obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            } else {
                tributoEliot.getInventarioGeneral().put("Carne cruda", tributoEliot.getInventarioGeneral().getOrDefault("Carne cruda", 0) + 1);
                mensaje.setText("1 de carne cruda obtenido");
                controlJuegosDelHambre.setCantidadRecursosCornocopia();
            }
        } else if(controlJuegosDelHambre.isZonaNorte() && controlJuegosDelHambre.getCantidadRecursosNorte() > 0) {
            Random random = new Random();
            mensaje.setText("");
            int num = random.nextInt(3) + 1;
            if(num == 1) {
                tributoEliot.getInventarioGeneral().put("Agua", tributoEliot.getInventarioGeneral().getOrDefault("Agua", 0) + 1);
                mensaje.setText("1 de agua obtenido");
                controlJuegosDelHambre.setCantidadRecursosNorte();
            } else if(num == 2) {
                tributoEliot.getInventarioGeneral().put("Palos", tributoEliot.getInventarioGeneral().getOrDefault("Palos", 0) + 1);
                mensaje.setText("1 palo cocinada obtenido");
                controlJuegosDelHambre.setCantidadRecursosNorte();
            } else {
                tributoEliot.getInventarioGeneral().put("Hierba antiséptica", tributoEliot.getInventarioGeneral().getOrDefault("Hierba antisética", 0) + 1);
                mensaje.setText("1 de hierba antiséptica obtenido");
                controlJuegosDelHambre.setCantidadRecursosNorte();
            }
        } else if(controlJuegosDelHambre.isZonaSur() && controlJuegosDelHambre.getCantidadRecursosSur() > 0) {
            Random random = new Random();
            mensaje.setText("");
            int num = random.nextInt(3) + 1;
            if(num == 1) {
                tributoEliot.getInventarioGeneral().put("Palos", tributoEliot.getInventarioGeneral().getOrDefault("Palos", 0) + 1);
                mensaje.setText("1 palo cocinada obtenido");
                controlJuegosDelHambre.setCantidadRecursosSur();
            } else if(num == 2) {
                tributoEliot.getInventarioGeneral().put("Yesca", tributoEliot.getInventarioGeneral().getOrDefault("Yesca", 0) + 1);
                mensaje.setText("1 de yesca obtenido");
                controlJuegosDelHambre.setCantidadRecursosSur();
            } else {
                tributoEliot.getInventarioGeneral().put("Tela", tributoEliot.getInventarioGeneral().getOrDefault("Tela", 0) + 1);
                mensaje.setText("1 de tela obtenido");
                controlJuegosDelHambre.setCantidadRecursosSur();
            }
        } else if(controlJuegosDelHambre.isZonaEste() && controlJuegosDelHambre.getCantidadRecursosEste() > 0) {
            Random random = new Random();
            mensaje.setText("");
            int num = random.nextInt(3) + 1;
            if(num == 1) {
                tributoEliot.getInventarioGeneral().put("Cuerda", tributoEliot.getInventarioGeneral().getOrDefault("Cuerda", 0) + 1);
                mensaje.setText("1 de cuerda obtenido");
                controlJuegosDelHambre.setCantidadRecursosEste();
            } else if(num == 2) {
                tributoEliot.getInventarioGeneral().put("Yesca", tributoEliot.getInventarioGeneral().getOrDefault("Yesca", 0) + 1);
                mensaje.setText("1 de yesca obtenido");
                controlJuegosDelHambre.setCantidadRecursosEste();
            } else {
                tributoEliot.getInventarioGeneral().put("Tela", tributoEliot.getInventarioGeneral().getOrDefault("Tela", 0) + 1);
                mensaje.setText("1 de tela obtenido");
                controlJuegosDelHambre.setCantidadRecursosEste();
            }
        } else if(controlJuegosDelHambre.isZonaOeste() && controlJuegosDelHambre.getCantidadRecursosOeste() > 0) {
            Random random = new Random();
            mensaje.setText("");
            int num = random.nextInt(2) + 1;
            if(num == 1) {
                tributoEliot.getInventarioGeneral().put("Cuerda", tributoEliot.getInventarioGeneral().getOrDefault("Cuerda", 0) + 1);
                mensaje.setText("1 de cuerda obtenido");
                controlJuegosDelHambre.setCantidadRecursosOeste();
            } else {
                tributoEliot.getInventarioGeneral().put("Palos", tributoEliot.getInventarioGeneral().getOrDefault("Palos", 0) + 1);
                mensaje.setText("1 palo cocinada obtenido");
                controlJuegosDelHambre.setCantidadRecursosOeste();
            }
        } else {
            mostrarAlerta("Sin recursos", "En esta zona ya no quedan recursos");
        }
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), mensaje);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), mensaje);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        SequentialTransition seq = new SequentialTransition(fadeIn, pause, fadeOut);
        seq.setOnFinished(event -> {

        });
        seq.play();
    }
    @FXML private void cazar(ActionEvent e) {
        if(controlJuegosDelHambre.isZonaCornocopia()) {
            mostrarAlerta("Sin animales", "No hay animales por esta zona");
            btnCazar.setDisable(true);
        } else if(controlJuegosDelHambre.isZonaNorte()) {
            btnCazar.setDisable(true);
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            if(num <= 35) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/cazar.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else {
                mostrarAlerta("Sin animales", "No hay animales por esta zona");
            }
        } else if(controlJuegosDelHambre.isZonaSur()) {
            btnCazar.setDisable(true);
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            if(num <= 20) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/cazar.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else {
                mostrarAlerta("Sin animales", "No hay animales por esta zona");
            }
        } else if(controlJuegosDelHambre.isZonaEste()) {
            btnCazar.setDisable(true);
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            if(num <= 5) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/cazar.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else {
                mostrarAlerta("Sin animales", "No hay animales por esta zona");
            }
        } else if(controlJuegosDelHambre.isZonaOeste()) {
            btnCazar.setDisable(true);
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            if(num <= 45) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/cazar.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else {
                mostrarAlerta("Sin animales", "No hay animales por esta zona");
            }
        }
        btnCazar.setDisable(true);
    }
    @FXML private void comerCarne(ActionEvent e) {
        if(tributoEliot.getInventarioGeneral().get("Carne cocinada") > 0) {
            tributoEliot.setHambre(30);
            tributoEliot.consumirObjeto("Carne cocinada");
            actualizarHambre();
        } else {
            mostrarAlerta("Sin Comida", "No te queda más carne cocinada");
        }
    }
    @FXML private void beberAgua(ActionEvent e) {
        if(tributoEliot.getInventarioGeneral().get("Agua") > 0) {
            tributoEliot.setSed(30);
            tributoEliot.consumirObjeto("Agua");
            actualizarSed();
        } else {
            mostrarAlerta("Sin Agua", "No te queda más agua");
        }
    }
    private void actualizarVida() {
        vida.setProgress((double) tributoEliot.getVidaActual() / tributoEliot.getClase().getVidaMaxima());
        vidaPersonaje.setText(tributoEliot.getVidaActual() + "/" + tributoEliot.getClase().getVidaMaxima());
        vendasRestantes.setText("Vendas restantes: " + tributoEliot.getInventarioCombate().getObjeto(TipoObjeto.VENDA).getCantidad());
    }
    private void actualizarHambre() {
        hambre.setProgress((double) tributoEliot.getHambre() / 100);
        hambrePersonaje.setText(tributoEliot.getHambre() + "/" + 100);
    }
    private void actualizarSed() {
        sed.setProgress((double) tributoEliot.getSed() / 100);
        sedPersonaje.setText(tributoEliot.getSed() + "/" + 100);
    }
    private void setBackground(String lugar) {
        panelAP.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image(getClass().getResource("/images/" + lugar + ".png").toExternalForm()),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
                        )
                )
        );
    }
    private void irACombate() {
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        if(num <= 35) {
            if(controlJuegosDelHambre.getEnemigos().get(0) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateGlimmer.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(1) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateClove.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(2) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateGloss.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(3) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateComadreja.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(4) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateMarvel.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(5) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateThresh.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(6) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateRue.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            } else if(controlJuegosDelHambre.getEnemigos().get(7) < 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historiaEliot/Cap6/tributoEliot/juegosDelHambre/combateEnobaria.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) panelAP.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch(Exception ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
                }
            }
        }
    }

    private void pasarDeZonaAZona() {
        if(tributoEliot.getHambre() == 0 || tributoEliot.getSed() == 0) {
            tributoEliot.perderVida();
            actualizarVida();
        } else if(tributoEliot.estaVivo()) {
            tributoEliot.setHambre(-10);
            tributoEliot.setSed(-10);
            actualizarHambre();
            actualizarSed();
        }
        if(!tributoEliot.estaVivo()) {
            InsertarRanking ranking = InsertarRanking.crearInstancia();
            ranking.setFinalJuego("Muerte");
            RankingDAO.insertarRanking(ranking);
            mostrarAlerta("Game Over", "Has muestro de hambre y sed");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) panelAP.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Error", "No se pudo mostrar la siguiente vista");
            }
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
