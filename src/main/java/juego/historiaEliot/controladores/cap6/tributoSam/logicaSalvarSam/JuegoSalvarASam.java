package juego.historiaEliot.controladores.cap6.tributoSam.logicaSalvarSam;

import juego.historiaEliot.mas.TributoSam;

public class JuegoSalvarASam {

    private static JuegoSalvarASam instancia;

    private boolean pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10,
                    pos11, pos12, pos13, pos14, pos15, pos16, pos17, pos18, pos19, pos20,
                    pos21, pos22, salaMapa, ropero, salaCamaras, cuartoInfo,
                    salaMedica, salaArchivos, cuartollaves, sala1, sala2, sala3,
                    sala4, sala5, sala6, sala7, sala8;
    private boolean samSalvo, lunaSalvo, final1, final2;
    private boolean mapaCompleto;
    private boolean vistaPrimeraVez;
    private boolean camarasDesactivadas;
    private TributoSam personajeEliot;
    private boolean trajeOficial;
    private double cordX, cordY;

    private JuegoSalvarASam() {
        this.samSalvo = false;
        this.lunaSalvo = false;
        this.final1 = false;
        this.final2 = false;
        this.mapaCompleto = false;
        this.vistaPrimeraVez = false;
        this.camarasDesactivadas = false;
        this.trajeOficial = false;
        this.cordX = 0;
        this.cordY = 0;
        this.pos1 = true;
        this.pos2 = false;
        this.pos3 = false;
        this.pos4 = false;
        this.pos5 = false;
        this.pos6 = false;
        this.pos7 = false;
        this.pos8 = false;
        this.pos9 = false;
        this.pos10 = false;
        this.pos11 = false;
        this.pos12 = false;
        this.pos13 = false;
        this.pos14 = false;
        this.pos15 = false;
        this.pos16 = false;
        this.pos17 = false;
        this.pos18 = false;
        this.pos19 = false;
        this.pos20 = false;
        this.pos21 = false;
        this.pos22 = false;
        this.cuartoInfo = false;
        this.salaMedica = false;
        this.salaArchivos = false;
        this.salaCamaras = false;
        this.salaMapa = false;
        this.ropero = false;
        this.cuartollaves = false;
        this.sala1 = false;
        this.sala2 = false;
        this.sala3 = false;
        this.sala4 = false;
        this.sala5 = false;
        this.sala6 = false;
        this.sala7 = false;
        this.sala8 = false;
    }

    public double getCordX() {
        return cordX;
    }
    public double getCordY() {
        return cordY;
    }
    public void setCordX(double cordX) {
        this.cordX = cordX;
    }
    public void setCordY(double cordY) {
        this.cordY = cordY;
    }
    public boolean getTrajeOficial() {
        return trajeOficial;
    }
    public void setTrajeOficial(boolean trajeOficial) {
        this.trajeOficial = trajeOficial;
    }
    public boolean getCamarasDesactivadas() {
        return camarasDesactivadas;
    }
    public void setCamarasDesactivadas(boolean camarasDesactivadas) {
        this.camarasDesactivadas = camarasDesactivadas;
    }
    public  boolean getVistaPrimeraVez(){
        return vistaPrimeraVez;
    }
    public void setVistaPrimeraVez(boolean vista){
        this.vistaPrimeraVez = vista;
    }
    public boolean getMapaCompleto() {
        return mapaCompleto;
    }
    public void setMapaCompleto(boolean mapaCompleto) {
        this.mapaCompleto = mapaCompleto;
    }
    public boolean getPos1() {
        return pos1;
    }
    public void setPos1(boolean pos1) {
        this.pos1 = pos1;
    }
    public boolean getPos2() {
        return pos2;
    }
    public void setPos2(boolean pos2) {
        this.pos2 = pos2;
    }
    public boolean getPos3() {
        return pos3;
    }
    public void setPos3(boolean pos3) {
        this.pos3 = pos3;
    }
    public boolean getPos4() {
        return pos4;
    }
    public void setPos4(boolean pos4) {
        this.pos4 = pos4;
    }
    public boolean getPos5() {
        return pos5;
    }
    public void setPos5(boolean pos5) {
        this.pos5 = pos5;
    }
    public boolean getPos6() {
        return pos6;
    }
    public void setPos6(boolean pos6) {
        this.pos6 = pos6;
    }
    public boolean getPos7() {
        return pos7;
    }
    public void setPos7(boolean pos7) {
        this.pos7 = pos7;
    }
    public boolean getPos8() {
        return pos8;
    }
    public void setPos8(boolean pos8) {
        this.pos8 = pos8;
    }
    public boolean getPos9() {
        return pos9;
    }
    public void setPos9(boolean pos9) {
        this.pos9 = pos9;
    }
    public boolean getPos10() {
        return pos10;
    }
    public void setPos10(boolean pos10) {
        this.pos10 = pos10;
    }
    public boolean getPos11() {
        return pos11;
    }
    public void setPos11(boolean pos11) {
        this.pos11 = pos11;
    }
    public boolean getPos12() {
        return pos12;
    }
    public void setPos12(boolean pos12) {
        this.pos12 = pos12;
    }
    public boolean getPos13() {
        return pos13;
    }
    public void setPos13(boolean pos13) {
        this.pos13 = pos13;
    }
    public boolean getPos14() {
        return pos14;
    }
    public void setPos14(boolean pos14) {
        this.pos14 = pos14;
    }
    public boolean getPos15() {
        return pos15;
    }
    public void setPos15(boolean pos15) {
        this.pos15 = pos15;
    }
    public boolean getPos16() {
        return pos16;
    }
    public void setPos16(boolean pos16) {
        this.pos16 = pos16;
    }
    public boolean getPos17() {
        return pos17;
    }
    public void setPos17(boolean pos17) {
        this.pos17 = pos17;
    }
    public boolean getPos18() {
        return pos18;
    }
    public void setPos18(boolean pos18) {
        this.pos18 = pos18;
    }
    public boolean getPos19() {
        return pos19;
    }
    public void setPos19(boolean pos19) {
        this.pos19 = pos19;
    }
    public boolean getPos20() {
        return pos20;
    }
    public void setPos20(boolean pos20) {
        this.pos20 = pos20;
    }
    public boolean getPos21() {
        return pos21;
    }
    public void setPos21(boolean pos21) {
        this.pos21 = pos21;
    }
    public boolean getPos22() {
        return pos22;
    }
    public void setPos22(boolean pos22) {
        this.pos22 = pos22;
    }
    public boolean getSalaMapa() {
        return salaMapa;
    }
    public void setSalaMapa(boolean salaMapa) {
        this.salaMapa = salaMapa;
    }
    public boolean getSalaArchivos() {
        return salaArchivos;
    }
    public void setSalaArchivos(boolean salaArchivos) {
        this.salaArchivos = salaArchivos;
    }
    public boolean getSalaCamaras() {
        return salaCamaras;
    }
    public void setSalaCamaras(boolean salaCamaras) {
        this.salaCamaras = salaCamaras;
    }
    public boolean getSalaMedica() {
        return salaMedica;
    }
    public void setSalaMedica(boolean salaMedica) {
        this.salaMedica = salaMedica;
    }
    public boolean getSalaArchivo() {
        return salaArchivos;
    }
    public void setSalaArchivo(boolean salaArchivos) {
        this.salaArchivos = salaArchivos;
    }

    public static JuegoSalvarASam getJuegoSalvarASam() {
        if(instancia == null) instancia = new JuegoSalvarASam();
        return instancia;
    }
    public JuegoSalvarASam getInstancia() {
        return instancia;
    }

}
