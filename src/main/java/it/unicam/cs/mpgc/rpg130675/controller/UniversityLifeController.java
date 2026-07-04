package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.model.azioni.Attivita;
import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;
import it.unicam.cs.mpgc.rpg130675.controller.Creatore;

import java.util.List;
import java.util.Random;



public class UniversityLifeController{

    private StudenteBase studente;
    private List<Esame> esamiDaSostenere;
    private List<EventoCasuale> mazzoEventi;
    private Esame esameAttuale;
    private int turniAllEsame;

    private Random generatoreCasuale;

    public void avviaPartita(String nomeGiocatore, String facoltaScelta) {
        Creatore creatore = new Creatore();

        this.studente = creatore.creaStudente(nomeGiocatore, facoltaScelta);
        this.esamiDaSostenere = creatore.creaListaEsame();
        this.mazzoEventi = creatore.creaListaEventi();

        this.turniAllEsame = 20;
        this.generatoreCasuale = new Random();

        if (!this.esamiDaSostenere.isEmpty()) {
            this.esameAttuale = this.esamiDaSostenere.remove(0);
        }
    }

    public void eseguiAzione(Attivita attivitaScelta) {
        attivitaScelta.esegui(this.studente);
        avanzaTurno();
    }

    // Genera un numero random (es. da 1 a 100).
    // Se esce <= 15, innesca un imprevisto.
    private void gestisciEventiCasuali() {
        int possibilita = generatoreCasuale.nextInt(100)+1;
        if(possibilita <= 15){
            int indicePescato = generatoreCasuale.nextInt(this.mazzoEventi.size());
            EventoCasuale eventoMisterioso = this.mazzoEventi.get(indicePescato);
            eventoMisterioso.innesca(this.studente);
        }

    }

    private void avanzaTurno() {
        this.turniAllEsame--;
        if (this.turniAllEsame <= 0) {
            esameAttuale.sostieni(this.studente);
        }
    }
}
