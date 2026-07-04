package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.esame.EsameScritto;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus.*;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus.*;
import it.unicam.cs.mpgc.rpg130675.model.studente.StudenteBase;

import java.util.ArrayList;
import java.util.List;

public class Creatore {

    public StudenteBase creaStudente(String nomeScelto, String facoltaScelta) {
        return new StudenteBase(nomeScelto, facoltaScelta);
    }

    public List<Esame> creaListaEsame() {
        List<Esame> esamiDaSostenere = new ArrayList<>();

            esamiDaSostenere.add(new EsameScritto("Matematica", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Programmazione", 18, 12));
            esamiDaSostenere.add(new EsameScritto("Inglese", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Fondamenti dell'informatica", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Architettura degli elaboratori", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Diritto dei prodotti digitali", 18, 6));
            esamiDaSostenere.add(new EsameScritto("Comunicazione e marketing", 18, 12));
            esamiDaSostenere.add(new EsameScritto("Modellazione della conoscenza", 18, 12));

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
