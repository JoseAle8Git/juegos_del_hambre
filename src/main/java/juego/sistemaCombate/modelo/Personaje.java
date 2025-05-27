package juego.sistemaCombate.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Personaje {

    // Atributos principales
    protected String nombre;
    protected ClaseCombate clase;
    protected int vidaMaxima;
    protected int vidaActual;
    protected Inventario inventario;

    // Estos booleanos indican si un personaje está bajo un efecto
    protected boolean deBuffFallo = false;
    protected boolean reduccionDano = false;
    protected boolean perderTurno = false;
    protected boolean ignorarEscudo = false;
    protected boolean criticoExtra = false;
    protected boolean inmuneAtaque = false;
    protected boolean inmuneEfectos = false;
    protected boolean fueAtacadoUltimoTurno = false;
    protected boolean buffProximoSiAtacado = false;
    protected boolean fallo60ProxTurno = false;
    protected boolean sinDefensaProxTurno = false;

    // Controla los turnos en los que el efecto estará activo
    protected int turnosEscudoDuplicado = 0;
    protected int turnosEscudoExtra = 0;
    protected int turnosSangrado = 0;
    protected int turnosQuemadura = 0;
    protected int turnosFalloExtra = 0;
    protected int turnosMenorDefensa = 0;
    protected int turnosBuffProximoAtaque = 0;
    protected int turnosEscudoReducido = 0;
    protected int turnosVeneno = 0;

    // Valor adicional al escudo base
    protected double escudoExtraPorcentaje = 0.0;

    public Personaje(String nombre, ClaseCombate clase) {
        this.nombre = nombre;
        this.clase = clase;
        this.vidaMaxima = clase.getVidaMaxima();
        this.vidaActual = this.vidaMaxima;
        this.inventario = new Inventario();
    }

    // Define cómo elige su ataque por input o aleatorio
    public abstract Ataque elegirAtaque();
    // Devuelve si es un jugador o IA
    public abstract boolean esJugador();

    // Verifica si es inmune, aplica escudos y efectos, aplica daño y actualiza vida
    public void recibirDano(int dano) {
        if (esInmuneAtaque()) {
            System.out.println(nombre + " es inmune a ataques este turno");
            return;
        }

        marcarAtacado();

        int escudo = ignorarEscudo || sinDefensaProxTurno ? 0 : clase.calcularEscudo(dano);
        if (turnosEscudoExtra > 0) escudo += (int)(dano * escudoExtraPorcentaje);
        if (turnosEscudoReducido > 0) escudo += (int)(dano * 0.05);
        escudo = Math.max(0, escudo);

        int finalDano = dano - escudo;
        if (reduccionDano) {
            finalDano /= 2;
            reduccionDano = false;
        }

        vidaActual = Math.max(0, vidaActual - Math.max(0, finalDano));
    }

    public void curar(int cantidad) {
        vidaActual = Math.min(vidaActual + cantidad, vidaMaxima);
    }

    public boolean estaVivo() {
        return vidaActual > 0;
    }

    public ClaseCombate getClase() {
        return clase;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public Inventario getInventario() {
        return inventario;
    }

    // Estados especiales
    public void marcarAtacado() {
        fueAtacadoUltimoTurno = true;
    }

    public void aplicarDebuffFallo() {
        deBuffFallo = true;
    }

    public boolean tieneDebuffFallo() {
        return deBuffFallo;
    }

    public void resetearDebuffFallo() {
        deBuffFallo = false;
    }

    public void aplicarReduccionDano() {
        reduccionDano = true;
    }

    public void aplicarPerderTurno() {
        perderTurno = true;
    }

    public boolean debePerderTurno() {
        return perderTurno;
    }

    public void resetPerderTurno() {
        perderTurno = false;
    }

    public void aplicarIgnorarEscudo() {
        ignorarEscudo = true;
    }

    public boolean debeIgnorarEscudo() {
        return ignorarEscudo;
    }

    public void resetIgnorarEscudo() {
        ignorarEscudo = false;
    }

    public void aplicarCriticoExtra() {
        criticoExtra = true;
    }

    public boolean tieneCriticoExtra() {
        return criticoExtra;
    }

    public void resetCriticoExtra() {
        criticoExtra = false;
    }

    public void aumentarEscudoTemporal(double porcentaje, int turnos) {
        escudoExtraPorcentaje += porcentaje;
        turnosEscudoExtra = turnos;
    }

    public void duplicarEscudoTemporal(int turnos) {
        escudoExtraPorcentaje += clase.getEscudoPorcentaje();
        turnosEscudoDuplicado = turnos;
    }

    public void aplicarSangrado() {
        if (turnosSangrado == 0) turnosSangrado = 2;
    }

    public void aplicarQuemadura() {
        if (turnosQuemadura == 0) turnosQuemadura = 2;
    }

    public void aplicarVeneno3T() {
        if (turnosVeneno == 0) turnosVeneno = 3;
    }

    public void aplicarFalloExtra() {
        if (turnosFalloExtra == 0) turnosFalloExtra = 2;
    }

    public boolean tieneFalloExtra() {
        return turnosFalloExtra > 0;
    }

    public void aplicarBajarDefensa() {
        turnosMenorDefensa = 2;
    }

    public double getDefensaMultiplicador() {
        return (turnosMenorDefensa > 0) ? 0.9 : 1.0;
    }

    public void aplicarBuffProximoAtaque() {
        turnosBuffProximoAtaque = 1;
    }

    public boolean tieneBuffProximoAtaque() {
        return turnosBuffProximoAtaque > 0;
    }

    public void aplicarEscudoReducido() {
        turnosEscudoReducido = 2;
    }

    public void activarInmunidadEfectos() {
        inmuneEfectos = true;
    }

    public boolean esInmune() {
        return inmuneEfectos;
    }

    public void activarBuffSiFueAtacado() {
        buffProximoSiAtacado = true;
    }

    public boolean tieneBuffPorSerAtacado() {
        return buffProximoSiAtacado && fueAtacadoUltimoTurno;
    }

    public void activarFallo60() {
        fallo60ProxTurno = true;
    }

    public boolean tieneFallo60() {
        return fallo60ProxTurno;
    }

    public void aplicarSinDefensa(int turnos) {
        sinDefensaProxTurno = true;
    }

    public void aplicarInmuneAtaque() {
        inmuneAtaque = true;
    }

    public boolean esInmuneAtaque() {
        return inmuneAtaque;
    }

    // Turno
    public void avanzarTurno() {
        if (turnosEscudoExtra > 0) {
            turnosEscudoExtra--;
            if (turnosEscudoExtra == 0) {
                escudoExtraPorcentaje = 0.0;
            }
        }

        if (turnosEscudoDuplicado > 0) {
            turnosEscudoDuplicado--;
            if (turnosEscudoDuplicado == 0) {
                escudoExtraPorcentaje -= clase.getEscudoPorcentaje();
            }
        }

        if (turnosSangrado > 0) {
            vidaActual = Math.max(0, vidaActual - 5);
            turnosSangrado--;
        }

        if (turnosQuemadura > 0) {
            vidaActual = Math.max(0, vidaActual - 6);
            turnosQuemadura--;
        }

        if (turnosVeneno > 0) {
            vidaActual = Math.max(0, vidaActual - 8);
            turnosVeneno--;
        }

        if (turnosFalloExtra > 0) turnosFalloExtra--;
        if (turnosMenorDefensa > 0) turnosMenorDefensa--;
        if (turnosBuffProximoAtaque > 0) turnosBuffProximoAtaque--;
        if (turnosEscudoReducido > 0) turnosEscudoReducido--;

        // Reset de estados
        inmuneEfectos = false;
        sinDefensaProxTurno = false;
        inmuneAtaque = false;
        fallo60ProxTurno = false;
        perderTurno = false;
        ignorarEscudo = false;
        criticoExtra = false;
        deBuffFallo = false;
        reduccionDano = false;
        buffProximoSiAtacado = false;
        fueAtacadoUltimoTurno = false;
    }
    public List<String> getEfectosActivos() {
        List<String> efectos = new ArrayList<>();
        if (deBuffFallo) efectos.add("Debuff de fallo");
        if (reduccionDano) efectos.add("Reducción de daño");
        if (perderTurno) efectos.add("Perder turno");
        if (ignorarEscudo) efectos.add("Ignorar escudo");
        if (criticoExtra) efectos.add("Crítico extra");
        if (inmuneAtaque) efectos.add("Inmune a ataques");
        if (inmuneEfectos) efectos.add("Inmune a efectos");
        if (buffProximoSiAtacado) efectos.add("Daño aumentado si fue atacado");
        if (fallo60ProxTurno) efectos.add("60% fallo próximo turno");
        if (sinDefensaProxTurno) efectos.add("Sin defensa próximo turno");
        if (turnosSangrado > 0) efectos.add("Sangrado");
        if (turnosQuemadura > 0) efectos.add("Quemadura");
        if (turnosVeneno > 0) efectos.add("Veneno");
        if (turnosEscudoExtra > 0) efectos.add("Escudo extra");
        if (turnosEscudoDuplicado > 0) efectos.add("Escudo duplicado");
        if (turnosEscudoReducido > 0) efectos.add("Escudo reducido");
        if (turnosFalloExtra > 0) efectos.add("Fallo aumentado");
        if (turnosBuffProximoAtaque > 0) efectos.add("Daño siguiente ataque +10");
        if (turnosMenorDefensa > 0) efectos.add("Defensa reducida");
        return efectos;
    }

}
