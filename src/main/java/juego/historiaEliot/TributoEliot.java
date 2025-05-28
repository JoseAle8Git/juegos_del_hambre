package juego.historiaEliot;

import juego.sistemaCombate.modelo.ClaseCombate;
import juego.sistemaCombate.modelo.Inventario;

import java.util.HashMap;
import java.util.Map;

public class TributoEliot {

    private static TributoEliot instancia;

    private String nombre;
    private ClaseCombate clase;
    private int vidaActual;
    private int hambre = 100;
    private int sed = 100;

    private Inventario inventarioCombate = new Inventario();
    private Map<String, Integer> inventarioGeneral = new HashMap<>();

    private TributoEliot(ClaseCombate clase) {
        this.nombre = "Eliot";
        this.clase = clase;
        this.vidaActual = clase.getVidaMaxima();

        for(String obj : new String[] {"Carne cocinada", "Carne cruda", "Agua", "Vendas", "Hojas", "Palos", "Yesca", "Cuerda", "Tela", "Hierba antiséptica"
        }) {
            inventarioGeneral.put(obj, 0);
        }
    }

    public static void crearInstancia(ClaseCombate clase) {
        if(instancia == null) instancia = new  TributoEliot(clase);
    }

    public static TributoEliot getInstancia() {
        return instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public ClaseCombate getClase() {
        return clase;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int cantidad) {
        hambre = Math.max(0, Math.min(100, cantidad));
    }

    public int getSed() {
        return sed;
    }

    public void setSed(int cantidad) {
        sed = Math.max(0, Math.min(100, cantidad));
    }

    public Map<String, Integer> getInventarioGeneral() {
        return inventarioGeneral;
    }

    public void anadirInventario(String nombre, int cantidad) {
        inventarioGeneral.put(nombre, inventarioGeneral.getOrDefault(nombre, 0) + cantidad);
    }

    public Inventario getInventarioComando() {
        return inventarioCombate;
    }

    public void curar(int cantidad) {
        vidaActual = Math.min(vidaActual + cantidad, clase.getVidaMaxima());
    }

    public boolean consumirObjeto(String nombre) {
        int actual = inventarioGeneral.getOrDefault(nombre, 0);
        if(actual > 0) {
            inventarioGeneral.put(nombre, actual - 1);
            return true;
        }
        return false;
    }

    public void restaurarEstadoCompleto() {
        this.vidaActual = clase.getVidaMaxima();
        this.hambre = 100;
        this.sed = 100;
        this.inventarioGeneral.replaceAll((k, v) -> 0);
        this.inventarioCombate.reiniciar();
    }

}
