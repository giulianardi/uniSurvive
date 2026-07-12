package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

import java.util.Random;

/**
 * Rappresenta l'attività lavorativa part-time all'interno del simulatore.
 *
 * Questa azione è il motore economico del gioco: permette allo studente di accumulare
 * i fondi necessari per altre attività (come le uscite di svago). Tuttavia, il lavoro
 * ha un costo fisso in termini di fatica fisica (energia) e genera un malus psicologico
 * imprevedibile (stress), simulando giornate lavorative più o meno pesanti.
 */
public class Lavoro implements Attivita {

    private static final int COSTO_ENERGIA = -30;

    private static final int AUMENTO_DENARO = 40;

    private static final int MIN_STRESS = 10;
    private static final int MAX_STRESS = 35;

    private Random generatoreCasuale;

    public Lavoro(){
        this.generatoreCasuale = new Random();
    }

    @Override
    public String getNome() {
        return "Lavorare";
    }

    @Override
    public String getDescrizione() {
        return "Un estenuante turno di lavoro part-time per arrotondare. Fondamentale per riempire il " +
                "portafogli e potersi permettere qualche uscita, ma prosciuga le energie fisiche e fa salire " +
                "pericolosamente lo stress.";
    }

    @Override
    public boolean isEseguibile(Studente studente) {
        return studente.getEnergia() >= Math.abs(COSTO_ENERGIA);
    }

    private int stressDiOggi(){
        return generatoreCasuale.nextInt((MAX_STRESS - MIN_STRESS) + 1) + MIN_STRESS;
    }

    @Override
    public void esegui(Studente studente) throws EccezioneInsufficienzaRisorse {
        if(isEseguibile(studente)){
            studente.editEnergia(COSTO_ENERGIA);
            studente.editDenaro(AUMENTO_DENARO);
            studente.editStress(stressDiOggi());
        }else {
            throw new EccezioneInsufficienzaRisorse("L'energia non è sufficiente per lavorare!");
        }
    }
}
