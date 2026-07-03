package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class Sciopero extends EventoCasualeNegativo {

    private static final int MALUS_ENERGIA_MINIMO = 10;
    private static final int MALUS_ENERGIA_MASSIMO = 20;

    private static final int MALUS_STRESS_MINIMO = 10;
    private static final int MALUS_STRESS_MASSIMO = 25;

    @Override
    public String getDescrizione() {
        return "Autobus cancellati e treni in ritardo. Arrivare in università oggi è stata una vera odissea!";
    }

    @Override
    public void innesca(Studente studente) {
        perditaEnergia(MALUS_ENERGIA_MINIMO, MALUS_ENERGIA_MASSIMO, studente);
        aumentoStress(MALUS_STRESS_MINIMO, MALUS_STRESS_MASSIMO, studente);
    }
}
