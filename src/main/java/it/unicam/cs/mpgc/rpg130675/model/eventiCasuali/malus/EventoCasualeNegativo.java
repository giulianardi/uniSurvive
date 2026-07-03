package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;

import java.util.Random;

public abstract class EventoCasualeNegativo implements EventoCasuale {

    private Random generatoreCasuale;

    public EventoCasualeNegativo() {
        this.generatoreCasuale = new Random();
    }

    private int generaValore(int min, int max) {
        return generatoreCasuale.nextInt((max - min) + 1) + min;
    }

    protected void perditaEnergia(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editEnergia(-perdita);
    }

    protected void perditaDenaro(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editDenaro(-perdita);
    }

    protected void aumentoStress(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editStress(aumento);
    }


}

