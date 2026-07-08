package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;

import javax.swing.*;
import java.awt.*;

/**
 * Gestisce esclusivamente la visualizzazione e la raccolta dati iniziale.
 * Non contiene alcuna logica di creazione del personaggio (Single Responsibility Principle).
 */
public class WelcomeView extends JPanel {

    private JTextField nameField;
    private JComboBox<Facolta> facultyComboBox;
    private JButton startButton;

    // Il listener a cui comunicheremo le azioni dell'utente
    private WelcomeScreenListener listener;

    public WelcomeView() {
        initializeComponents();
        setupLayout();
        setupInteractions();
    }

    public void setWelcomeScreenListener(WelcomeScreenListener listener) {
        this.listener = listener;
    }

    private void initializeComponents() {
        nameField = new JTextField(15);

        // Le scelte sono hardcoded per ora, ma in una versione avanzata
        // potrebbero arrivare dal Model (es. un file di configurazione)
        facultyComboBox = new JComboBox<>(Facolta.values());

        startButton = new JButton("Inizia la tua Carriera Universitaria");
    }

    private void setupLayout() {
        // Usiamo un layout pulito per centrare i contenuti
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titolo
        JLabel titleLabel = new JLabel("UniSurvive", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);

        // Campo Nome
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(new JLabel("Nome dello Studente:"), gbc);

        gbc.gridx = 1;
        this.add(nameField, gbc);

        // Campo Facoltà
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JLabel("Scegli la Facoltà:"), gbc);

        gbc.gridx = 1;
        this.add(facultyComboBox, gbc);

        // Bottone Start
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(startButton, gbc);
    }

    private void setupInteractions() {
        nameField.addActionListener(e -> startButton.doClick());

        startButton.addActionListener(e -> {
            String enteredName = nameField.getText().trim();
            Facolta selectedFaculty = (Facolta) facultyComboBox.getSelectedItem();

            if (enteredName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Il nome dello studente non può essere vuoto!",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Se tutto è corretto, avvisiamo chi si occupa della logica
            if (listener != null) {
                listener.onGameStartRequested(enteredName, selectedFaculty);
            }
        });
    }
}