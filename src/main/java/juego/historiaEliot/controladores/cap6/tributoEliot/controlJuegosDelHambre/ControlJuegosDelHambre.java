package juego.historiaEliot.controladores.cap6.tributoEliot.controlJuegosDelHambre;

import java.util.ArrayList;

public class ControlJuegosDelHambre {

    private static ControlJuegosDelHambre instancia;

    private boolean lunaAliada;
    private int probTrampa;
    private int probCombate;
    private ArrayList<Integer> enemigos;
    private int cantidadRecursosCornocopia, cantidadRecursosNorte, cantidadRecursosSur,
    cantidadRecursosEste, cantidadRecursosOeste;
    private int turno;
    private boolean zonaCornocopia, zonaSur, zonaNorte, zonaEste, zonaOeste;
    private boolean trampaNorte, trampaSur, trampaEste, trampaOeste;
    private int hambre, sed;

    private ControlJuegosDelHambre() {
        this.hambre = 100;
        this.sed = 100;
        this.lunaAliada = false;
        this.probTrampa = 20;
        this.probCombate = 40;
        this.cantidadRecursosCornocopia = 10;
        this.cantidadRecursosOeste = 5;
        this.cantidadRecursosEste = 5;
        this.cantidadRecursosNorte = 10;
        this.cantidadRecursosSur = 5;
        this.turno = 0;
        this.zonaCornocopia = true;
        this.zonaSur = false;
        this.zonaNorte = false;
        this.zonaEste = false;
        this.zonaOeste = false;
        this.enemigos = new ArrayList<>();
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.enemigos.add(0);
        this.trampaNorte = false;
        this.trampaSur = false;
        this.trampaEste = false;
        this.trampaOeste = false;
    }

    public static ControlJuegosDelHambre getInstancia() {
        if (instancia == null) instancia = new ControlJuegosDelHambre();
        return instancia;
    }
    public ArrayList<Integer> getEnemigos() {
        return enemigos;
    }
    public boolean isLunaAliada() {
        return lunaAliada;
    }
    public void setLunaAliada(boolean lunaAliada) {
        this.lunaAliada = lunaAliada;
    }
    public int getProbTrampa() {
        return probTrampa;
    }
    public void setProbTrampa(int probTrampa) {
        this.probTrampa += probTrampa;
    }
    public int getProbCombate() {
        return probCombate;
    }
    public boolean isTrampaNorte() {
        return trampaNorte;
    }
    public void setTrampaNorte(boolean trampaNorte) {
        this.trampaNorte = trampaNorte;
    }
    public boolean isTrampaSur() {
        return trampaSur;
    }
    public void setTrampaSur(boolean trampaSur) {
        this.trampaSur = trampaSur;
    }
    public boolean isTrampaEste() {
        return trampaEste;
    }
    public void setTrampaEste(boolean trampaEste) {
        this.trampaEste = trampaEste;
    }
    public boolean isTrampaOeste() {
        return trampaOeste;
    }
    public void setTrampaOeste(boolean trampaOeste) {
        this.trampaOeste = trampaOeste;
    }
    public int getTurno() {
        return turno;
    }
    public void setTurno() {
        this.turno++;
    }
    public boolean isZonaCornocopia() {
        return zonaCornocopia;
    }
    public void setZonaCornocopia(boolean zonaCornocopia) {
        this.zonaCornocopia = zonaCornocopia;
    }
    public boolean isZonaSur() {
        return zonaSur;
    }
    public void setZonaSur(boolean zonaSur) {
        this.zonaSur = zonaSur;
    }
    public boolean isZonaNorte() {
        return zonaNorte;
    }
    public void setZonaNorte(boolean zonaNorte) {
        this.zonaNorte = zonaNorte;
    }
    public boolean isZonaEste() {
        return zonaEste;
    }
    public void setZonaEste(boolean zonaEste) {
        this.zonaEste = zonaEste;
    }
    public boolean isZonaOeste() {
        return zonaOeste;
    }
    public void setZonaOeste(boolean zonaOeste) {
        this.zonaOeste = zonaOeste;
    }
    public int getCantidadRecursosCornocopia() {
        return cantidadRecursosCornocopia;
    }
    public void setCantidadRecursosCornocopia() {
        this.cantidadRecursosCornocopia--;
    }
    public int getCantidadRecursosNorte() {
        return cantidadRecursosNorte;
    }
    public void setCantidadRecursosNorte() {
        this.cantidadRecursosNorte--;
    }
    public int getCantidadRecursosEste() {
        return cantidadRecursosEste;
    }
    public void setCantidadRecursosEste() {
        this.cantidadRecursosEste--;
    }
    public int getCantidadRecursosOeste() {
        return cantidadRecursosOeste;
    }
    public void setCantidadRecursosOeste() {
        this.cantidadRecursosOeste--;
    }
    public int getCantidadRecursosSur() {
        return cantidadRecursosSur;
    }
    public void setCantidadRecursosSur() {
        this.cantidadRecursosSur--;
    }
    public int getHambre() {
        return hambre;
    }
    public void setHambre(int hambre) {
        this.hambre = Math.min(100, hambre);
    }
    public int getSeded() {
        return sed;
    }
    public void setSeded(int sed) {
        this.sed = Math.min(100, sed);
    }
    public int combateFinal() {
        int suma = 0;
        for (int num : enemigos) {
            suma += num;
        }
        return suma;
    }

}
