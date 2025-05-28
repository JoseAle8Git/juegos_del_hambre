package juego.historiaKatniss.modelo;

public class Jugador extends Personaje {
	private int puntuacion;
	private Inventario inventario;
	private int prestigio = 0;
	private int relacionPeeta = 0;
	private boolean amorFingido;

	public Jugador(String nombre) {
		super(nombre, 100);
		this.puntuacion = 0;
		this.inventario = new Inventario();
		this.relacionPeeta = 0;
		this.prestigio = 0;
		this.amorFingido = false;
	}

	public void añadirPuntos(int puntos) {
		this.puntuacion += puntos;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void añadirPrestigio(int cantidad) {
		this.prestigio += cantidad;
	}

	public int getPrestigio() {
		return prestigio;
	}

	public int getRelacionPeeta() {
		return relacionPeeta;
	}

	public void modificarRelacionPeeta(int valor) {
		relacionPeeta += valor;
	}


	public void aceptarAmorFingido() {
		this.amorFingido = true;
	}

	public boolean aceptoAmorFingido() {
		return this.amorFingido;
	}


}
