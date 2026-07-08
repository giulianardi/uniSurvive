package it.unicam.cs.mpgc.rpg130675.gui;

import javax.swing.*;


/**
 * Gestisce l'aggiornamento dell'interfaccia grafica e le transizioni di stato visive del gioco.
 * Riceve gli aggiornamenti dal motore di gioco e li riflette sulla vista principale.
 */
public class GameUIListener {
    MainGameView gameView = new MainGameView();
    JFrame frame = new JFrame("UniSurvive");

    public void aggiornaStatistiche(int turno, int conoscenza, int energia, int stress, int denaro) {
        gameView.updateStats(turno, conoscenza, energia, stress, denaro);
    }

    public void mostraMessaggio(String titolo, String messaggio) {
        gameView.showEventMessage(titolo, messaggio);
    }

    public void triggerGameOver(String nomeStudente) {
        GameOverView gameOverView = new GameOverView(nomeStudente);
        frame.getContentPane().removeAll();
        frame.add(gameOverView);
        frame.revalidate();
        frame.repaint();
    }

    public void triggerVittoria(String nomeStudente) {
        JOptionPane.showMessageDialog(frame,
                "Congratulazioni " + nomeStudente + "!\nHai superato tutti gli esami e ti sei laureato!",
                "VITTORIA - LAUREA",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
