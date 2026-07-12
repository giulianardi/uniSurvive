package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.carriera.EsameSalvato;
import it.unicam.cs.mpgc.rpg130675.model.carriera.LibrettoUniversitario;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class GameUIListener {
    private final MainGameView gameView;
    private final Stage stage;

    public GameUIListener(MainGameView gameView, Stage stage) {
        this.gameView = gameView;
        this.stage = stage;
    }

    public void aggiornaStatistiche(int turno, int conoscenza, int energia, int stress, int denaro) {
        gameView.updateStats(turno, conoscenza, energia, stress, denaro);
    }

    public void mostraMessaggio(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evento Casuale");
        alert.setHeaderText(titolo);
        alert.setContentText(messaggio);

        alert.showAndWait();
    }

    public void mostraLibretto(LibrettoUniversitario libretto) {
        if (libretto == null || libretto.getEsamiSuperati().isEmpty()) {
            mostraMessaggio("Libretto Vuoto", "Non hai ancora superato nessun esame nella tua carriera!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int cfuTotali = 0;

        for (EsameSalvato esame : libretto.getEsamiSuperati()) {
            sb.append("✔ ").append(esame.getNomeMateria())
                    .append(" (").append(esame.getCfuGuadagnati()).append(" CFU)\n");
            cfuTotali += esame.getCfuGuadagnati();
        }
        sb.append("\nTotale CFU accumulati globalmente: ").append(cfuTotali);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Libretto Universitario");
        alert.setHeaderText("Esami Superati nella Storia");
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }

    public void triggerGameOver(String nomeStudente) {
        GameOverView gameOverView = new GameOverView(nomeStudente);
        Scene currentScene = stage.getScene();
        currentScene.setRoot(gameOverView);
    }

    public void triggerVittoria(String nomeStudente) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("VITTORIA - LAUREA");
        alert.setHeaderText("Congratulazioni " + nomeStudente + "!");
        alert.setContentText("Hai superato tutti gli esami e ti sei laureato!");
        alert.showAndWait();
        Platform.exit();
    }
}