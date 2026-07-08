package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Rappresenta un'azione generica che lo studente può compiere durante un turno di gioco.
 * <p>
 * E' possibile aggiungere nuove attività semplicemente creando nuove classi
 * che implementano questo contratto, senza dover modificare il motore di gioco principale.
 * </p>
 */
public interface Attivita {
    String getNome();
    String getDescrizione();

    /**
     * Verifica se lo studente possiede i requisiti minimi (es. livello sufficiente di energia o denaro)
     * per poter svolgere questa specifica attività in questo turno.
     *
     * @param studente L'istanza dello studente che intende compiere l'azione.
     * @return {@code true} se lo studente soddisfa i requisiti per eseguire l'attività,
     * {@code false} altrimenti.
     */
    boolean isEseguibile(Studente studente);

    /**
     * Applica le conseguenze dell'attività alle statistiche dello studente.
     * <p>
     * Questo metodo ha la responsabilità di alterare lo stato dello studente
     * (es. modificando energia, stress, denaro, conoscenza). Dovrebbe essere invocato
     * dal Controller solo se l'attività risulta eseguibile.
     * </p>
     * * @param studente L'istanza dello studente su cui applicare gli effetti dell'attività.
     */
    void esegui(Studente studente) throws EccezioneInsufficienzaRisorse;
}
