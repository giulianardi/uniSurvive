package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.esame.EsameScritto;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.*;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus.*;
import it.unicam.cs.mpgc.rpg130675.model.studente.Facolta;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;

import java.util.ArrayList;
import java.util.List;

public class Creatore {

    public StudenteBase creaStudente(String nomeScelto, Facolta facoltaScelta) {
        return new StudenteBase(nomeScelto, facoltaScelta);
    }

    public List<Esame> creaListaEsame(Facolta facoltaScelta) {
        List<Esame> esamiDaSostenere = new ArrayList<>();

        if(facoltaScelta == Facolta.INFORMATICA_COMUNICAZIONE) {
            esamiDaSostenere.add(new EsameScritto("Matematica", 50, 12));
            esamiDaSostenere.add(new EsameScritto("Programmazione", 60, 12));
            esamiDaSostenere.add(new EsameScritto("Inglese", 20, 6));
            esamiDaSostenere.add(new EsameScritto("Fondamenti dell'informatica", 40, 6));
            esamiDaSostenere.add(new EsameScritto("Architettura degli elaboratori", 30, 6));
            esamiDaSostenere.add(new EsameScritto("Diritto dei prodotti digitali", 20, 6));
            esamiDaSostenere.add(new EsameScritto("Comunicazione e marketing", 60, 12));
            esamiDaSostenere.add(new EsameScritto("Modellazione della conoscenza", 50, 12));
        }else if(facoltaScelta == Facolta.CHIMICA){
            esamiDaSostenere.add(new EsameScritto("Matematica 1", 18, 12));
            esamiDaSostenere.add(new EsameScritto("Chimica generale", 18, 14));
            esamiDaSostenere.add(new EsameScritto("Inglese", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Matematica 2", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Fisica 1", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Chimica analitica 1", 18, 14));
            esamiDaSostenere.add(new EsameScritto("Chimica Fisica 1", 18, 12));
            esamiDaSostenere.add(new EsameScritto("Chimica Organiza 1", 18, 12));
        }
        return esamiDaSostenere;
    }
    public List<EventoCasuale> creaListaEventi(){
        List<EventoCasuale> mazzoEventi = new ArrayList<>();

        // 2. Aggiungo tutti gli eventi BONUS
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
