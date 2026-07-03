package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class Insonnia extends EventoCasualeNegativo {

    private static final int MALUS_ENERGIA_MINIMO = 20;
    private static final int MALUS_ENERGIA_MASSIMO = 40;

    private static final int MALUS_STRESS_MINIMO = 10;
    private static final int MALUS_STRESS_MASSIMO = 20;

    @Override
    public String getDescrizione() {
        return "L'ansia per la sessione non ti ha fatto chiudere occhio per tutta la notte. " +
                "Ti senti uno zombie.";
    }

    @Override
    public void innesca(Studente studente) {
        perditaEnergia(MALUS_ENERGIA_MINIMO, MALUS_ENERGIA_MASSIMO, studente);
        aumentoStress(MALUS_STRESS_MINIMO, MALUS_STRESS_MASSIMO, studente);
    }
}
