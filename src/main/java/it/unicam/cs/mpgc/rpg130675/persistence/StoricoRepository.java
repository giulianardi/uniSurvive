package it.unicam.cs.mpgc.rpg130675.persistence;

import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;

public interface StoricoRepository {

    void salvaEsameSuperato(Esame nomeEsame);

    LibrettoUniversitario caricaStorico();

    void azzeraStorico();
}