package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

import java.util.Random;

/**
 * Rappresenta l'attività lavorativa part-time all'interno del simulatore.
 * <p>
 * Questa azione è il motore economico del gioco: permette allo studente di accumulare
 * i fondi necessari per altre attività (come le uscite di svago). Tuttavia, il lavoro
 * ha un costo fisso in termini di fatica fisica (energia) e genera un malus psicologico
 * imprevedibile (stress), simulando giornate lavorative più o meno pesanti.
 * </p>
 */
public class Lavoro implements Attivita {

    private static final int COSTO_ENERGIA = -30;

    private static final int AUMENTO_DENARO = 40;

    private static final int MIN_STRESS = 10;
    private static final int MAX_STRESS = 35;

    private Random generatoreCasuale;

    /**
     * Costruisce l'attività lavorativa.
     * Ho scelto di creare il "dado" (Random) qui nel costruttore una volta sola,
     * invece di ricrearlo a ogni turno, per ottimizzare l'efficienza e l'uso della memoria del gioco.
     */
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

    /**
     * Verifica se lo studente è fisicamente in grado di affrontare il turno di lavoro.
     * Per lavorare non servono soldi, ma è indispensabile avere abbastanza energia.
     *
     * * @param studente Lo studente che intende lavorare.
     * @return true se l'energia è sufficiente, false se lo studente è troppo stanco.
     */
    @Override
    public boolean isEseguibile(Studente studente) {
        return studente.getEnergia() >= Math.abs(COSTO_ENERGIA);
    }

    /**
     * Calcola quanto sarà stressante il turno di lavoro di oggi.
     * È un metodo di supporto (helper) creato per nascondere la formula matematica
     * della generazione casuale e mantenere il metodo esegui() più pulito.
     *
     * @return Un numero casuale compreso tra il livello minimo e massimo di stress.
     */
    private int stressDiOggi(){
        return generatoreCasuale.nextInt((MAX_STRESS - MIN_STRESS) + 1) + MIN_STRESS;
    }

    /**
     * Applica le conseguenze della giornata lavorativa sulle statistiche dello studente.
     * Toglie l'energia, aumenta il denaro (stipendio) e applica lo stress calcolato per oggi.
     *
     * * @param studente Lo studente che ha lavorato.
     * @throws EccezioneInsufficienzaRisorse Se i requisiti per l'esecuzione non sono soddisfatti.
     */
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
