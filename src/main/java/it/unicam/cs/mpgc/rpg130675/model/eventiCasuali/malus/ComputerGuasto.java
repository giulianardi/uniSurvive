package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class ComputerGuasto extends EventoCasualeNegativo {

    private static final int MALUS_DENARO_MINIMO = 30;
    private static final int MALUS_DENARO_MASSIMO = 60;

    private static final int MALUS_STRESS_MINIMO = 10;
    private static final int MALUS_STRESS_MASSIMO = 30;

    @Override
    public String getDescrizione() {
        return "Schermata blu della morte! Il tuo portatile ti ha abbandonato e la riparazione " +
                "ti costa un occhio della testa.";
    }

    @Override
    public void innesca(Studente studente) {
        perditaDenaro(MALUS_DENARO_MINIMO, MALUS_DENARO_MASSIMO, studente);
        aumentoStress(MALUS_STRESS_MINIMO, MALUS_STRESS_MASSIMO, studente);
    }
}
