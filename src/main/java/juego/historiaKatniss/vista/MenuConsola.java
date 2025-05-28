package juego.historiaKatniss.vista;

import java.util.Scanner;

public class MenuConsola {
    private static Scanner scanner = new Scanner(System.in);

    public static String pedirTexto(String mensaje) {
        Narrador.mostrar(mensaje);
        return scanner.nextLine();
    }

    public static int menuOpciones(String titulo, String... opciones) {
        Narrador.titulo(titulo);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }

        int eleccion = 0;
        while (eleccion < 1 || eleccion > opciones.length) {
            System.out.print("Elige una opción (1-" + opciones.length + "): ");
            try {
                eleccion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
        return eleccion;
    }
}
