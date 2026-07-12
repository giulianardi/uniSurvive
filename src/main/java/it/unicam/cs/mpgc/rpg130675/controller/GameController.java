package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.gui.GameUIListener;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Attivita;
import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;
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

    private Studente studente;
    private List<Esame> esamiDaSostenere;
    private List<EventoCasuale> mazzoEventi;
    private Esame esameAttuale;
    private int turniAllEsame;

    private Random generatoreCasuale;

    private StoricoRepository archivioStorico;

    private GameUIListener uiListener;

    public GameController(GameUIListener uiListener) {
        this.uiListener = uiListener;
    }

    private boolean controllaGameOver() {
        if (this.studente.isInBurnout()) {
            if (this.uiListener != null) {
                this.uiListener.triggerGameOver(this.studente.getNome());
            }
            return true;
        }
        return false;
    }

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
                uiListener.mostraMessaggio(eventoMisterioso.getTitolo(), eventoMisterioso.getDescrizione());
            }
        }

    }


    /**
     * Gestisce l'avanzamento del tempo, i controlli di stato e lo svolgimento degli esami.
     */
    private void avanzaTurno() {
        if (controllaGameOver()) {
            return;
        }

        gestisciEventiCasuali();

        if (controllaGameOver()) {
            return;
        }

        this.turniAllEsame--;

        if (this.turniAllEsame <= 0 && esameAttuale != null) {
            boolean esitoPromozione = esameAttuale.sostieni(this.studente);

            if (esitoPromozione) {
                uiListener.mostraMessaggio("Esame Superato!", "Hai superato l'esame di " + esameAttuale.getNomeMateria() + "!");
                archivioStorico.salvaEsameSuperato(esameAttuale);
                if (!this.esamiDaSostenere.isEmpty()) {
                    this.esameAttuale = this.esamiDaSostenere.remove(0);
                    this.turniAllEsame = 20;
                } else {
                    uiListener.triggerVittoria(this.studente.getNome());
                    return;
                }
            } else {
                uiListener.mostraMessaggio("Bocciato...", "Hai fallito l'esame di " + esameAttuale.getNomeMateria() + ". Lo stress aumenta!");
                this.turniAllEsame = 10;
                if (controllaGameOver()) {
                    return;
                }
            }
        }

        aggiornaGrafica();
    }

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
