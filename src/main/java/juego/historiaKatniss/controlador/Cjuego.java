package juego.historiaKatniss.controlador;

import juego.historiaKatniss.modelo.*;
import juego.historiaKatniss.vista.*;
import juego.sistemaCombate.dao.ClaseCombateDAO;
import juego.sistemaCombate.modelo.ClaseCombate;

import java.util.Random;

public class Cjuego {
	private int combatesRealizados = 0;
	private int tributosRestantes = 24;
	private Jugador jugador;
	private boolean juegoActivo = true;

	public void iniciarJuego() {
		String nombre = MenuConsola.pedirTexto("Introduce tu nombre:");
		jugador = new Jugador(nombre);

		juegoActivo = true;
		capitulo1Prologo();
		if (!juegoActivo)
			return;

		capitulo2Cosecha();
		if (!juegoActivo)
			return;

		capitulo3CaminoAlCapitolio();
		if (!juegoActivo)
			return;

		capitulo4InicioArena();
		if (!juegoActivo)
			return;

		desarrollarArenaYCombates();
		if (!juegoActivo)
			return;

		capitulo5FaseFinal();
		if (!juegoActivo)
			return;
	}



	public void capitulo1Prologo() {
		Narrador.titulo("Capítulo 1: El Distrito 12 se despierta");

		Narrador.mostrar("✦ El sol apenas se asoma entre el humo de las fábricas. Te despierta el chillido de Buttercup, el gato de Prim.");
		Narrador.mostrar("✦ Tu madre prepara una infusión, pero tú sabes que no hay suficiente comida.");
		Narrador.mostrar("✦ Hoy toca decidir: ¿arriesgarte en el bosque? ¿Intercambiar en el mercado? ¿O quedarte con Prim?");

		boolean decisionTomada = false;

		while (!decisionTomada) {
			int opcion = MenuConsola.menuOpciones("¿Qué decides hacer esta mañana?", "🦌 Ir a cazar al bosque",
					"🤝 Ir al mercado negro (La Veta)", "🏠 Quedarte con Prim");

			switch (opcion) {
				case 1 -> {
					Narrador.mostrar("Te adentras en el bosque, esquivando la verja eléctrica (que como siempre, está apagada).");
					Narrador.mostrar("Tras un rato, ves un venado... pero hay una caja misteriosa colgando de un árbol.");
					int puzzle = MenuConsola.menuOpciones("¿Qué haces?", "Intentas disparar al venado",
							"Te acercas a la caja y resuelves el acertijo");

					if (puzzle == 1) {
						Cpuzzle.opcionCazar(jugador);
					} else {
						Cpuzzle.acertijoCaja(jugador);
					}
				}

				case 2 -> {
					Narrador.mostrar("Greasy Sae te mira con interés. 'Te doy medicinas para tu hermana... pero necesito que me des comida.'");

					int aceptar = MenuConsola.menuOpciones("¿Aceptas el trato?", "Sí, entrego la carne",
							"No, buscaré otra opción");

					if (aceptar == 1) {
						if (jugador.getInventario().getObjetos().contains("Carne")) {
							jugador.getInventario().getObjetos().remove("Carne");

							Narrador.mostrar("Greasy Sae asiente. 'Trato hecho. Cuida a Prim.'");
							Narrador.mostrar("✦ Vuelves corriendo a casa con la medicina y el pan en las manos.");
							Narrador.mostrar("✦ Tu madre prepara rápidamente una infusión con las medicinas.");
							Narrador.mostrar("✦ Al poco tiempo, Prim empieza a recuperar el color en su rostro.");
							Narrador.mostrar("✦ Sonríe débilmente y te dice: 'Gracias por todo, Katniss...'");
							jugador.getInventario().añadirObjeto("Medicina");
							jugador.getInventario().añadirObjeto("Pan");
							jugador.añadirPuntos(30);
							decisionTomada = true;
						} else {
							Narrador.mostrar("Greasy Sae te mira serio. 'No tienes carne, chica. Vuelve cuando la consigas.'");
						}
					} else {
						Narrador.mostrar("Decides seguir buscando otra forma de ayudar a Prim.");
					}
				}

				case 3 -> {
					Narrador.mostrar("Te sientas junto a Prim. Ella está triste, nerviosa por la Cosecha.");
					Narrador.mostrar("La abrazas. 'Todo irá bien, te lo prometo', dices sin estar convencida.");
					jugador.añadirPuntos(10);
					jugador.getInventario().añadirObjeto("Vínculo con Prim");
					decisionTomada = true;
				}
			}
		}

		Narrador.separador();
		Narrador.mostrar("✦ Esa noche, sueñas con fuego, con un sinsajo... y con Prim siendo llamada al estrado.");
	}


	// ????????????????????????????????????????
	// ? CAP?TULO 2: D?A DE LA COSECHA ?
	// ????????????????????????????????????????

	public void capitulo2Cosecha() {
		Narrador.titulo("Capítulo 2: Día de la Cosecha");

		Narrador.mostrar("✦ Las calles del Distrito 12 están llenas de niños y niñas vestidos con sus mejores ropas, aunque la mayoría están raídas por el uso.");
		Narrador.mostrar("✦ Los soldados del Capitolio vigilan cada movimiento con rostros serios. Nadie habla. Solo se oyen pasos.");
		Narrador.mostrar("✦ Effie Trinket, con su voz aguda y su peluca rosa chillón, sube al estrado con pasos teatrales.");
		Narrador.mostrar("✦ Las esferas con nombres giran lentamente. Dentro de ellas, cientos de papeles doblados con destinos marcados.");
		Narrador.mostrar("✦ Katniss respira hondo. Su madre está entre la multitud, pálida. Prim le aprieta la mano con fuerza.");
		Narrador.mostrar("✦ Effie aclara la garganta y sonríe: '¡Feliz Cosecha! ¡Y que la suerte esté siempre de su lado!'");
		Narrador.mostrar("✦ El silencio se hace espeso cuando Effie introduce la mano en la esfera de cristal...");

		// PARTE 1: Sorteo y decisión (Katniss o Prim)

		int sorteo = (int) (Math.random() * 100);

		if (sorteo < 40) {
			Narrador.mostrar("✦ Effie despliega el papel lentamente, como si disfrutara del momento.");
			Narrador.mostrar("✦ Con voz firme, lee: 'Katniss Everdeen.'");
			Narrador.mostrar("✦ Todo se congela. No puedes moverte. Sientes cómo tu corazón se hunde.");
			jugador.añadirPuntos(50);
		} else {
			Narrador.mostrar("✦ Effie despliega el papel, sonríe con emoción y dice: 'Primrose Everdeen.'");
			Narrador.mostrar("✦ Un grito ahogado recorre la plaza. Katniss queda paralizada.");
			Narrador.mostrar("✦ Prim cae de rodillas, con el rostro empapado de lágrimas.");
			Narrador.mostrar("✦ Nadie se mueve. El tiempo parece haberse detenido.");

			int decision = MenuConsola.menuOpciones("¿Qué haces?", "¡Me ofrezco voluntaria en su lugar!",
					"Me quedo paralizada, sin poder reaccionar...");

			if (decision == 1) {
				Narrador.mostrar("✦ Corres hacia el estrado empujando a los soldados: '¡Me ofrezco voluntaria! ¡Yo me ofrezco!'");
				Narrador.mostrar("✦ El público enmudece. Nadie se ha ofrecido en años.");
				Narrador.mostrar("✦ Effie parpadea, confundida, y luego asiente: 'Tenemos... una voluntaria.'");
				jugador.añadirPuntos(100);
				jugador.añadirPrestigio(25);
			} else {
				Narrador.mostrar("✦ Quieres gritar, pero tus piernas no responden. La boca se te seca.");
				Narrador.mostrar("✦ Prim sube al escenario temblando, mientras los soldados la guían con brusquedad.");
				jugador.recibirDaño(20);
				jugador.añadirPuntos(10);

				// Segunda oportunidad
				Narrador.separador();
				Narrador.mostrar("✦ Tu madre rompe en llanto. '¡Haz algo!' grita entre la multitud.");
				Narrador.mostrar("✦ El público empieza a murmurar. Sientes las miradas clavadas en ti.");
				int segundaDecision = MenuConsola.menuOpciones("¿Te arrepientes y corres al escenario?",
						"Sí, me ofrezco ahora", "No... dejo que Prim vaya");

				if (segundaDecision == 1) {
					Narrador.mostrar("✦ Con el corazón latiendo con fuerza, finalmente gritas: '¡Me ofrezco!'");
					Narrador.mostrar("✦ Un guardia duda... luego te deja subir.");
					Narrador.mostrar("✦ Effie asiente, sorprendida. Has tomado el lugar de tu hermana.");
					jugador.añadirPuntos(50);
					jugador.añadirPrestigio(15);
				} else {
					Narrador.mostrar("✦ Prim es llevada a los entrenamientos.");
					Narrador.mostrar("✦ Pasan los días. Las noticias llegan: ha sido la primera en morir.");
					Narrador.separador();
					Narrador.mostrar("☠️ FINAL: No reaccionaste. Tu hermana murió en los Juegos del Hambre.");
					juegoActivo = false;
					return;
				}
			}
		}

		// PARTE 2: Vestuario para la ceremonia

		Narrador.separador();
		Narrador.mostrar("✦ El destino está sellado. Tu viaje hacia los Juegos del Hambre está a punto de comenzar...");
		Narrador.separador();
		Narrador.mostrar("✦ Has sido llevada a la estación de preparación del Capitolio.");
		Narrador.mostrar("✦ Tu estilista, Cinna, te muestra tres opciones para la ceremonia de presentación de tributos.");
		Narrador.mostrar("✦ Debes decidir cómo quieres ser recordada al entrar en la arena...");

		int eleccionVestuario = MenuConsola.menuOpciones("Elige tu vestuario para la ceremonia:",
				"🔥 Un vestido negro decorado con fuego simulado – atrevido y desafiante",
				"🎩 Vestido largo elegante y oscuro – clásico, sobrio",
				"👕 Una ropa sencilla, práctica y cómoda – sin destacar");

		switch (eleccionVestuario) {
			case 1 -> {
				if (jugador.getPrestigio() >= 20) {
					Narrador.mostrar("✦ Cinna asiente con orgullo. 'Has causado impacto en la cosecha. Estás lista para el fuego.'");
					Narrador.mostrar("✦ Las llamas parecen bailar alrededor de tu cuerpo mientras caminas.");
					Narrador.mostrar("✦ El público del Capitolio queda fascinado con tu audacia.");
					jugador.añadirPrestigio(30);
					jugador.añadirPuntos(20);
				} else {
					Narrador.mostrar("✦ Cinna duda. 'Todavía no estás preparada para llevar algo tan poderoso.'");
					Narrador.mostrar("✦ Tendrás que elegir otro traje más discreto.");
					Cpuzzle.repetirEleccionVestuario(jugador);
				}
			}
			case 2 -> {
				Narrador.mostrar("✦ Cinna te mira y dice: 'Con esto, parecerás fuerte y serena. No llamarás tanto la atención, pero ganarás respeto.'");
				Narrador.mostrar("✦ Caminas con paso firme, elegante y discreta.");
				jugador.añadirPrestigio(15);
				jugador.añadirPuntos(10);
			}
			case 3 -> {
				Narrador.mostrar("✦ No llamas la atención, pero estás cómoda. Algunos lo ven como un gesto de humildad.");
				jugador.añadirPrestigio(5);
				jugador.añadirPuntos(5);
			}
		}

		// PARTE 3: Acertijo de Effie Trinket
		Cpuzzle.acertijoEffie(jugador);

		// PARTE 4: Despedida con Prim y tu madre

		Narrador.separador();
		Narrador.mostrar("✦ Poco antes de subir al tren hacia el Capitolio, te conceden unos minutos para despedirte.");
		Narrador.mostrar("✦ Tu madre y Prim entran corriendo a la sala de visitas. Sus ojos están llenos de lágrimas.");
		Narrador.mostrar("✦ Tu madre te abraza fuerte. No dice nada. Solo llora en silencio.");
		Narrador.mostrar("✦ Prim te toma la mano y tiembla. 'Prométeme que volverás... y que no dejarás que el Capitolio te cambie.'");

		int promesa = MenuConsola.menuOpciones("¿Qué haces?", "Prometerle a Prim que lucharás para volver",
				"Guardar silencio, no puedes mentirle");

		if (promesa == 1) {
			Narrador.mostrar("✦ Sonríes con firmeza. 'Volveré, Prim. Por ti. Por mamá.'");
			Narrador.mostrar("✦ Prim asiente, y aunque sigue llorando, parece un poco más tranquila.");
			jugador.añadirPrestigio(15);
			jugador.añadirPuntos(10);
		} else {
			Narrador.mostrar("✦ Bajas la mirada. No puedes prometer lo que no sabes si cumplirás.");
			Narrador.mostrar("✦ Tu madre te acaricia el rostro con tristeza. 'Solo... sobrevive.'");
			jugador.añadirPuntos(5);
		}

		Narrador.separador();
		Narrador.mostrar("✦ Afuera, el silbato del tren resuena como un lamento de despedida.");
		Narrador.mostrar("✦ Sales de la sala de visitas con lágrimas en los ojos y el corazón encogido.");
		Narrador.mostrar("✦ La estación del Distrito 12 está abarrotada de curiosos y cámaras del Capitolio.");
		Narrador.mostrar("✦ Subes al tren sin mirar atrás. Sabes que si lo haces, te romperás.");

		Narrador.mostrar("✦ El interior del tren es lujoso, muy distinto a la realidad que conoces.");
		Narrador.mostrar("✦ Te sientas junto a la ventana y observas cómo tu hogar se aleja lentamente.");
		Narrador.mostrar("✦ En ese instante, entiendes que tu vida jamás volverá a ser la misma.");

		Narrador.separador();
		Narrador.mostrar("✦ La aventura ha comenzado. Los Juegos del Hambre te esperan...");
	}


	// ??????????????????????????????????????????
	// ? CAP?TULO 3: CAMINO AL CAPITOLIO ?
	// ??????????????????????????????????????????

	// ?????????????????????????????????????????????
	// PARTE 1: Narrativa del viaje y ambientaci?n
	// ?????????????????????????????????????????????
	public void capitulo3CaminoAlCapitolio() {
		Narrador.titulo("Capítulo 3: Camino al Capitolio");

		Narrador.mostrar("✦ El tren del Capitolio avanza velozmente por los paisajes arrasados de Panem.");
		Narrador.mostrar("✦ A través del cristal ves cómo el Distrito 12 desaparece entre la niebla del amanecer.");
		Narrador.mostrar("✦ Todo en el interior del tren es lujo: sofás acolchados, comida caliente, duchas que funcionan.");
		Narrador.mostrar("✦ Te sientes fuera de lugar... y sola.");

		// PARTE 2: Encuentro con Peeta en el tren

		Narrador.mostrar("✦ De pronto, Peeta entra en el vagón. Tiene ojeras, pero fuerza una sonrisa.");
		Narrador.mostrar("✦ Se sienta frente a ti. La mesa está servida, pero nadie toca la comida.");
		Narrador.mostrar("✦ 'Supongo que... ya no hay vuelta atrás, ¿eh?', dice en voz baja.");
		Narrador.mostrar("✦ Te observa con cuidado, como si no supiera si puede confiar en ti.");

		// PARTE 3: Conversación con Peeta (más extensa)

		int r1 = MenuConsola.menuOpciones("¿Cómo respondes a su primer intento de hablar?", "✦ 'Podría ser peor...'", "✦ 'No estoy para hablar.'");

		if (r1 == 1) {
			jugador.modificarRelacionPeeta(10);
			Narrador.mostrar("✦ Peeta sonríe levemente. 'Eso pensaba... al menos hay comida caliente, ¿no?'");
			Narrador.mostrar("✦ Se recuesta en el respaldo, intentando romper el hielo. 'Aunque todo esto sigue siendo surrealista...'");
		} else {
			jugador.modificarRelacionPeeta(-5);
			Narrador.mostrar("✦ Peeta asiente en silencio. El ambiente se vuelve tenso.");
			Narrador.mostrar("✦ Mira por la ventana. 'Supongo que es difícil imaginar algo peor que esto.'");
		}

		Narrador.mostrar("✦ Pasan unos segundos. Peeta baja la mirada.");
		Narrador.mostrar("✦ 'En la escuela... cuando te tiraron al barro, fui yo quien te dio pan. ¿Lo recuerdas?'");

		int r2 = MenuConsola.menuOpciones("¿Qué le dices?", "✦ '¿Por qué lo hiciste?'", "✦ 'No necesito tu compasión.'");

		if (r2 == 1) {
			jugador.modificarRelacionPeeta(10);
			Narrador.mostrar("✦ 'No lo sé... fue instinto supongo', responde él. Su voz suena sincera.");
			Narrador.mostrar("✦ 'No podía soportar verte así. Era injusto.'");
		} else {
			jugador.modificarRelacionPeeta(-10);
			Narrador.mostrar("✦ 'Lo siento si te ofendí', murmura Peeta. Baja la mirada.");
			Narrador.mostrar("✦ La incomodidad entre vosotros crece.");
		}

		Narrador.mostrar("✦ Peeta se pasa una mano por el cabello y suspira.");
		Narrador.mostrar("✦ 'No sé qué va a pasar allá afuera... pero tal vez podríamos ayudarnos.'");

		int r3 = MenuConsola.menuOpciones("¿Confías en él?", "✦ 'Tal vez podríamos ayudarnos...'", "✦ 'Allá no hay aliados.'");

		if (r3 == 1) {
			jugador.modificarRelacionPeeta(10);
			Narrador.mostrar("✦ 'Me alegra oír eso', dice él. Por primera vez, parece más tranquilo.");
			Narrador.mostrar("✦ 'Yo no quiero ser tu enemigo, Katniss. Solo... sobrevivir también.'");
			Narrador.mostrar("✦ Se queda en silencio unos segundos, luego se pone de pie lentamente.");
			Narrador.mostrar("✦ 'Nos vemos más tarde...', murmura antes de salir hacia su vagón.");
			Narrador.mostrar("✦ La puerta se cierra suavemente tras él, dejándote sola con tus pensamientos.");
		} else {
			jugador.modificarRelacionPeeta(-10);
			Narrador.mostrar("✦ 'Entiendo', responde Peeta. Su tono es firme, distante.");
			Narrador.mostrar("✦ Se levanta del asiento y se dirige a su vagón sin decir nada más.");
		}
		Narrador.separador();
		Narrador.mostrar("✦ Te quedas mirando la silla vacía. La conversación te deja pensando.");
		Narrador.mostrar("✦ Quizás... Peeta no sea solo un competidor.");
		Narrador.mostrar("✦ Pero en los Juegos del Hambre, confiar puede costarte la vida.");

		// PARTE 4: Puzzle nocturno en el tren

		Narrador.separador();
		Narrador.mostrar("✦ Esa noche no puedes dormir. Los recuerdos, el miedo y la incertidumbre te abruman.");
		Narrador.mostrar("✦ Sales a caminar por el tren, buscando silencio.");
		Narrador.mostrar("✦ En un compartimento olvidado encuentras una caja metálica con símbolos del Capitolio.");
		Narrador.mostrar("✦ Una pantalla se enciende: 'Solo quien piense más allá de lo visible, verá lo invisible'.");

		Cpuzzle.acertijoDelMapa(jugador);

		// PARTE 5: Noche después del puzzle

		Narrador.separador();
		Narrador.mostrar("✦ De vuelta en tu camarote, el traqueteo del tren te arrulla mientras te tumbas en la cama.");
		Narrador.mostrar("✦ Cierra los ojos... pero las preguntas, los rostros, los miedos, todo gira en tu cabeza.");
		Narrador.mostrar("✦ Finalmente, el cansancio vence y caes en un sueño inquieto, poblado de llamas y ojos del Capitolio.");

		// PARTE 6: Propuesta de Peeta en la mañana

		Narrador.separador();
		Narrador.mostrar("✦ Unos suaves golpes en la puerta te despiertan. Es temprano, pero no demasiado.");
		Narrador.mostrar("✦ Al abrir, encuentras a Peeta en el pasillo, con el rostro serio pero tranquilo.");
		Narrador.mostrar("✦ '¿Puedo pasar? Solo será un momento...'");
		Narrador.mostrar("✦ Entra y se sienta. Hay silencio unos segundos, luego habla:");

		Narrador.mostrar("✦ 'Katniss... he estado pensando. Esta noche, en la entrevista...'");
		Narrador.mostrar("✦ '¿Y si fingimos estar enamorados? Darle al Capitolio lo que quiere ver.'");
		Narrador.mostrar("✦ 'Podríamos ganar prestigio, apoyo, patrocinadores. Sobrevivir puede depender de eso.'");

		int decisionPlan = MenuConsola.menuOpciones("¿Qué decides hacer?",
				"✦ Aceptas el plan. Puede ser útil.",
				"✦ Lo rechazas. No quieres fingir sentimientos.");

		if (decisionPlan == 1) {
			jugador.modificarRelacionPeeta(10);
			jugador.añadirPrestigio(10);
			jugador.aceptarAmorFingido();
			Narrador.mostrar("✦ Asientes. 'Si nos da ventaja... haremos lo que sea necesario.'");
			Narrador.mostrar("✦ Peeta parece aliviado. 'Gracias. Lo haremos bien, ya verás.'");
		} else {
			jugador.modificarRelacionPeeta(-10);
			Narrador.mostrar("✦ Niegas con la cabeza. 'No puedo fingir eso, Peeta. No sería justo para ninguno de los dos.'");
			Narrador.mostrar("✦ Él baja la mirada, dolido. 'Lo entiendo. Solo... quería intentarlo.'");
		}

		// PARTE 7: Preparación para la entrevista

		Narrador.separador();
		Narrador.mostrar("✦ Amanece. El tren comienza a frenar: has llegado al Capitolio.");
		Narrador.mostrar("✦ Te despiertas sobresaltada. Effie ya está en la puerta de tu camarote con una sonrisa forzada.");
		Narrador.mostrar("✦ '¡Arriba, querida! Hoy es el gran día de la entrevista con Caesar Flickerman. ¡Debemos brillar!'");
		Narrador.mostrar("✦ Rápidamente eres llevada a una sala de maquillaje, donde tu equipo de preparación trabaja sin descanso.");
		Narrador.mostrar("✦ Te limpian, peinan y visten. Todo es brillo, perfume y expectativas.");
		Narrador.mostrar("✦ Cinna aparece al final, con su mirada tranquila, y coloca una pequeña joya en tu pelo: 'Confía en ti misma.'");

		Narrador.separador();
		Narrador.mostrar("🎙️ El escenario está listo. Las cámaras del Capitolio apuntan hacia el sofá donde pronto te sentarás.");
		Narrador.mostrar("✦ En unos minutos, estarás frente a millones de personas. El espectáculo está a punto de comenzar...");

		// PARTE 8: Elección del vestuario para la entrevista

		Narrador.separador();
		Narrador.mostrar("✦ Cinna te espera en una sala privada, con varios trajes colgados tras él.");
		Narrador.mostrar("✦ 'Esta entrevista es clave, Katniss. Debemos impactar, pero siendo fieles a ti misma.'");
		Narrador.mostrar("✦ Te mira con calma y añade: '¿Quieres que yo elija, o prefieres decidir tú?'");

		int eleccionVestido = MenuConsola.menuOpciones("¿Qué decides hacer?",
				"✦ Confiar plenamente en Cinna. Él sabe lo que hace.",
				"✦ Quieres decidir tú: elegir algo rebelde y desafiante.",
				"✦ Pedir algo simple y discreto. No quieres llamar la atención.");

		switch (eleccionVestido) {
			case 1 -> {
				Narrador.mostrar("✦ 'Confío en ti, Cinna', le dices. Él asiente con una leve sonrisa.");
				Narrador.mostrar("✦ Te entrega un vestido etéreo que brilla con luces suaves como llamas bajo el agua.");
				Narrador.mostrar("✦ Cuando te presentas ante los demás, todos guardan silencio por unos segundos...");
				Narrador.mostrar("✦ El efecto ha sido impresionante.");
				jugador.añadirPrestigio(25);
				jugador.añadirPuntos(15);
			}
			case 2 -> {
				Narrador.mostrar("✦ 'Quiero algo que desafíe al Capitolio. Algo con garra.'");
				Narrador.mostrar("✦ Cinna te observa en silencio y luego responde: 'Si eso es lo que deseas... te ayudaré.'");
				Narrador.mostrar("✦ Te vistes con un atuendo oscuro, con detalles en rojo como fuego contenido.");
				Narrador.mostrar("✦ En la entrevista, algunos te admiran... otros te miran con desconfianza.");
				jugador.añadirPrestigio(10);
				jugador.añadirPuntos(10);
			}
			case 3 -> {
				Narrador.mostrar("✦ 'Prefiero algo sencillo... No quiero parecer alguien que no soy.'");
				Narrador.mostrar("✦ Cinna asiente, aunque parece decepcionado.");
				Narrador.mostrar("✦ El traje es modesto, sin adornos. Pasas desapercibida.");
				Narrador.mostrar("✦ Puede que hayas perdido la oportunidad de impresionar...");
				jugador.añadirPrestigio(-5);
				jugador.añadirPuntos(5);
			}
		}

		// PARTE 9: Entrevista con Caesar Flickerman

		Narrador.separador();
		Narrador.mostrar("📺 Las luces del escenario te ciegan por un instante. El público del Capitolio aplaude con entusiasmo.");
		Narrador.mostrar("✦ Caesar Flickerman te saluda con su risa contagiosa. '¡Nuestra valiente tributo del Distrito 12!'");
		Narrador.mostrar("✦ La entrevista se desarrolla con preguntas típicas: tu familia, tus habilidades...");

		if (jugador.getRelacionPeeta() >= 10) {
			Narrador.mostrar("✦ Entonces llega el turno de Peeta. Caesar lo mira con picardía:");
			Narrador.mostrar("✦ '¿Hay alguien especial allá afuera? ¿Alguien que te inspire a luchar?'");

			if (jugador.aceptoAmorFingido()) {
				Narrador.mostrar("✦ Peeta sonríe con timidez. 'Sí... hay alguien. Está aquí. Siempre ha estado en mi corazón.'");
				Narrador.mostrar("✦ El público enloquece. Las cámaras enfocan tu rostro.");
				Narrador.mostrar("✦ Has ganado prestigio entre los ciudadanos del Capitolio.");
				jugador.añadirPrestigio(25);
			} else {
				Narrador.mostrar("✦ Peeta duda, pero luego dice: 'No. Estoy aquí por mi familia... y por mí mismo.'");
				Narrador.mostrar("✦ La audiencia guarda silencio por un segundo, algo decepcionada.");
				jugador.añadirPrestigio(5);
			}
		} else {
			Narrador.mostrar("✦ Peeta da respuestas breves, manteniéndose distante. La audiencia no reacciona con entusiasmo.");
		}

		// PARTE 10: Inicio de los Juegos

		Narrador.separador();
		Narrador.mostrar("✦ Esa noche, después de la entrevista, el Capitolio no duerme.");
		Narrador.mostrar("✦ Las calles arden con fuegos artificiales y vítores por los tributos favoritos.");
		Narrador.mostrar("✦ Pero tú... tú estás sentada en una sala silenciosa, esperando.");

		Narrador.mostrar("✦ Cinna entra. Te ayuda a ponerte un traje oscuro, ceñido al cuerpo.");
		Narrador.mostrar("✦ 'No puedo hacer más por ti ahora', susurra. 'Solo... recuerda quién eres.'");
		Narrador.mostrar("✦ Te abraza por un instante. Luego se va sin mirar atrás.");

		Narrador.mostrar("✦ Peeta también está allí. No dice nada, pero sus ojos buscan los tuyos.");
		Narrador.mostrar("✦ Tal vez hay miedo. Tal vez hay verdad. Pero ya no hay tiempo.");

		Narrador.mostrar("✦ Un aerodeslizador te recoge. A través de la escotilla, ves el Capitolio alejándose.");
		Narrador.mostrar("✦ Las luces se desvanecen. Solo queda la oscuridad del cielo... y el rugido del destino.");

		Narrador.mostrar("✦ Finalmente, aterrizas. Te guían por pasillos metálicos hasta un tubo de lanzamiento.");
		Narrador.mostrar("✦ El cristal se cierra sobre ti. Una voz metálica resuena en tus oídos:");

		Narrador.mostrar("🎙️ 'Tributo del Distrito 12. Preparada para el inicio de los 74º Juegos del Hambre.'");

		Narrador.mostrar("✦ Tus manos tiemblan. La plataforma vibra. Comienza a elevarse.");
		Narrador.mostrar("✦ La luz inunda todo. El sol golpea tu rostro. Estás en medio de un claro... rodeada de enemigos.");

		Narrador.separador();
		Narrador.mostrar("✦ ¡COMIENZAN LOS JUEGOS DEL HAMBRE!");
		Narrador.mostrar("✦ No hay más tiempo para dudas. Sobrevivir es lo único que importa ahora.");
	}


	// CAP 4
	public void capitulo4InicioArena() {
		Narrador.titulo("Capítulo 4: Primeros latidos de la arena");

		Narrador.mostrar("✦ El mundo se detiene por un instante.");
		Narrador.mostrar("✦ Estás de pie sobre la plataforma, pero el tiempo parece congelado.");
		Narrador.mostrar("✦ Una voz artificial resuena en tu mente. No es real... es una simulación previa al inicio.");

		Narrador.mostrar("🎙️ 'TRIBUTO: Como parte de una nueva estrategia del Capitolio, puedes elegir una especialidad antes de comenzar.'");
		Narrador.mostrar("✦ Un panel virtual se despliega frente a ti, brillante, flotando en el aire.");
		Narrador.mostrar("✦ Tienes solo segundos para decidir... y esa decisión marcará tu estilo de combate.");

		// DAO para obtener clases
		ClaseCombateDAO dao = new ClaseCombateDAO();

		String[] roles = { "Normal", "Soldado", "Guerrero", "Arquero", "Cazador", "Asesino" };
		int eleccion = MenuConsola.menuOpciones("Elige tu clase de combate:", roles);

		String claseElegida = roles[eleccion - 1];
		ClaseCombate claseCombate = dao.cargarClasePorNombre(claseElegida);

		jugador.setClaseCombate(claseCombate);
		Narrador.mostrar("✦ Has elegido el rol de combate: " + claseCombate.getNombre());
		Narrador.mostrar("✦ Vida máxima: " + claseCombate.getVidaMaxima());
		Narrador.mostrar("✦ Escudo base: " + (int)(claseCombate.getEscudoPorcentaje() * 100) + "%");

		Narrador.separador();
		Narrador.mostrar("✦ Estás listo para entrar en la arena... que comiencen los Juegos.");

		jugador.añadirPuntos(15);
		Narrador.separador();
		Narrador.mostrar("✦ El panel desaparece. La vibración de la plataforma regresa.");
		Narrador.mostrar("✦ A tu alrededor, los demás tributos se alzan lentamente... estás rodeado.");
		Narrador.mostrar("✦ Frente a ti, en el centro del claro, está la Cornucopia: armas, mochilas, trampas... y muerte.");
		Narrador.mostrar("✦ El cronómetro comienza a descender. 10... 9... 8...");

}
	public void desarrollarArenaYCombates() {
		MapaJuego mapa = jugador.getMapaJuego();
		if (mapa == null) {
			mapa = new MapaJuego(false); // si no tenía mapa del puzzle
			jugador.setMapaJuego(mapa);
		}

		final int TOTAL_TRIBUTOS_INICIALES = 24;
		int tributosRestantes = TOTAL_TRIBUTOS_INICIALES - 1; // menos el jugador
		int combatesGanados = 0;

		while (tributosRestantes > 4 && combatesGanados < 4) {
			Narrador.separador();
			mapa.mostrarZonasDisponibles();
			Zona[] zonasEnum = Zona.values();
			String[] zonas = new String[zonasEnum.length];
			for (int i = 0; i < zonasEnum.length; i++) {
				zonas[i] = zonasEnum[i].name();
			}
			int opcion = MenuConsola.menuOpciones("¿A qué zona deseas ir?", zonas);
			Zona zonaElegida = Zona.valueOf(zonas[opcion - 1]);

			Narrador.mostrar("Te mueves hacia la zona: " + zonaElegida);
			Narrador.mostrar("Un tributo enemigo aparece, ¡prepárate!");

			int vidaEnemigo = 100;
			int curasEnemigo = 2;
			int curasJugador = 3;
			int vidaMaxJugador = jugador.getClaseCombate().getVidaMaxima();

			while (jugador.getVida() > 0 && vidaEnemigo > 0) {
				// TURNO DEL JUGADOR
				Narrador.separador();
				String[] acciones = { "Atacar", "Curarse (" + curasJugador + " restantes)" };
				int accion = MenuConsola.menuOpciones("Tu turno. ¿Qué quieres hacer?", acciones);
				if (accion == 1) {
					int danoJugador = new Random().nextInt(16) + 10; // 10-25
					vidaEnemigo -= danoJugador;
					Narrador.mostrar("✦ Has atacado y causado " + danoJugador + " de daño. Vida del enemigo: " + Math.max(0, vidaEnemigo));
				} else if (accion == 2) {
					if (curasJugador > 0 && jugador.getVida() < vidaMaxJugador) {
						int cura = new Random().nextInt(20) + 10;
						jugador.curar(cura);
						curasJugador--;
						Narrador.mostrar("✦ Te has curado. Vida actual: " + jugador.getVida());
					} else {
						Narrador.mostrar("✦ No puedes curarte ahora.");
					}
				}

				// TURNO DEL ENEMIGO
				if (vidaEnemigo > 0) {
					if (curasEnemigo > 0 && vidaEnemigo < 60 && Math.random() < 0.5) {
						int cura = new Random().nextInt(15) + 10;
						vidaEnemigo = Math.min(100, vidaEnemigo + cura);
						curasEnemigo--;
						Narrador.mostrar("✦ El enemigo se ha curado. Vida actual: " + vidaEnemigo);
					} else {
						int danoEnemigo = new Random().nextInt(11) + 5; //
						int escudoBloqueado = jugador.getClaseCombate().calcularEscudo(danoEnemigo);
						int danoFinal = danoEnemigo - escudoBloqueado;

						if (danoFinal > 0) {
							jugador.recibirDaño(danoFinal);
							Narrador.mostrar("✦ El enemigo te golpea con " + danoFinal + " de daño. Vida actual: " + jugador.getVida());
						} else {
							Narrador.mostrar("✦ Bloqueaste completamente el ataque enemigo.");
						}
					}
				}
			}
			if (jugador.getVida() <= 0) {
				Narrador.mostrar("Has sido derrotado. El juego ha terminado.");
				juegoActivo = false;
				return;
			} else {
				combatesGanados++;
				jugador.añadirPuntos(20);
				Narrador.mostrar("✦ ¡Has ganado el combate #" + combatesGanados + "!");
				int eliminados = new Random().nextInt(3) + 1;
				tributosRestantes = Math.max(4, tributosRestantes - eliminados);
				Narrador.mostrar("✦ Tributos restantes: " + tributosRestantes);
			}
		}
		Narrador.separador();
		Narrador.mostrar("✦ Has sobrevivido. Solo quedan 4 tributos, incluido Peeta.");
	}

	public void capitulo5FaseFinal() {
		Narrador.titulo("Capítulo 5: El final de los juegos");

		Narrador.mostrar("✦ El sol comienza a ponerse sobre la arena, tiñendo de rojo los árboles y las rocas.");
		Narrador.mostrar("✦ Solo quedan cuatro tributos: tú, Peeta y dos más. La tensión es insoportable.");
		Narrador.mostrar("✦ Peeta se acerca, su rostro manchado de sangre y sudor.");

		if (jugador.aceptoAmorFingido()) {
			Narrador.mostrar("Peeta: 'Si realmente fingimos estar enamorados... entonces ganemos como un equipo.'");
		} else {
			Narrador.mostrar("Peeta: 'No sé si confiar en ti, pero no tenemos otra opción. Vamos a separarlos y luchar 1 a 1.'");
		}

		Narrador.mostrar("✦ Decidís que cada uno enfrentará a un tributo. Si ambos ganan, podrían ser los ganadores conjuntos del Distrito 12.");
		Narrador.separador();

		// Primer combate del jugador
		boolean jugadorGano = combateFinal("Tu combate final", 120, 3);
		if (!jugadorGano) {
			Narrador.mostrar("✦ Has sido derrotado. El cañón suena por ti. El juego ha terminado.");
			juegoActivo = false;
			return;
		}

		Narrador.mostrar("✦ Tras vencer, miras a lo lejos y ves a Peeta luchando ferozmente.");
		String[] opcionesAyuda = { "Sí, ayudar a Peeta", "No, dejarlo luchar solo" };
		int eleccionAyuda = MenuConsola.menuOpciones("¿Deseas ayudar a Peeta en su combate?", opcionesAyuda);
		boolean ayudar = (eleccionAyuda == 1);
		boolean peetaGano;
		if (ayudar) {
			Narrador.mostrar("✦ Corres a ayudarlo y juntos derrotan al enemigo.");
			peetaGano = true;
		} else {
			peetaGano = Math.random() < 0.5;
			if (peetaGano) {
				Narrador.mostrar("✦ Peeta ha logrado vencer... pero su mirada hacia ti está llena de rencor.");
			} else {
				Narrador.mostrar("✦ Peeta ha sido derrotado... Solo tú quedas en pie.");
				Narrador.mostrar("✦ El último tributo enemigo corre hacia ti, buscando venganza. ¡Debes luchar!");

				boolean dueloFinal = combateFinal("Combate final contra el último tributo", 130, 3);

				if (dueloFinal) {
					Narrador.titulo("🏆 FINAL: Victoria total - Sobreviviste sola");
				} else {
					Narrador.titulo("💀 FINAL: Caíste en el último combate");
					juegoActivo = false;
				}
				return;
			}
		}

		// Peeta ha ganado y el jugador también
		if (ayudar) {
			if (jugador.getRelacionPeeta() >= 5) {
				Narrador.mostrar("Peeta: 'Lo logramos. Juntos hasta el final. Ganamos como uno solo.'");
				Narrador.titulo("🏆 FINAL: Victoria conjunta con Peeta");
			} else {
				Narrador.mostrar("Peeta: 'Crees que te necesito... pero solo fuiste una carga.'");
				boolean dueloPeeta = combateFinal("Combate final contra Peeta", 110, 2);
				if (dueloPeeta) {
					Narrador.titulo("🏆 FINAL: Victoria tras traición de Peeta");
				} else {
					Narrador.titulo("💀 FINAL: Derrotado por Peeta");
					juegoActivo = false;
				}
			}
		} else {
			Narrador.mostrar("Peeta: 'No viniste... no puedo perdonarte.'");
			boolean dueloPeeta = combateFinal("Duelo inevitable contra Peeta", 110, 2);
			if (dueloPeeta) {
				Narrador.titulo("🏆 FINAL: Victoria tras traición de Peeta");
			} else {
				Narrador.titulo("💀 FINAL: Derrotado por Peeta");
				juegoActivo = false;
			}
		}
	}
	// MÉTODO DE COMBATE COMPARTIDO

	private boolean combateFinal(String titulo, int vidaEnemigoBase, int curasJugador) {
		Narrador.separador();
		Narrador.mostrar("✦ " + titulo);

		int vidaEnemigo = vidaEnemigoBase;
		int curasEnemigo = 2;
		int vidaMaxJugador = jugador.getClaseCombate().getVidaMaxima();

		while (jugador.getVida() > 0 && vidaEnemigo > 0) {
			String[] acciones = { "Atacar", "Curarse (" + curasJugador + " restantes)" };
			int accion = MenuConsola.menuOpciones("Tu turno. ¿Qué quieres hacer?", acciones);

			if (accion == 1) {
				int danoJugador = new Random().nextInt(16) + 10;
				vidaEnemigo -= danoJugador;
				Narrador.mostrar("✦ Has atacado e infligido " + danoJugador + " de daño. Vida del enemigo: " + Math.max(vidaEnemigo, 0));
			} else {
				if (curasJugador > 0 && jugador.getVida() < vidaMaxJugador) {
					int cura = new Random().nextInt(20) + 10;
					jugador.curar(cura);
					curasJugador--;
					Narrador.mostrar("✦ Te has curado. Vida actual: " + jugador.getVida());
				} else {
					Narrador.mostrar("✦ No puedes curarte ahora.");
				}
			}
			if (vidaEnemigo > 0) {
				if (curasEnemigo > 0 && vidaEnemigo < 60 && Math.random() < 0.5) {
					int cura = new Random().nextInt(15) + 10;
					vidaEnemigo = Math.min(vidaEnemigoBase, vidaEnemigo + cura);
					curasEnemigo--;
					Narrador.mostrar("✦ El enemigo se cura. Vida actual: " + vidaEnemigo);
				} else {
					int danoEnemigo = new Random().nextInt(11) + 5;
					int escudo = jugador.getClaseCombate().calcularEscudo(danoEnemigo);
					int danoFinal = danoEnemigo - escudo;
					if (danoFinal > 0) {
						jugador.recibirDaño(danoFinal);
						Narrador.mostrar("✦ El enemigo te inflige " + danoFinal + " de daño. Vida actual: " + jugador.getVida());
					} else {
						Narrador.mostrar("✦ Bloqueaste completamente el ataque enemigo.");
					}
				}
			}
		}
		return jugador.getVida() > 0;
	}
}
