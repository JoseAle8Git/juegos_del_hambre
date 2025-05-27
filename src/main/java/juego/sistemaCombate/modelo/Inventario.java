package juego.sistemaCombate.modelo;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<TipoObjeto, ObjetoCombate> objetos;

    public Inventario() {
        objetos = new HashMap<>();
        objetos.put(TipoObjeto.VENDA, new ObjetoCombate("Vendas" ,TipoObjeto.VENDA, 5));
        objetos.put(TipoObjeto.CARNE, new ObjetoCombate("Carne" ,TipoObjeto.CARNE, 2));
        objetos.put(TipoObjeto.HOJA, new ObjetoCombate("Hoja" ,TipoObjeto.HOJA, 2));
    }

    public void añadirObjeto(ObjetoCombate objeto) {
        objetos.put(objeto.getTipo(), objeto);
    }

    public ObjetoCombate getObjeto(TipoObjeto tipo) {
        return objetos.get(tipo);
    }

    public boolean usarObjeto(TipoObjeto tipo) {
        ObjetoCombate objeto = objetos.get(tipo);
        if (objeto != null && objeto.getCantidad() > 0) {
            objeto.usar();
            return true;
        }
        return false;
    }

    public boolean tieneObjetoDisponible(TipoObjeto tipo) {
        ObjetoCombate objeto = objetos.get(tipo);
        return objeto != null && objeto.getCantidad() > 0;
    }

    public void reiniciar() {
        objetos.values().forEach(o -> o.setCantidad(0));
    }
}