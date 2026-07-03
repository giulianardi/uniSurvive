package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;
import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasuale;

import java.util.Random;

public abstract class EventoCasualePositivo implements EventoCasuale {

    private Random generatoreCasuale;

    public EventoCasualePositivo() {
        this.generatoreCasuale = new Random();
    }

    private int generaValore(int min, int max) {
        return generatoreCasuale.nextInt((max - min) + 1) + min;
    }

    protected void aumentoEnergia(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editEnergia(aumento);
    }

    protected void aumentoDenaro(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editDenaro(aumento);
    }

    protected void perditaStress(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editStress(-perdita);
    }

    protected void aumentoConoscenza(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editConoscenza(aumento);
    }
}
