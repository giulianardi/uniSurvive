package it.unicam.cs.mpgc.rpg130675.model.esame;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public interface Esame {
    String getNomeMateria();

    int getDifficoltaBase();

    int getCfuForniti();

    boolean sostieni(Studente studente);

}
