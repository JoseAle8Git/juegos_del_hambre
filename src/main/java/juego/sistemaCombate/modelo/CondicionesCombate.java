package juego.sistemaCombate.modelo;

public class CondicionesCombate {

    private Terreno terreno;
    private Clima clima;
    private MomentoDia momento;

    public CondicionesCombate(Terreno terreno, Clima clima, MomentoDia momento) {
        this.terreno = terreno;
        this.clima = clima;
        this.momento = momento;
    }

    public double modificarAtaque(double base) {
        double mod = base;
        if (terreno == Terreno.MONTANOSO) mod -= 0.2;
        if (terreno == Terreno.MOJADO) mod += 0.15;
        if (clima == Clima.NEVADO) mod -= 0.3;
        if (clima == Clima.TORMENTA_ELECTRICA) mod += 0.2;
        if (momento == MomentoDia.NOCHE) mod += 0.1;
        return Math.max(0, mod);
    }

    public double modificadorDefensa(double base) {
        double mod = base;
        if (terreno == Terreno.PANTANOSO) mod -= 0.2;
        if (clima == Clima.LLUVIOSO) mod -= 0.4;
        if (clima == Clima.TORMENTA_ELECTRICA) mod -= 0.2;
        if (momento == MomentoDia.MANANA) mod -= 0.1;
        return Math.max(0, mod);
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public MomentoDia getMomento() {
        return momento;
    }

    public Clima getClima() {
        return clima;
    }
}
