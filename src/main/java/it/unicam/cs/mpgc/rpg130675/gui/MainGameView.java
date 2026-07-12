package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.azioni.TipoAzione;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MainGameView extends BorderPane {

    private Label turnLabel;
    private Label knowledgeLabel;
    private Label energyLabel;
    private Label stressLabel;
    private Label moneyLabel;

    private Button studyButton;
    private Button workButton;
    private Button partyButton;
    private Button sleepButton;
    private Button librettoButton;

    private MainGameListener listener;

    public MainGameView() {
        initializeComponents();
        setupLayout();
        setupInteractions();
    }

    public void setMainGameListener(MainGameListener listener) {
        this.listener = listener;
    }

    private Button createActionButton(TipoAzione azione, String cssClass) {
        Button btn = new Button();
        btn.getStyleClass().addAll("action-button", cssClass);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);

        VBox content = new VBox(5);
        content.setAlignment(Pos.CENTER);

        Label title = new Label(azione.getNomeDescrittivo());
        title.getStyleClass().add("action-button-title");

        Label subtitle = new Label(azione.getImpattoStatistiche());
        subtitle.getStyleClass().add("action-button-subtitle");

        content.getChildren().addAll(title, subtitle);
        btn.setGraphic(content);
        return btn;
    }

    private void initializeComponents() {
        turnLabel = new Label("Turni all'esame: 20");
        turnLabel.getStyleClass().add("turn-label");

        knowledgeLabel = new Label("Conoscenza: 0");
        energyLabel = new Label("Energia: 100");
        stressLabel = new Label("Stress: 0");
        moneyLabel = new Label("Denaro: 0€");

        studyButton = createActionButton(TipoAzione.STUDIA, "btn-study");
        workButton = createActionButton(TipoAzione.LAVORA, "btn-work");
        partyButton = createActionButton(TipoAzione.FESTA, "btn-party");
        sleepButton = createActionButton(TipoAzione.DORMI, "btn-sleep");

        librettoButton = new Button("📖 " + TipoAzione.LIBRETTO.getNomeDescrittivo());
        librettoButton.getStyleClass().add("btn-libretto");
    }

    private void setupLayout() {
        this.setPadding(new Insets(15));

        BorderPane topPanel = new BorderPane();
        topPanel.setLeft(turnLabel);
        topPanel.setRight(librettoButton);
        topPanel.setPadding(new Insets(0, 0, 15, 0));
        this.setTop(topPanel);

        VBox statsPanel = new VBox(10);
        statsPanel.getStyleClass().add("stats-panel");
        statsPanel.getChildren().addAll(
                new Label("Statistiche Studente"),
                knowledgeLabel, energyLabel, stressLabel, moneyLabel
        );
        this.setCenter(statsPanel);
        BorderPane.setMargin(statsPanel, new Insets(0, 0, 15, 0));

        GridPane actionsPanel = new GridPane();
        actionsPanel.setHgap(10);
        actionsPanel.setVgap(10);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(50);
        actionsPanel.getColumnConstraints().addAll(cc, cc);
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(50);
        actionsPanel.getRowConstraints().addAll(rc, rc);

        actionsPanel.add(studyButton, 0, 0);
        actionsPanel.add(workButton, 1, 0);
        actionsPanel.add(partyButton, 0, 1);
        actionsPanel.add(sleepButton, 1, 1);

        this.setBottom(actionsPanel);
    }

    private void setupInteractions() {
        studyButton.setOnAction(e -> notifyAction(TipoAzione.STUDIA));
        workButton.setOnAction(e -> notifyAction(TipoAzione.LAVORA));
        partyButton.setOnAction(e -> notifyAction(TipoAzione.FESTA));
        sleepButton.setOnAction(e -> notifyAction(TipoAzione.DORMI));
        librettoButton.setOnAction(e -> notifyAction(TipoAzione.LIBRETTO));
    }

    private void notifyAction(TipoAzione actionType) {
        if (listener != null) {
            listener.onActionSelected(actionType);
        }
    }

    public void updateStats(int turn, int knowledge, int energy, int stress, int money) {
        turnLabel.setText("Turni all'esame: " + turn);
        knowledgeLabel.setText("Conoscenza: " + knowledge);
        energyLabel.setText("Energia: " + energy);
        stressLabel.setText("Stress: " + stress);
        moneyLabel.setText("Denaro: " + money + "€");
    }
}