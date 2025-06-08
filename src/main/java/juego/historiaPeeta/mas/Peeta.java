package juego.historiaPeeta.mas;

import juego.sistemaCombate.modelo.*;

import java.util.HashMap;
import java.util.Map;

public class Peeta {

    private static Peeta instancia;

    private String nombre;
    private ClaseCombate clase;
    private int vidaActual;
    private Enemigos enemigos;

    private Inventario inventarioCombate = new Inventario();
    private Map<String, Integer> inventarioGeneral = new HashMap<>();
    private boolean[] zonasVisitadas;

    private Peeta() {
        boolean[] zonasVisitadas = {false, false, false};
        this.nombre = "Peeta";
        this.clase = new Normal();
        this.vidaActual = clase.getVidaMaxima();
        this.enemigos = new Enemigos();

        for(String obj : new String[] {"Carne", "Vendas", "Hojas",}) {
            inventarioGeneral.put(obj, 0);
        }

        this.zonasVisitadas = zonasVisitadas;
    }

//    private Peeta(ClaseCombate clase) {
//        this.nombre = "Peeta";
//        this.clase = clase;
//        this.vidaActual = clase.getVidaMaxima();
//
//        for(String obj : new String[] {"Carne", "Vendas", "Hojas",}) {
//            inventarioGeneral.put(obj, 0);
//        }
//    }
    public static void crearInstancia() {
        if(instancia == null) instancia = new Peeta();
    }
    public static Peeta getInstancia() {
        return instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public ClaseCombate getClase() {
        return clase;
    }

    public void setClase(String nombreClase) {
        switch (nombreClase) {
            case ("soldado") -> this.clase = new Soldado();
            case ("guerrero") -> this.clase = new Guerrero();
            case ("arquero") -> this.clase = new Arquero();
            case ("cazador") -> this.clase = new Cazador();
            case ("asesino") -> this.clase = new Asesino();
        }
        this.vidaActual = clase.getVidaMaxima();
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = Math.min(vidaActual, clase.getVidaMaxima());
    }

    public Enemigos getEnemigos() {
        return enemigos;
    }

    public void anadirInventario(int cantidad) {
        inventarioGeneral.put("Vendas", inventarioGeneral.getOrDefault("Vendas", 0) + cantidad);
    }

    public Inventario getInventarioCombate() {
        return inventarioCombate;
    }

    public void curar(int cantidad) {
        vidaActual = Math.min(vidaActual + cantidad, clase.getVidaMaxima());
    }

    public boolean consumirVenda() {
        int vendasActuales = inventarioGeneral.getOrDefault("Vendas", 0);
        if (vendasActuales > 0) {
            inventarioGeneral.put("Vendas", vendasActuales - 1);
            curar(30);
            return true;
        }
        return false;
    }

    public void setInventarioCombate(Inventario inventario) {
        this.inventarioCombate = inventario;
    }

    public boolean[] getZonasVisitadas() {
        return zonasVisitadas;
    }

    public void setZonasVisitadas(boolean[] zonasVisitadas) {
        this.zonasVisitadas = zonasVisitadas;
    }
}
