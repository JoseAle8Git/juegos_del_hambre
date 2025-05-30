package juego.historiaKatniss;

import juego.historiaKatniss.controlador.Cjuego;

public class Main {
	public static void main(String[] args) {
		try {
			System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Cjuego().iniciarJuego();
	}
}
