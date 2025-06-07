package juego.historiaPeeta.mas;

import javafx.scene.image.Image;
import juego.sistemaCombate.dao.PersonajeDAO;
import juego.sistemaCombate.modelo.Enemigo;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Random;

public class Enemigos {
    private ArrayList<Enemigo> enemigos;
    private ArrayList<String> rutasImagenes;

    public Enemigos() {
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
        ArrayList<String> rutasImagenes = new ArrayList<>();

        rutasImagenes.add("/images/imagesPeeta/glimmer.png");
        rutasImagenes.add("/images/imagesPeeta/clove.png");
        rutasImagenes.add("/images/imagesPeeta/comadreja.png");
        rutasImagenes.add("/images/imagesPeeta/gloss.png");
        rutasImagenes.add("/images/imagesPeeta/enobaria.png");

        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Glimmer"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Clove"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Comadreja"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Gloss"));
        listaEnemigos.add((Enemigo) new PersonajeDAO().cargarPersonajePorNombre("Enobaria"));


        this.rutasImagenes = rutasImagenes;
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
        int i = 0;
        while (enemigo != enemigos.get(i)) {
            i++;
        }
        rutasImagenes.remove(i);
        enemigos.remove(i);
    }

    public String getRutaImagen(Enemigo enemigo) {
        int i = 0;
        while (enemigo != enemigos.get(i)) {
            i++;
        }
        return rutasImagenes.get(i);
    }
}
