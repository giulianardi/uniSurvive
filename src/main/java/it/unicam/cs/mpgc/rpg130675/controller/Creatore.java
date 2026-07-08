package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.esame.EsameOrale;
import it.unicam.cs.mpgc.rpg130675.model.esame.EsameScritto;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.Aperitivo;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.AppuntiPerfetti;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.BorsaDiStudio;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.PaccoDaGiu;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus.*;
import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;

import java.util.ArrayList;
import java.util.List;


/**
 * Gestisce la creazione e l'inizializzazione delle entità principali del gioco,
 * come lo studente, gli eventi casuali e gli esami.
 */
public class Creatore {

    /**
     * Crea un nuovo studente assegnandogli le statistiche base.
     *
     * @param nomeScelto    il nome scelto per lo studente.
     * @param facoltaScelta la facoltà di appartenenza che definisce il percorso di studi.
     * @return un oggetto {@link StudenteBase} inizializzato.
     */
    public StudenteBase creaStudente(String nomeScelto, Facolta facoltaScelta) {
        return new StudenteBase(nomeScelto, facoltaScelta);
    }

    /**
     * Genera una lista di esami da sostenere in base alla facoltà selezionata.
     *
     * @param facoltaScelta la facoltà di appartenenza dello studente.
     * @return una lista di oggetti {@link Esame} specifici per la facoltà.
     */
    public List<Esame> creaListaEsame(Facolta facoltaScelta) {
        List<Esame> esamiDaSostenere = new ArrayList<>();

        switch (facoltaScelta) {
            case INFORMATICA_COMUNICAZIONE:
                esamiDaSostenere.add(new EsameScritto("Matematica", 50, 12));
                esamiDaSostenere.add(new EsameScritto("Programmazione", 60, 12));
                esamiDaSostenere.add(new EsameScritto("Inglese", 20, 6));
                esamiDaSostenere.add(new EsameScritto("Fondamenti dell'informatica", 40, 6));
                esamiDaSostenere.add(new EsameScritto("Architettura degli elaboratori", 30, 6));
                esamiDaSostenere.add(new EsameScritto("Diritto dei prodotti digitali", 20, 6));
                esamiDaSostenere.add(new EsameOrale("Comunicazione e marketing", 60, 12));
                esamiDaSostenere.add(new EsameScritto("Modellazione della conoscenza", 50, 12));
                break;
            case CHIMICA:
                esamiDaSostenere.add(new EsameScritto("Matematica 1", 50, 12));
                esamiDaSostenere.add(new EsameOrale("Chimica generale", 60, 14));
                esamiDaSostenere.add(new EsameScritto("Inglese", 20, 6));
                esamiDaSostenere.add(new EsameOrale("Matematica 2", 40, 6));
                esamiDaSostenere.add(new EsameScritto("Fisica 1", 30, 6));
                esamiDaSostenere.add(new EsameOrale("Chimica analitica 1", 20, 14));
                esamiDaSostenere.add(new EsameOrale("Chimica Fisica 1", 60, 12));
                esamiDaSostenere.add(new EsameOrale("Chimica Organica 1", 50, 12));
                break;
        }
        return esamiDaSostenere;
    }

    /**
     * Crea e restituisce una lista contenente tutti gli eventi casuali (bonus e malus)
     * disponibili nel gioco.
     *
     * @return una lista di oggetti {@link EventoCasuale}.
     */
    public List<EventoCasuale> creaListaEventi(){
        List<EventoCasuale> mazzoEventi = new ArrayList<>();

        // Aggiungo tutti gli eventi BONUS
        mazzoEventi.add(new Aperitivo());
        mazzoEventi.add(new AppuntiPerfetti());
        mazzoEventi.add(new BorsaDiStudio());
        mazzoEventi.add(new PaccoDaGiu());

        //Aggiungo tutti gli eventi MALUS
        mazzoEventi.add(new ComputerGuasto());
        mazzoEventi.add(new Influenza());
        mazzoEventi.add(new Insonnia());
        mazzoEventi.add(new Multa());
        mazzoEventi.add(new Sciopero());


        return mazzoEventi;
    }
}
