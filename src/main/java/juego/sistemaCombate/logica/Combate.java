package juego.sistemaCombate.logica;

import juego.sistemaCombate.modelo.*;
import javafx.scene.control.Label;

public class Combate {

    private final Jugador jugador;
    private final Enemigo enemigo;
    private final Aliado aliado;
    private final CondicionesCombate condiciones;
    private boolean turnoJugador;

    public Combate(Jugador jugador, Enemigo enemigo, Aliado aliado, CondicionesCombate condiciones) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.aliado = aliado;
        this.condiciones = condiciones;
        this.turnoJugador = Math.random() < 0.6;
    }

    public boolean isTurnoJugador() {
        return turnoJugador;
    }

    public boolean aplicarAtaque(Personaje atacante, Personaje defensor, Ataque ataque) {
        boolean impacto = Math.random() <= ataque.getProbAcierto();
        if (!impacto) return false;

        boolean critico = false;
        if (atacante.tieneCriticoExtra()) critico = Math.random() < 0.2;
        else if (atacante.getClase().haceCounterA(defensor.getClase())) critico = Math.random() < 0.15;

        double modAtaque = condiciones.modificarAtaque(1.0);
        double modDefensa = condiciones.modificadorDefensa(1.0);

        int dano = ataque.calcularDano(critico, modAtaque);

        if (ataque.getEfecto() == EfectoEspecial.DOBLE_DANO_50 && Math.random() < 0.5) dano *= 2;
        if (atacante.tieneBuffProximoAtaque()) dano += 10;
        if (ataque.getEfecto() == EfectoEspecial.EJECUTAR_SI_VIDA_BAJA &&
                ((double) defensor.getVidaActual() / defensor.getVidaMaxima()) < 0.3) dano = defensor.getVidaActual();

        int defensa = (int) (defensor.getClase().calcularEscudo(dano) * modDefensa);
        if (defensor.debeIgnorarEscudo()) defensa = 0;

        int danoFinal = Math.max(0, dano - defensa);
        defensor.recibirDano(danoFinal);

        if (ataque.getEfecto() != EfectoEspecial.NINGUNO) {
            aplicarEfecto(atacante, defensor, ataque.getEfecto());
        }

        return true;
    }

    private void aplicarEfecto(Personaje atacante, Personaje defensor, EfectoEspecial efecto) {
        if (defensor.esInmune()) return;

        switch (efecto) {
            case FALLAR_SIGUIENTE_ATAQUE -> defensor.aplicarDebuffFallo();
            case REDUCIR_DANO_SIGUIENTE_ATAQUE -> defensor.aplicarReduccionDano();
            case AUMENTAR_ESCUDO_TEMPORAL -> defensor.aumentarEscudoTemporal(0.15, 2);
            case CRITICO_ADICIONAL -> atacante.aplicarCriticoExtra();
            case PERDER_TURNO -> { if (Math.random() < 0.5) defensor.aplicarPerderTurno(); }
            case IGNORAR_ESCUDO -> { if (Math.random() < 0.45) defensor.aplicarIgnorarEscudo(); }
            case DUPLICAR_ESCUDO_TEMPORAL -> atacante.duplicarEscudoTemporal(2);
            case SANGRADO -> defensor.aplicarSangrado();
            case QUEMADURA -> defensor.aplicarQuemadura();
            case AUMENTAR_FALLO_ENEMIGO -> defensor.aplicarFalloExtra();
            case BAJAR_DEFENSA_USUARIO -> atacante.aplicarBajarDefensa();
            case BUFF_PROXIMO_ATAQUE -> atacante.aplicarBuffProximoAtaque();
            case REDUCIR_ESCUDO_RIVAL -> defensor.aplicarEscudoReducido();
            case INMUNE_EFECTOS_SIGUIENTE_TURNO -> atacante.activarInmunidadEfectos();
            case BONUS_DANO_SI_FUE_ATACADO_ANTES -> atacante.activarBuffSiFueAtacado();
            case FALLO_60_PROXIMO_TURNO -> defensor.activarFallo60();
            case SIN_DEFENSA_SIGUIENTE_TURNO -> defensor.aplicarSinDefensa(1);
            case VENENO_3T -> defensor.aplicarVeneno3T();
            case INMUNE_A_ATAQUE_PROXIMO_TURNO -> atacante.aplicarInmuneAtaque();
            default -> {}
        }
    }

    public void decidirYUsarObjetosIA(Label mensaje) {
        if (enemigo.getInventario().tieneObjetoDisponible(TipoObjeto.VENDA) &&
                enemigo.getVidaActual() < enemigo.getVidaMaxima() * 0.4 && Math.random() < 0.8) {

            enemigo.getInventario().usarObjeto(TipoObjeto.VENDA);
            enemigo.curar(30); // o el valor que consideres
            mensaje.setText(enemigo.getNombre() + " usó una venda y recuperó vida");

        } else if (enemigo.getInventario().tieneObjetoDisponible(TipoObjeto.CARNE) && Math.random() < 0.3) {

            enemigo.getInventario().usarObjeto(TipoObjeto.CARNE);
            enemigo.setConsumidoCarneEsteTurno(true);
            mensaje.setText(enemigo.getNombre() + " comió carne");

        } else if (enemigo.getInventario().tieneObjetoDisponible(TipoObjeto.HOJA) && Math.random() < 0.3) {

            enemigo.getInventario().usarObjeto(TipoObjeto.HOJA);
            enemigo.setConsumidoHojaEsteTurno(true);
            mensaje.setText(enemigo.getNombre() + " usó hojas");
        }
    }

}
