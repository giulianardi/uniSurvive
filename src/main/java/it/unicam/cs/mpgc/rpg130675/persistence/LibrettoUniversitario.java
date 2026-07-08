package it.unicam.cs.mpgc.rpg130675.persistence;

import java.util.ArrayList;
import java.util.List;

public class LibrettoUniversitario {

    private List<EsameSalvato> esamiSuperati;

    public LibrettoUniversitario() {
        this.esamiSuperati = new ArrayList<>();
    }

    public List<EsameSalvato> getEsamiSuperati() {
        return esamiSuperati;
    }

    public void aggiungiEsame(EsameSalvato esame) {
        this.esamiSuperati.add(esame);
    }
}