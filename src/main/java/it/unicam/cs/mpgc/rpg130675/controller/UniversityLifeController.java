package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.gui.GameUIListener;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Attivita;
import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;
import it.unicam.cs.mpgc.rpg130675.persistence.JsonStoricoRepository;
import it.unicam.cs.mpgc.rpg130675.persistence.StoricoRepository;

import java.util.List;
import java.util.Random;



public class UniversityLifeController {

    private StudenteBase studente;
    private List<Esame> esamiDaSostenere;
    private List<EventoCasuale> mazzoEventi;
    private Esame esameAttuale;
    private int turniAllEsame;

    private Random generatoreCasuale;

    private StoricoRepository archivioStorico;

    private GameUIListener uiListener;

    public UniversityLifeController(GameUIListener uiListener) {
        this.uiListener = uiListener;
    }

    /**
     * Metodo helper privato. Controlla se lo studente ha raggiunto o superato
     * il limite massimo di stress (Burnout).
     * * @return true se il giocatore ha perso, false altrimenti.
     */
    private boolean controllaGameOver() {
        if (this.studente.isInBurnout()) {
            // Comunichiamo alla Vista di mostrare la schermata di Game Over
            // bloccando l'esecuzione di qualsiasi altra logica.
            if (this.uiListener != null) {
                this.uiListener.triggerGameOver(this.studente.getNome());
            }
            return true;
        }
        return false;
    }

    public void avviaPartita(String nomeGiocatore, String facoltaScelta) {
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

    public void eseguiAzione(Attivita attivitaScelta) {
        try {
            attivitaScelta.esegui(this.studente);
            avanzaTurno();
        } catch (Exception e) {
            // QUESTO COMANDO È FONDAMENTALE PER IL DEBUG:
            // Stampa l'errore rosso in console rivelando il colpevole!
            System.err.println("!!! ATTENZIONE: ERRORE CATTURATO !!!");
            e.printStackTrace();

            if (this.uiListener != null) {
                uiListener.mostraMessaggio("Azione non permessa", e.getMessage());
            }
        }
    }

    // Genera un numero random (es. da 1 a 100).
    // Se esce <= 15, innesca un imprevisto.
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

    private void avanzaTurno() {

        // 1. Controllo immediato: l'azione appena eseguita ha causato il burnout?
        if (controllaGameOver()) {
            return; // Interrompe il turno, il gioco è finito
        }

        // 2. Innesco degli Eventi Casuali
        gestisciEventiCasuali();

        // 3. Secondo controllo: un evento negativo (es. Computer Guasto) ha causato il burnout?
        if (controllaGameOver()) {
            return;
        }

        // 4. Gestione del timer dell'Esame
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

                // 5. Terzo e ultimo controllo: la delusione della bocciatura ha causato il burnout?
                if (controllaGameOver()) {
                    return;
                }
            }
        }

        // Se siamo arrivati fin qui, il giocatore è sopravvissuto al turno.
        // Aggiorniamo i dati a schermo.
        aggiornaGrafica();
    }

    /**
     * Metodo helper privato per sincronizzare la vista con lo stato attuale del modello.
     * Raccoglie i dati aggiornati dallo studente e li invia all'interfaccia grafica.
     */
    private void aggiornaGrafica() {
        if (this.uiListener != null) {
            this.uiListener.aggiornaStatistiche(
                    this.turniAllEsame, // Mostriamo quanti turni mancano alla scadenza
                    this.studente.getConoscenza(),
                    this.studente.getEnergia(),
                    this.studente.getStress(),
                    this.studente.getDenaro()
            );
        }
    }

    public void mostraLibretto() {
        if (archivioStorico == null) return;

        it.unicam.cs.mpgc.rpg130675.persistence.LibrettoUniversitario libretto = archivioStorico.caricaStorico();

        if (libretto == null || libretto.getEsamiSuperati().isEmpty()) {
            if (uiListener != null) uiListener.mostraMessaggio("Libretto Vuoto", "Non hai ancora superato nessun esame nella tua carriera!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- ESAMI SUPERATI NELLA STORIA ---\n\n");
        int cfuTotali = 0;

        for (it.unicam.cs.mpgc.rpg130675.persistence.EsameSalvato esame : libretto.getEsamiSuperati()) {
            sb.append("✔ ").append(esame.getNomeMateria()).append(" (").append(esame.getCfuGuadagnati()).append(" CFU)\n");
            cfuTotali += esame.getCfuGuadagnati();
        }
        sb.append("\nTotale CFU accumulati globalmente: ").append(cfuTotali);

        if (uiListener != null) {
            uiListener.mostraMessaggio("Libretto Universitario", sb.toString());
        }
    }
}
