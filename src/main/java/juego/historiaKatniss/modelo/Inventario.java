package juego.historiaKatniss.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<String> objetos = new ArrayList<>();

    public void añadirObjeto(String objeto) {
        objetos.add(objeto);
    }

    public List<String> getObjetos() {
        return objetos;
    }
}
