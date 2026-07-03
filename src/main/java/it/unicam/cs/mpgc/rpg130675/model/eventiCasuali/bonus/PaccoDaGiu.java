package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class PaccoDaGiu extends EventoCasualePositivo {

    private static final int BONUS_ENERGIA_MINIMO = 20;
    private static final int BONUS_ENERGIA_MASSIMO = 40;

    private static final int BONUS_STRESS_MINIMO = 20;
    private static final int BONUS_STRESS_MASSIMO = 35;

    @Override
    public String getDescrizione() {
        return "Ricevi una fornitura enorme di cibo da casa. " +
                "Mangi bene, non spendi soldi e il morale sale alle stelle.";
    }

    @Override
    public void innesca(Studente studente) {
        perditaStress(BONUS_STRESS_MINIMO, BONUS_STRESS_MASSIMO, studente);
        aumentoEnergia(BONUS_ENERGIA_MINIMO, BONUS_ENERGIA_MASSIMO, studente);
    }
}
