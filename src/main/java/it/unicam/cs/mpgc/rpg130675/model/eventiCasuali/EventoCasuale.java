package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Definisce il contratto per la gestione degli eventi randomici (imprevisti o bonus)
 * all'interno del simulatore.
 * <p>
 * Il motore di gioco può istanziare e innescare una moltitudine di eventi diversi.
 * Ogni classe concreta che implementa questa interfaccia avrà la singola responsabilità
 * di definire l'impatto di un determinato evento sulle statistiche dello studente.
 * </p>
 */
public interface EventoCasuale {

        String getDescrizione();

    /**
     * Applica le conseguenze logiche e numeriche dell'evento sullo stato del giocatore.
     * <p>
     * Costituisce il punto di ingresso per l'esecuzione dell'imprevisto.
     * </p>
     *
     * @param studente L'istanza dello studente bersaglio su cui applicare gli effetti
     * strutturali dell'evento.
     */
        void innesca(Studente studente);

}
