package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class Multa extends EventoCasualeNegativo {

    private static final int MALUS_DENARO_MINIMO = 20;
    private static final int MALUS_DENARO_MASSIMO = 40;

    private static final int MALUS_STRESS_MINIMO = 10;
    private static final int MALUS_STRESS_MASSIMO = 20;

    @Override
    public String getDescrizione() {
        return "Eri in ritardo e hai dimenticato di fare il biglietto. " +
                "Il controllore non ha voluto sentire ragioni.";
    }

    @Override
    public void innesca(Studente studente) {
        perditaDenaro(MALUS_DENARO_MINIMO, MALUS_DENARO_MASSIMO, studente);
        aumentoStress(MALUS_STRESS_MINIMO, MALUS_STRESS_MASSIMO, studente);
    }
}
