package juego.historiaKatniss.controlador;

import juego.historiaKatniss.modelo.*;
import juego.historiaKatniss.vista.*;

public class Cpuzzle {

	// CAPITULO 1
	public static void opcionCazar(Jugador jugador) {
		Narrador.mostrar("Te colocas en posición con tu arco. El bosque está en silencio.");
		Narrador.mostrar("Escuchas un crujido entre los arbustos...");

		int probabilidad = (int) (Math.random() * 100);

		if (probabilidad < 60) {
			Narrador.mostrar("¡Disparas y logras cazar un conejo!");
			jugador.getInventario().añadirObjeto("Carne");
			jugador.añadirPuntos(15);
			Narrador.mostrar("Obtienes carne fresca para Prim.");
		} else if (probabilidad < 90) {
			Narrador.mostrar("Disparas... pero fallas. El animal escapa corriendo.");
			Narrador.mostrar("No has conseguido nada esta vez.");
		} else {
			Narrador.mostrar("Tu pie pisa algo... ¡una pequeña trampa del Capitolio olvidada!");
			jugador.recibirDaño(10);
			Narrador.mostrar("Te haces daño en la pierna (-10 de vida).");
		}
	}

	public static void acertijoCaja(Jugador jugador) {
		Narrador.mostrar("Caja: 'Tengo agujas pero no coso, tengo números pero no sumo... ¿qué soy?'");
		String respuesta = MenuConsola.pedirTexto("Tu respuesta:").toLowerCase();

		if (respuesta.contains("reloj")) {
			Narrador.mostrar("¡Correcto! Encuentras carne seca y una medicina dentro.");
			jugador.getInventario().añadirObjeto("Carne");
			jugador.getInventario().añadirObjeto("Medicina");
			jugador.añadirPuntos(25);
		} else {
			Narrador.mostrar("La caja explota con tinta azul. Quedas pintada como un pitufo.");
			jugador.añadirPuntos(5);
		}
	}

	// CAPITULO 2

	public static void repetirEleccionVestuario(Jugador jugador) {
		int nuevaEleccion = MenuConsola.menuOpciones("Elige otro vestuario:",
				"✨ Vestido largo elegante y oscuro — clásico, sobrio",
				"🧥 Una ropa sencilla, práctica y cómoda — sin destacar");

		switch (nuevaEleccion) {
		case 1 -> {
			Narrador.mostrar("✦ Caminas con paso firme, elegante y discreta. Transmites confianza.");
			jugador.añadirPrestigio(15);
			jugador.añadirPuntos(10);
		}
		case 2 -> {
			Narrador.mostrar("✦ No llamas la atención, pero estás cómoda. Algunos lo ven como un gesto de humildad.");
			jugador.añadirPrestigio(5);
			jugador.añadirPuntos(5);
		}
		}
	}

	public static void acertijoEffie(Jugador jugador) {
		Narrador.mostrar("✦ Antes del desfile, Effie Trinket te observa con una sonrisa inquisitiva.");
		Narrador.mostrar("✦ 'Veamos si tienes algo más que una cara bonita, querida', dice con tono burlón.");
		Narrador.mostrar("✦ Te lanza un acertijo:");

		Narrador.mostrar("🧠 'Cuanto más quitas, más grande se hace. ¿Qué es?'");

		String respuesta = MenuConsola.pedirTexto("Tu respuesta:").toLowerCase();

		if (respuesta.contains("agujero") || respuesta.contains("hueco")) {
			Narrador.mostrar("✦ Effie levanta una ceja, impresionada. 'Bueno... no esperaba menos.'");
			Narrador.mostrar("✦ Ganas prestigio entre los organizadores.");
			jugador.añadirPrestigio(20);
			jugador.añadirPuntos(15);
		} else {
			Narrador.mostrar("✦ Effie suspira: 'Oh, qué decepción. Esperaba más brillantez de ti.'");
			jugador.añadirPuntos(5);
		}
	}

}