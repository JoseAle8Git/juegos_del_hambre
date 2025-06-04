package juego.historiaPeeta.mas;

import juego.sistemaCombate.dao.PersonajeDAO;
import juego.sistemaCombate.modelo.Enemigo;

import java.util.ArrayList;
import java.util.Random;

public class Enemigos {
    private ArrayList<Enemigo> enemigos;

    public Enemigos() {
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Glimmer"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Clove"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Comadreja"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Gloss"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Enobaria"));

        this.enemigos = listaEnemigos;
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }

    public Enemigo getEnemigoAleatorio() {
        if (enemigos.isEmpty()) return null;
        Random random = new Random();
        return enemigos.get(random.nextInt(enemigos.size()));
    }
    public void eliminarEnemigo(Enemigo enemigo) {
        enemigos.remove(enemigo);
    }
}
