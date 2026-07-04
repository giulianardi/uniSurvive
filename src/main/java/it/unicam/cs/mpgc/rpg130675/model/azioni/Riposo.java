package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Rappresenta l'attività di "Riposo" all'interno del simulatore.
 * <p>
 * Questa è l'azione di base e di "salvataggio" del gioco. Permette allo studente
 * di recuperare le energie fisiche necessarie per poter tornare a studiare o lavorare.
 * Essendo un'azione essenziale per la sopravvivenza, è gratuita e sempre disponibile.
 * </p>
 */
public class Riposo implements Attivita {

    private static final int RECUPERO_ENERGIA = 50;


    @Override
    public String getNome() {
        return "Riposare";
    }

    @Override
    public String getDescrizione() {
        return "Un bel sonno ristoratore. Ripristina molta energia per affrontare il prossimo turno.";
    }

    /**
     * Controlla se lo studente può riposare in questo turno.
     * Poiché dormire non costa soldi e si può fare in qualsiasi condizione fisica,
     * questo metodo restituisce sempre vero.
     *
     * * @param studente Lo studente che intende riposare.
     * @return Sempre {@code true}, perché l'azione è sempre garantita.
     */
    @Override
    public boolean isEseguibile(Studente studente) {
        return true;
    }

    /**
     * Applica i benefici del sonno allo studente, ricaricando la sua barra dell'energia.
     *
     * * @param studente Lo studente che si è riposato.
     */
    @Override
    public void esegui(Studente studente) {
        if (isEseguibile(studente)) {
            studente.editEnergia(RECUPERO_ENERGIA);
        }
    }
}
