package juego.sistemaCombate.logica;

import juego.sistemaCombate.modelo.*;
import juego.sistemaCombate.dao.*;

import java.util.List;
import java.util.Random;

public class CondicionesCombateConstructora {

    private final TerrenoDAO terrenoDAO = new TerrenoDAO();
    private final ClimaDAO climaDAO = new ClimaDAO();
    private final MomentoDiaDAO momentoDAO = new MomentoDiaDAO();
    private final Random random = new Random();

    public CondicionesCombate generarAleatorias() {
        List<Terreno> terrenos = terrenoDAO.getTodos();
        List<Clima> climas = climaDAO.getTodos();
        List<MomentoDia> momentos = momentoDAO.getTodos();

        Terreno terreno = terrenos.get(random.nextInt(terrenos.size()));
        Clima clima = climas.get(random.nextInt(climas.size()));
        MomentoDia momento = momentos.get(random.nextInt(momentos.size()));

        return new CondicionesCombate(terreno, clima, momento);
    }

    public CondicionesCombate crearManual(Terreno t, Clima c, MomentoDia m) {
        return new CondicionesCombate(t, c, m);
    }
}
