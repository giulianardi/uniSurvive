package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Rappresenta l'attività ricreativa "Festa" all'interno del simulatore.
 * <p>
 * Questa classe concreta definisce un'azione strategica a disposizione del giocatore,
 * mirata alla riduzione drastica dello stress per prevenire il Burnout.
 * La classe incapsula esclusivamente i costi, i benefici e le regole di validazione
 * legate a questo specifico evento.
 * </p>
 */
public class Festa implements Attivita {

    /** Il costo in termini di punti energia necessari per affrontare la serata. */
    private static final int COSTO_ENERGIA = -20;

    /** Il valore (negativo) di abbattimento del livello di stress dello studente. */
    private static final int COSTO_STRESS = -40;

    /** L'impatto economico dell'attività sulle finanze dello studente. */
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

    /**
     * Metodo helper privato per calcolare se il portafoglio dello studente permette questa spesa.
     *
     * @param studente Lo studente da verificare.
     * @return {@code true} se lo studente ha abbastanza soldi, {@code false} altrimenti.
     */
    private boolean isDenaroSufficiente(Studente studente){
        return studente.getDenaro() >= Math.abs(COSTO_DENARO);
    }

    /**
     * Metodo helper privato per calcolare se lo studente non è troppo stanco per uscire.
     *
     * @param studente Lo studente da verificare.
     * @return {@code true} se lo studente ha abbastanza energia, {@code false} altrimenti.
     */
    private boolean isEnergiaSufficiente(Studente studente){
        return studente.getEnergia() >= Math.abs(COSTO_ENERGIA);
    }

    /**
     * Verifica se lo studente può andare alla festa in questo turno.
     * Affinché sia possibile, deve avere sia i soldi che le energie necessarie.
     *
     * * @param studente Lo studente che vuole uscire.
     * @return {@code true} se l'azione è permessa, {@code false} altrimenti.
     */
    @Override
    public boolean isEseguibile(Studente studente) {
        return isDenaroSufficiente(studente) && isEnergiaSufficiente(studente);
    }

    /**
     * Applica le modifiche alle statistiche dello studente dopo aver fatto festa.
     * Toglie soldi ed energia, ma riduce lo stress.
     * * @param studente Lo studente su cui applicare gli effetti.
     * @throws it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse Se i requisiti per l'esecuzione non sono soddisfatti.
     */
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
