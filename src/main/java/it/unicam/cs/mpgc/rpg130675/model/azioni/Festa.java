package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Rappresenta l'attività ricreativa "Festa" all'interno del simulatore.
 *
 * Questa classe concreta definisce un'azione strategica a disposizione del giocatore,
 * mirata alla riduzione drastica dello stress per prevenire il Burnout.
 * La classe incapsula esclusivamente i costi, i benefici e le regole di validazione
 * legate a questo specifico evento.
 */
public class Festa implements Attivita {

    private static final int COSTO_ENERGIA = -20;

    private static final int COSTO_STRESS = -40;

    private static final int COSTO_DENARO = -30;

    @Override
    public String getNome() {
        return "Andare ad una festa";
    }

    @Override
    public String getDescrizione() {
        return "Una serata epica fuori con gli amici per staccare completamente la spina dai libri. " +
                "Svuota il portafogli e ti fa fare le ore piccole consumando energia, ma è l'unico vero modo" +
                " per abbattere drasticamente lo stress e allontanare lo spettro del Burnout.";
    }

    private boolean isDenaroSufficiente(Studente studente){
        return studente.getDenaro() >= Math.abs(COSTO_DENARO);
    }

    private boolean isEnergiaSufficiente(Studente studente){
        return studente.getEnergia() >= Math.abs(COSTO_ENERGIA);
    }

    @Override
    public boolean isEseguibile(Studente studente) {
        return isDenaroSufficiente(studente) && isEnergiaSufficiente(studente);
    }

    @Override
    public void esegui(Studente studente) throws EccezioneInsufficienzaRisorse {
        if(isEseguibile(studente)){
            studente.editDenaro(COSTO_DENARO);
            studente.editEnergia(COSTO_ENERGIA);
            studente.editStress(COSTO_STRESS);
        }else if(studente.getEnergia()<Math.abs(COSTO_ENERGIA)){
            throw new EccezioneInsufficienzaRisorse("L'energia non è sufficiente per fare festa!");
        }else if(studente.getDenaro()<Math.abs(COSTO_DENARO)){
            throw new EccezioneInsufficienzaRisorse("Il denaro non è sufficiente per fare festa!");
        }else{
            throw new EccezioneInsufficienzaRisorse("Non hai abbastanza risorse per andare ad una festa :(");
        }
    }
}
