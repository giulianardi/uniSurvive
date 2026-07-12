package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;


public interface EventoCasuale {

        String getTitolo();

        String getDescrizione();


        void innesca(Studente studente);

}
