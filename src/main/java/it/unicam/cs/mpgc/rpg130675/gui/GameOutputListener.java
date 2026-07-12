package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.carriera.LibrettoUniversitario;

/**
 * Contratto che il GameController usa per comunicare eventi di gioco
 * verso una qualsiasi interfaccia utente.
 * Disaccoppia la logica di gioco da una specifica tecnologia di presentazione.
 */
public interface GameOutputListener {

    void aggiornaStatistiche(int turno, int conoscenza, int energia, int stress, int denaro);

    void mostraMessaggio(String titolo, String messaggio);

    void mostraLibretto(LibrettoUniversitario libretto);

    void triggerGameOver(String nomeStudente);

    void triggerVittoria(String nomeStudente);
}