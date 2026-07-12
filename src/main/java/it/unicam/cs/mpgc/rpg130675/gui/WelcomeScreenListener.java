package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;

public interface WelcomeScreenListener {
    void onGameStartRequested(String studentName, Facolta faculty);
}
