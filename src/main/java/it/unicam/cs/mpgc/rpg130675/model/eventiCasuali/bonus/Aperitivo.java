package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public class Aperitivo extends EventoCasualePositivo {

    private static final int BONUS_ENERGIA_MINIMO = 10;
    private static final int BONUS_ENERGIA_MASSIMO = 20;

    private static final int BONUS_STRESS_MINIMO = 15;
    private static final int BONUS_STRESS_MASSIMO = 30;

    @Override
    public String getDescrizione() {
        return "Incontri dei vecchi amici che insistono per offrirti l'aperitivo. " +
                "Mangi al buffet e ti rilassi senza tirare fuori un centesimo!";
    }

    @Override
    public void innesca(Studente studente) {
        aumentoEnergia(BONUS_ENERGIA_MINIMO, BONUS_ENERGIA_MASSIMO, studente);
        perditaStress(BONUS_STRESS_MINIMO, BONUS_STRESS_MASSIMO, studente);
    }
}
