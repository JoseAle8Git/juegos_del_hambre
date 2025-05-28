package juego.historiaKatniss.vista;

public class Narrador {

    public static void mostrar(String texto) {
        System.out.println(texto);
    }

    public static void separador() {
        System.out.println("─────────────────────────────────────────────────────────────");
    }

    public static void titulo(String texto) {
        separador();
        System.out.println("★ " + texto.toUpperCase() + " ★");
        separador();
    }
}
