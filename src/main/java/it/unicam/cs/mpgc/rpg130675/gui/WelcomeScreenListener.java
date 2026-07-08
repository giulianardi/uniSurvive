package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;

/**
 * Interfaccia per gestire gli eventi generati dalla schermata di benvenuto.
 * Questo ci permette di disaccoppiare la GUI dalla logica di gioco.
 */
public interface WelcomeScreenListener {
    void onGameStartRequested(String studentName, Facolta faculty);
}
