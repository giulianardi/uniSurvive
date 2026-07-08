package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.gui.GameUIListener;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Attivita;
import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;
import it.unicam.cs.mpgc.rpg130675.persistence.EsameSalvato;
import it.unicam.cs.mpgc.rpg130675.persistence.JsonStoricoRepository;
import it.unicam.cs.mpgc.rpg130675.persistence.LibrettoUniversitario;
import it.unicam.cs.mpgc.rpg130675.persistence.StoricoRepository;

import java.util.List;
import java.util.Random;


/**
 * Gestisce la logica di gioco, coordinando le interazioni tra studente,
 * attività, eventi casuali e GUI.
 */
public class GameController {

    private StudenteBase studente;
    private List<Esame> esamiDaSostenere;
    private List<EventoCasuale> mazzoEventi;
    private Esame esameAttuale;
    private int turniAllEsame;

    private Random generatoreCasuale;

    private StoricoRepository archivioStorico;

    private GameUIListener uiListener;

    /**
     * Inizializza il controller di gioco.
     *
     * @param uiListener il listener per comunicare con l'interfaccia grafica.
     */
    public GameController(GameUIListener uiListener) {
        this.uiListener = uiListener;
    }

    /**
     * Metodo helper privato. Controlla se lo studente ha raggiunto o superato
     * il limite massimo di stress (Burnout). In caso lo abbia raggiunto comunica
     * con la grafica dicendogli di avviare la schermata di Game Over.
     *
     * * @return true se il giocatore ha perso, false altrimenti.
     */
    private boolean controllaGameOver() {
        if (this.studente.isInBurnout()) {
            if (this.uiListener != null) {
                this.uiListener.triggerGameOver(this.studente.getNome());
            }
            return true;
        }
        return false;
    }

    /**
     * Inizializza una nuova partita preparando studente, esami ed eventi.
     *
     * @param nomeGiocatore il nome del personaggio.
     * @param facoltaScelta la facoltà scelta per il percorso accademico.
     */
    public void avviaPartita(String nomeGiocatore, Facolta facoltaScelta) {
        Creatore creatore = new Creatore();

        this.studente = creatore.creaStudente(nomeGiocatore, facoltaScelta);
        this.esamiDaSostenere = creatore.creaListaEsame(facoltaScelta);
        this.mazzoEventi = creatore.creaListaEventi();

        this.turniAllEsame = 20;
        this.generatoreCasuale = new Random();

        this.archivioStorico = new JsonStoricoRepository();

        this.archivioStorico.azzeraStorico();

        if (!this.esamiDaSostenere.isEmpty()) {
            this.esameAttuale = this.esamiDaSostenere.remove(0);
        }
    }

    /**
     * Esegue l'attività selezionata dallo studente e avanza al turno successivo.
     *
     * @param attivitaScelta l'attività che lo studente intende svolgere.
     */
    public void eseguiAzione(Attivita attivitaScelta) {
        try {
            attivitaScelta.esegui(this.studente);
            System.out.println("Azione completata con successo!");
            avanzaTurno();
        } catch (EccezioneInsufficienzaRisorse e) {
            System.out.println("Impossibile eseguire l'azione: " + e.getMessage());

            if (this.uiListener != null) {
                uiListener.mostraMessaggio("Azione non permessa", e.getMessage());
            }
        }
    }

    /**
     * Gestisce la probabilità di occorrenza di un evento casuale.
     * <p>
     * Genera un numero random da 1 a 100.
     * Se esce un numero minore o uguale a 15, innesca un imprevisto.
     */
    private void gestisciEventiCasuali() {
        if (this.mazzoEventi == null || this.mazzoEventi.isEmpty()) {
            return;
        }

        int possibilita = generatoreCasuale.nextInt(100) + 1;
        if (possibilita <= 15) {
            int indicePescato = generatoreCasuale.nextInt(this.mazzoEventi.size());
            EventoCasuale eventoMisterioso = this.mazzoEventi.get(indicePescato);
            eventoMisterioso.innesca(this.studente);

            if (this.uiListener != null) {
                String messaggioFormattato = "<html><b>" + eventoMisterioso.getTitolo() + "</b><br><br>" + eventoMisterioso.getDescrizione() + "</html>";
                uiListener.mostraMessaggio("Evento Casuale", messaggioFormattato);
            }
        }

    }

    private void GameOver(){
        if (controllaGameOver()) {
            return;
        }
    }

    /**
     * Gestisce l'avanzamento del tempo, i controlli di stato e lo svolgimento degli esami.
     */
    private void avanzaTurno() {

        //Controllo immediato: l'azione appena eseguita ha causato il burnout?
        GameOver();

        //Innesco degli Eventi Casuali
        gestisciEventiCasuali();

        //Secondo controllo: un evento negativo ha causato il burnout?
        GameOver();

        this.turniAllEsame--;

        if (this.turniAllEsame <= 0 && esameAttuale != null) {
            boolean esitoPromozione = esameAttuale.sostieni(this.studente);

            if (esitoPromozione) {
                uiListener.mostraMessaggio("Esame Superato!", "Hai superato l'esame di " + esameAttuale.getNomeMateria() + "!");
                archivioStorico.salvaEsameSuperato(esameAttuale);
                // Preparazione per il prossimo esame
                if (!this.esamiDaSostenere.isEmpty()) {
                    this.esameAttuale = this.esamiDaSostenere.remove(0);
                    this.turniAllEsame = 20; // Resetta il timer
                } else {
                    // Vittoria
                    uiListener.triggerVittoria(this.studente.getNome());
                    return;
                }
            } else {
                uiListener.mostraMessaggio("Bocciato...", "Hai fallito l'esame di " + esameAttuale.getNomeMateria() + ". Lo stress aumenta!");
                this.turniAllEsame = 10; // Penalità di tempo per riprovare l'esame

                //Terzo e ultimo controllo: la delusione della bocciatura ha causato il burnout?
                GameOver();
            }
        }

        aggiornaGrafica();
    }

    /**
     * Metodo helper privato per sincronizzare la vista con lo stato attuale del modello.
     * Raccoglie i dati aggiornati dallo studente e li invia all'interfaccia grafica.
     */
    private void aggiornaGrafica() {
        if (this.uiListener != null) {
            this.uiListener.aggiornaStatistiche(
                    this.turniAllEsame,
                    this.studente.getConoscenza(),
                    this.studente.getEnergia(),
                    this.studente.getStress(),
                    this.studente.getDenaro()
            );
        }
    }

    /**
     * Recupera lo storico degli esami salvati e mostra un riepilogo all'utente.
     */
    public void mostraLibretto() {
        if (archivioStorico == null) return;

        LibrettoUniversitario libretto = archivioStorico.caricaStorico();

        if (libretto == null || libretto.getEsamiSuperati().isEmpty()) {
            if (uiListener != null) uiListener.mostraMessaggio("Libretto Vuoto", "Non hai ancora superato nessun esame nella tua carriera!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- ESAMI SUPERATI NELLA STORIA ---\n\n");
        int cfuTotali = 0;

        for (EsameSalvato esame : libretto.getEsamiSuperati()) {
            sb.append("✔ ").append(esame.getNomeMateria()).append(" (").append(esame.getCfuGuadagnati()).append(" CFU)\n");
            cfuTotali += esame.getCfuGuadagnati();
        }
        sb.append("\nTotale CFU accumulati globalmente: ").append(cfuTotali);

        if (uiListener != null) {
            uiListener.mostraMessaggio("Libretto Universitario", sb.toString());
        }
    }
}
