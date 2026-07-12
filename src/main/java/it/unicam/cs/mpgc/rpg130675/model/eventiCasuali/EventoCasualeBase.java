package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali;

import java.util.Random;

public abstract class EventoCasualeBase implements EventoCasuale{

    private Random generatoreCasuale;

    public EventoCasualeBase() {
        this.generatoreCasuale = new Random();
    }


    protected int generaValore(int min, int max) {
        return generatoreCasuale.nextInt((max - min) + 1) + min;
    }
}
