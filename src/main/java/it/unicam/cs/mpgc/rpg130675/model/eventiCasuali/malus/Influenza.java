package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class Influenza extends EventoCasualeNegativo {

    private static final int MALUS_ENERGIA_MINIMO = 25;
    private static final int MALUS_ENERGIA_MASSIMO = 50;

    @Override
    public String getDescrizione() {
        return "Ti sei preso un brutto virus influenzale. Febbre e tosse ti mettono fisicamente KO.";
    }

    @Override
    public void innesca(Studente studente) {
        perditaEnergia(MALUS_ENERGIA_MINIMO, MALUS_ENERGIA_MASSIMO, studente);
    }
}
