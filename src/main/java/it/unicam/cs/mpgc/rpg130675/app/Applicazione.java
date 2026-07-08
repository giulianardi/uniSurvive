package it.unicam.cs.mpgc.rpg130675.app;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import it.unicam.cs.mpgc.rpg130675.controller.GameController;
import it.unicam.cs.mpgc.rpg130675.gui.GameUIListener;
import it.unicam.cs.mpgc.rpg130675.gui.MainGameView;
import it.unicam.cs.mpgc.rpg130675.gui.WelcomeView;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Festa;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Lavoro;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Riposo;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Studio;

import javax.swing.*;


/**
 * Punto di ingresso dell'applicazione.
 * Si occupa di inizializzare l'interfaccia grafica, configurare il tema visuale
 * e gestire la transizione tra le diverse viste del gioco.
 */
public class Applicazione {

    static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
        } catch (Exception ex) {
            System.err.println("Impossibile caricare il tema FlatLaf.");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("UniSurvive");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);

            WelcomeView welcomeView = new WelcomeView();

            welcomeView.setWelcomeScreenListener((nome, facolta) -> {

                MainGameView gameView = new MainGameView();
                GameUIListener uiListener = new GameUIListener();
                GameController controller = new GameController(uiListener);

                controller.avviaPartita(nome, facolta);

                gameView.setMainGameListener(azioneScelta -> {
                    System.out.println("Hai cliccato il bottone: " + azioneScelta);

                    try {
                        switch (azioneScelta) {
                            case STUDIA:
                                System.out.println("Invio comando Studio al controller...");
                                controller.eseguiAzione(new Studio());
                                break;
                            case LAVORA:
                                System.out.println("Invio comando Lavoro al controller...");
                                controller.eseguiAzione(new Lavoro());
                                break;
                            case FESTA:
                                System.out.println("Invio comando Festa al controller...");
                                controller.eseguiAzione(new Festa());
                                break;
                            case DORMI:
                                System.out.println("Invio comando Riposo al controller...");
                                controller.eseguiAzione(new Riposo());
                                break;
                            case LIBRETTO:
                                controller.mostraLibretto();
                                break;
                            default:
                                System.out.println("Azione non riconosciuta!");
                        }
                    } catch (Exception ex) {
                        System.out.println("ERRORE DURANTE L'AZIONE: ");
                        ex.printStackTrace();
                    }
                });

                frame.add(gameView);
                frame.revalidate();
                frame.repaint();
            });

            frame.add(welcomeView);
            frame.setVisible(true);
        });
    }
}