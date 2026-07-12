package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
import it.unicam.cs.mpgc.rpg130675.gui.GameUIListener;
import it.unicam.cs.mpgc.rpg130675.model.azioni.Attivita;
import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.MotoreEventi;
import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;
import it.unicam.cs.mpgc.rpg130675.persistence.JsonStoricoRepository;
import it.unicam.cs.mpgc.rpg130675.model.carriera.LibrettoUniversitario;
import it.unicam.cs.mpgc.rpg130675.persistence.StoricoRepository;

import java.util.List;
import java.util.Optional;
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
    private MotoreEventi motoreEventi;

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
        Optional<EventoCasuale> eventoMisterioso = motoreEventi.provaEstrazione(this.mazzoEventi);

        eventoMisterioso.ifPresent(evento -> {
            evento.innesca(this.studente);

            if (this.uiListener != null) {
                uiListener.mostraMessaggio(evento.getTitolo(), evento.getDescrizione());
            }
        });
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
            gestisciSessioneEsame();
        }else {
                uiListener.mostraMessaggio("Bocciato...", "Hai fallito l'esame di " + esameAttuale.getNomeMateria() + ". Lo stress aumenta!");
                this.turniAllEsame = 10;
                if (controllaGameOver()) {
                    return;
                }
            }

        aggiornaGrafica();
    }

    private void gestisciSessioneEsame() {
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
        }
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

        if (uiListener != null) {
            uiListener.mostraLibretto(libretto);
        }
    }
}
