package juego.sistemaCombate.modelo;

import java.util.List;
import java.util.Scanner;

public class Jugador extends Personaje {

    private boolean consumidoCarneEsteTurno = false;
    private boolean consumidoHojaEsteTurno = false;

    public Jugador(String nombre, ClaseCombate clase) {
        super(nombre, clase);
    }

    public Jugador(String nombre, ClaseCombate clase, Inventario inventario) {
        super(nombre, clase);
        this.inventario = inventario;
    }

    @Override
    public Ataque elegirAtaque() {
        Scanner scanner = new Scanner(System.in);
        List<Ataque> ataques = clase.getAtaques();

        System.out.println("\nElige tu ataque:");
        for (int i = 0; i < ataques.size(); i++) {
            Ataque atk = ataques.get(i);
            System.out.println((i + 1) + ". " + atk.getNombre() +
                    " (Daño: " + atk.getDanoBase() +
                    ", Acierto: " + (int) (atk.getProbAcierto() * 100) + "%" +
                    ", Efecto: " + atk.getEfecto() + ")");
        }

        int opcion = -1;
        while (opcion < 1 || opcion > ataques.size()) {
            System.out.print("Introduce un número entre 1 y " + ataques.size() + ": ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        return ataques.get(opcion - 1);
    }

    @Override
    public boolean esJugador() {
        return true;
    }

    public void setConsumidoCarneEsteTurno(boolean valor) {
        this.consumidoCarneEsteTurno = valor;
    }

    public void setConsumidoHojaEsteTurno(boolean valor) {
        this.consumidoHojaEsteTurno = valor;
    }

    public boolean haConsumidoCarneEsteTurno() {
        return consumidoCarneEsteTurno;
    }

    public boolean haConsumidoHojaEsteTurno() {
        return consumidoHojaEsteTurno;
    }

    public void resetConsumiblesTurno() {
        consumidoCarneEsteTurno = false;
        consumidoHojaEsteTurno = false;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
