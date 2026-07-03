package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali;

import java.util.Random;

/**
 * Fornisce l'infrastruttura di base per tutte le tipologie di eventi randomici
 * all'interno del simulatore.
 * <p>
 * Questa classe astratta funge da radice per l'intera gerarchia degli imprevisti
 * (sia bonus che malus). Centralizzando la creazione e la gestione del generatore
 * di numeri pseudo-casuali e fornisce alle sottoclassi concrete gli strumenti
 * necessari per il calcolo aleatorio delle variazioni di stato.
 * </p>
 */
public abstract class EventoCasualeBase implements EventoCasuale{

    private Random generatoreCasuale;

    public EventoCasualeBase() {
        this.generatoreCasuale = new Random();
    }

    /**
     * Metodo di supporto per la generazione di un valore intero limitato
     * all'interno di un range specifico.
     *
     * @param min Il limite inferiore del range (incluso).
     * @param max Il limite superiore del range (incluso).
     * @return Un numero intero pseudo-casuale compreso tra min e max.
     */
    protected int generaValore(int min, int max) {
        return generatoreCasuale.nextInt((max - min) + 1) + min;
    }
}
