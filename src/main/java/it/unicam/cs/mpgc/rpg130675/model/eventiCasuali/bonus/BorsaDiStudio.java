package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class BorsaDiStudio extends EventoCasualePositivo {

    private static final int BONUS_DENARO_MINIMO = 40;
    private static final int BONUS_DENARO_MASSIMO = 80;

    private static final int BONUS_STRESS_MINIMO = 15;
    private static final int BONUS_STRESS_MASSIMO = 25;

    @Override
    public String getTitolo() {
        return "Borsa di Studio!";
    }

    @Override
    public String getDescrizione() {
        return "Miracolo della segreteria studenti! Hai ricevuto un rimborso " +
                "inaspettato sul tuo conto bancario.";
    }

    @Override
    public void innesca(Studente studente) {
        aumentoDenaro(BONUS_DENARO_MINIMO,BONUS_DENARO_MASSIMO,studente);
        perditaStress(BONUS_STRESS_MINIMO,BONUS_STRESS_MASSIMO,studente);
    }
}
