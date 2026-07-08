package it.unicam.cs.mpgc.rpg130675.gui;

import javax.swing.*;
import java.awt.*;


/**
 * Schermata di Game Over. Ha la singola responsabilità di mostrare
 * l'esito negativo della partita e permettere l'uscita.
 */
public class GameOverView extends JPanel {

    private JButton exitButton;

    public GameOverView(String nomeStudente) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(40, 40, 40)); // Sfondo scuro per il Game Over
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("GAME OVER - BURNOUT TOTALE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.RED);
        gbc.gridy = 0;
        this.add(titleLabel, gbc);

        JLabel messageLabel = new JLabel(
                "<html><div style='text-align: center;'>" +
                        "Mi dispiace " + nomeStudente + ", lo stress ha superato il limite sopportabile.<br>" +
                        "Hai abbandonato gli studi per preservare la tua salute mentale." +
                        "</div></html>", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        this.add(messageLabel, gbc);

        exitButton = new JButton("Chiudi Gioco");
        exitButton.addActionListener(e -> System.exit(0)); // Chiude l'applicazione
        gbc.gridy = 2;
        this.add(exitButton, gbc);
    }
}