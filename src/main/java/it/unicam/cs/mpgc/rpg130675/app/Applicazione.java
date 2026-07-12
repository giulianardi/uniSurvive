package it.unicam.cs.mpgc.rpg130675.app;

import it.unicam.cs.mpgc.rpg130675.controller.GameController;
import it.unicam.cs.mpgc.rpg130675.gui.GameUIListener;
import it.unicam.cs.mpgc.rpg130675.gui.MainGameView;
import it.unicam.cs.mpgc.rpg130675.gui.WelcomeView;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Festa;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Lavoro;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Riposo;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Studio;

import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Applicazione extends Application {

    private Stage primaryStage;
    private Scene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UniSurvive");

        WelcomeView welcomeView = new WelcomeView();

        mainScene = new Scene(welcomeView, 600, 500);

        caricaStili(mainScene);

        welcomeView.setWelcomeScreenListener((nome, facolta) -> avviaGioco(nome, facolta));

        this.primaryStage.setScene(mainScene);
        this.primaryStage.show();
    }

    private void caricaStili(Scene scene) {
        try {
            String cssPath = Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (NullPointerException e) {
            System.err.println("Attenzione: file style.css non trovato in /src/main/resources/css/." +
                    " L'applicazione userà il tema di default di JavaFX.");
        }
    }

    private void avviaGioco(String nome, Facolta facolta) {
        MainGameView gameView = new MainGameView();

        GameUIListener uiListener = new GameUIListener(gameView, primaryStage);
        GameController controller = new GameController(uiListener);

        controller.avviaPartita(nome, facolta);

        gameView.setMainGameListener(azioneScelta -> {
            System.out.println("Azione ricevuta dalla UI: " + azioneScelta);

            try {
                switch (azioneScelta) {
                    case STUDIA -> controller.eseguiAzione(new Studio());
                    case LAVORA -> controller.eseguiAzione(new Lavoro());
                    case FESTA -> controller.eseguiAzione(new Festa());
                    case DORMI -> controller.eseguiAzione(new Riposo());
                    case LIBRETTO -> controller.mostraLibretto();
                    default -> System.out.println("Azione non supportata!");
                }
            } catch (Exception ex) {
                System.err.println("Errore durante l'esecuzione dell'azione:");
                ex.printStackTrace();
            }
        });

        mainScene.setRoot(gameView);
    }
}