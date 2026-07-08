package it.unicam.cs.mpgc.rpg130675.gui;
import it.unicam.cs.mpgc.rpg130675.model.azioni.TipoAzione;

import javax.swing.*;
import java.awt.*;

public class MainGameView extends JPanel {

    // Etichette per mostrare i dati
    private JLabel turnLabel;
    private JLabel knowledgeLabel;
    private JLabel energyLabel;
    private JLabel stressLabel;
    private JLabel moneyLabel;

    // Bottoni per le azioni
    private JButton studyButton;
    private JButton workButton;
    private JButton partyButton;
    private JButton sleepButton;

    //Bottone per vedere il libretto
    private JButton librettoButton;

    private MainGameListener listener;

    public MainGameView() {
        initializeComponents();
        setupLayout();
        setupInteractions();
    }

    public void setMainGameListener(MainGameListener listener) {
        this.listener = listener;
    }

    private void initializeComponents() {
        turnLabel = new JLabel("Turno: 20", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));

        knowledgeLabel = new JLabel("Conoscenza: 0");
        energyLabel = new JLabel("Energia: 100");
        stressLabel = new JLabel("Stress: 0");
        moneyLabel = new JLabel("Denaro: 50€");

        studyButton = new JButton("Studia");
        workButton = new JButton("Lavora (Part-Time)");
        partyButton = new JButton("Festa (Svago)");
        sleepButton = new JButton("Dormi / Riposa");

        librettoButton = new JButton("📖 Libretto Esami");

        // Colore Studio: Azzurro
        studyButton.setBackground(new Color(52, 152, 219));
        studyButton.setForeground(Color.WHITE); // Testo bianco
        studyButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Colore Lavoro: Rosso/Arancio
        workButton.setBackground(new Color(231, 76, 60));
        workButton.setForeground(Color.WHITE);
        workButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Colore Festa: Viola
        partyButton.setBackground(new Color(155, 89, 182));
        partyButton.setForeground(Color.WHITE);
        partyButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Colore Dormi: Verde
        sleepButton.setBackground(new Color(46, 204, 113));
        sleepButton.setForeground(Color.WHITE);
        sleepButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Colore Libretto: Giallo scuro / Oro
        librettoButton.setBackground(new Color(243, 156, 18));
        librettoButton.setForeground(Color.WHITE);
    }

    private void setupLayout() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Pannello Superiore: Titolo e Turno
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(turnLabel, BorderLayout.CENTER);
        topPanel.add(librettoButton, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        // Pannello Centrale: Statistiche
        JPanel statsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistiche Studente"));
        statsPanel.add(knowledgeLabel);
        statsPanel.add(energyLabel);
        statsPanel.add(stressLabel);
        statsPanel.add(moneyLabel);
        this.add(statsPanel, BorderLayout.CENTER);

        // Pannello Inferiore: Azioni disponibili
        JPanel actionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Azioni del Turno"));
        actionsPanel.add(studyButton);
        actionsPanel.add(workButton);
        actionsPanel.add(partyButton);
        actionsPanel.add(sleepButton);
        this.add(actionsPanel, BorderLayout.SOUTH);
    }

    private void setupInteractions() {
        // Quando un bottone viene premuto, avvisiamo il listener
        studyButton.addActionListener(e -> notifyAction(TipoAzione.STUDIA));
        workButton.addActionListener(e -> notifyAction(TipoAzione.LAVORA));
        partyButton.addActionListener(e -> notifyAction(TipoAzione.FESTA));
        sleepButton.addActionListener(e -> notifyAction(TipoAzione.DORMI));

        librettoButton.addActionListener(e -> notifyAction(TipoAzione.LIBRETTO));
    }

    private void notifyAction(TipoAzione actionType) {
        if (listener != null) {
            listener.onActionSelected(actionType);
        }
    }

    /**
     * Metodo che il Controller chiamerà per aggiornare la grafica
     * in base allo stato reale del Modello.
     */
    public void updateStats(int turn, int knowledge, int energy, int stress, int money) {
        turnLabel.setText("Turno: " + turn);
        knowledgeLabel.setText("Conoscenza: " + knowledge);
        energyLabel.setText("Energia: " + energy);
        stressLabel.setText("Stress: " + stress);
        moneyLabel.setText("Denaro: " + money + "€");
    }

    /**
     * Metodo utile per mostrare a video gli imprevisti (Eventi casuali o Burnout)
     */
    public void showEventMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}