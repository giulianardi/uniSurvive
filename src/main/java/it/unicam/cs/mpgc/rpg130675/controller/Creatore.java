package it.unicam.cs.mpgc.rpg130675.controller;

import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;
import it.unicam.cs.mpgc.rpg130675.model.esame.EsameScritto;

import java.util.ArrayList;
import java.util.List;

public class ListaEsame {

    public void creaListaEsame(){
        List<Esame> esamiDaSostenere = new ArrayList<>();

        Esame esameMatematica = new EsameScritto("Matematica", 18, 6);
        Esame esameProgrammazione = new EsameScritto("Programmazione", 18, 12);
        Esame esameInglese = new EsameScritto("Inglese", 18, 6);
        Esame esameFondamenti = new EsameScritto("Fondamenti dell'informatica", 18, 6);
        Esame esameArchitettura = new EsameScritto("Architettura degli elaboratori", 18, 6);
        Esame esameDiritto = new EsameScritto("Diritto dei prodotti digitali", 18, 6);
        Esame esameMarketing = new EsameScritto("Comunicazione e marketing", 18, 12);
        Esame esameModellazione = new EsameScritto("Modellazione della conoscenza", 18, 12);

        esamiDaSostenere.add(esameMatematica);
        esamiDaSostenere.add(esameProgrammazione);
        esamiDaSostenere.add(esameInglese);
        esamiDaSostenere.add(esameFondamenti);
        esamiDaSostenere.add(esameArchitettura);
        esamiDaSostenere.add(esameDiritto);
        esamiDaSostenere.add(esameMarketing);
        esamiDaSostenere.add(esameModellazione);
    }
}
