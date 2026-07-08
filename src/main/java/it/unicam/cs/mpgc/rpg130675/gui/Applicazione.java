package it.unicam.cs.mpgc.rpg130675.gui;

// Importiamo il controller
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import it.unicam.cs.mpgc.rpg130675.controller.UniversityLifeController;

// Importiamo le azioni dal modello per far funzionare i bottoni
import it.unicam.cs.mpgc.rpg130675.model.azioni.Studio;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Lavoro;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Festa;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Riposo;

import javax.swing.*;

public class Applicazione {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
        } catch (Exception ex) {
            System.err.println("Impossibile caricare il tema FlatLaf.");
        }

        // Assicuriamoci che la UI venga creata nel thread corretto per Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulatore di Vita Universitaria - RPG");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null); // Centra la finestra sullo schermo

            // 1. Creiamo la schermata iniziale
            WelcomeView welcomeView = new WelcomeView();

            // 2. Definiamo cosa succede quando l'utente clicca su "Inizia"
            welcomeView.setWelcomeScreenListener((nome, facolta) -> {

                // Creiamo subito la schermata principale (ma non la mostriamo ancora)
                MainGameView gameView = new MainGameView();

                // Creiamo l'implementazione del "walkie-talkie" per far comunicare Controller e Grafica
                GameUIListener uiListener = new GameUIListener() {
                    @Override
                    public void aggiornaStatistiche(int turno, int conoscenza, int energia, int stress, int denaro) {
                        gameView.updateStats(turno, conoscenza, energia, stress, denaro);
                    }

                    @Override
                    public void mostraMessaggio(String titolo, String messaggio) {
                        gameView.showEventMessage(titolo, messaggio);
                    }

                    @Override
                    public void triggerGameOver(String nomeStudente) {
                        // Creiamo e mostriamo la schermata rossa di Game Over
                        GameOverView gameOverView = new GameOverView(nomeStudente);
                        frame.getContentPane().removeAll();
                        frame.add(gameOverView);
                        frame.revalidate();
                        frame.repaint();
                    }

                    @Override
                    public void triggerVittoria(String nomeStudente) {
                        // Mostriamo un pop-up di vittoria e chiudiamo il gioco
                        JOptionPane.showMessageDialog(frame,
                                "Congratulazioni " + nomeStudente + "!\nHai superato tutti gli esami e ti sei laureato!",
                                "VITTORIA - LAUREA",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                };

                // Istanziamo il Controller passandogli l'interfaccia grafica appena creata
                UniversityLifeController controller = new UniversityLifeController(uiListener);

                // Avviamo la logica di gioco nel controller (questo creerà lo Studente e aggiornerà i numeri iniziali)
                controller.avviaPartita(nome, facolta);

                // Colleghiamo i click dei bottoni della View al metodo del Controller
                // 6. Colleghiamo i bottoni della Vista al Controller
                gameView.setMainGameListener(azioneScelta -> {
                    // TRACCIANTE 1: Vediamo se il bottone arriva fin qui
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
                        // TRACCIANTE 2: Forse c'è un errore nascosto che fa bloccare l'azione?
                        System.out.println("ERRORE DURANTE L'AZIONE: ");
                        ex.printStackTrace();
                    }
                });

                // Togliamo la schermata di benvenuto e inseriamo quella del gioco
                frame.getContentPane().removeAll();
                frame.add(gameView);
                frame.revalidate();
                frame.repaint();
            });

            // Mostriamo la finestra iniziale
            frame.add(welcomeView);
            frame.setVisible(true);
        });
    }
}