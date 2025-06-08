package juego.historiaEliot.mas;

import juego.sistemaCombate.modelo.*;

import java.util.HashMap;
import java.util.Map;

public class TributoSam {

    private static TributoSam instancia;

    private String nombre;
    private ClaseCombate clase;
    private Soldado soldado;
    private int vidaActual;

    private Inventario inventarioCombate = new Inventario();
    private Map<String, Integer> inventarioGeneral = new HashMap<>();

    private TributoSam() {
        this.nombre = "Eliot";
        this.soldado = new Soldado();
        this.clase = soldado;
        this.vidaActual = soldado.getVidaMaxima();

        inventarioGeneral.put("Vendas", 5);
    }

    public static void crearInstancia() {
        if(instancia == null) instancia = new TributoSam();
    }

    public static TributoSam getInstancia() {
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
        this.vidaActual = Math.min(vidaActual, soldado.getVidaMaxima());
    }

    public Map<String, Integer> getInventarioGeneral() {
        return inventarioGeneral;
    }

    public void anadirInventario(int cantidad) {
        ObjetoCombate objeto = new ObjetoCombate("Venda", TipoObjeto.VENDA, cantidad);
        inventarioCombate.anadirObjeto(objeto);
    }

    public Inventario getInventarioCombate() {
        return inventarioCombate;
    }

    public void curar(int cantidad) {
        vidaActual = Math.min(vidaActual + cantidad, soldado.getVidaMaxima());
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


}
