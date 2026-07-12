package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.Aperitivo;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.AppuntiPerfetti;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.BorsaDiStudio;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.PaccoDaGiu;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus.*;
import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Gestisce la creazione e l'inizializzazione delle entità principali del gioco,
 * come lo studente, gli eventi casuali e gli esami.
 */
public class Creatore {

    private final Map<Facolta, GeneratorePianoDiStudi> registroPianiDiStudi;

    /**
     * Crea un nuovo studente assegnandogli le statistiche base.
     *
     * @param nomeScelto    il nome scelto per lo studente.
     * @param facoltaScelta la facoltà di appartenenza che definisce il percorso di studi.
     * @return un oggetto {@link StudenteBase} inizializzato.
     */
    public Studente creaStudente(String nomeScelto, Facolta facoltaScelta) {
        return new StudenteBase(nomeScelto, facoltaScelta);
    }

    public Creatore() {
        this.registroPianiDiStudi = new HashMap<>();

        this.registroPianiDiStudi.put(Facolta.INFORMATICA_COMUNICAZIONE, new PianoInformaticaComunicazione());
        this.registroPianiDiStudi.put(Facolta.CHIMICA, new PianoChimica());
    }

    public List<Esame> creaListaEsame(Facolta facoltaScelta) {
        GeneratorePianoDiStudi generatore = registroPianiDiStudi.get(facoltaScelta);

        if (generatore == null) {
            throw new IllegalArgumentException("Nessun piano di studi configurato per la facoltà: " + facoltaScelta);
        }

        return generatore.generaEsami();
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
