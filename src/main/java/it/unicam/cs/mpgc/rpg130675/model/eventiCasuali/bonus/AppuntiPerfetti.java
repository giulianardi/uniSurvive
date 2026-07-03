package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class AppuntiPerfetti extends EventoCasualePositivo {

    private static final int BONUS_CONOSCENZA_MINIMO = 15;
    private static final int BONUS_CONOSCENZA_MASSIMO = 25;

    @Override
    public String getDescrizione() {
        return "Un collega degli anni precedenti ti regala i suoi riassunti perfetti " +
                "con le domande tipiche del prof.";
    }

    @Override
    public void innesca(Studente studente) {
        aumentoConoscenza(BONUS_CONOSCENZA_MINIMO, BONUS_CONOSCENZA_MASSIMO, studente);
    }
}
