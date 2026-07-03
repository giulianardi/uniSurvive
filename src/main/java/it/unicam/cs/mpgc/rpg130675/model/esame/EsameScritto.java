package it.unicam.cs.mpgc.rpg130675.model.esame;

public class EsameScritto extends EsameBase {

    public EsameScritto(String materia, int difficolta, int cfu) {
        super(materia, difficolta, cfu);
    }

    @Override
    protected int calcolaMalusStress(int stressAttuale) {
        return stressAttuale / 10;
    }
}
