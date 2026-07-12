package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasualeBase;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;


public abstract class EventoCasualePositivo extends EventoCasualeBase {

    protected void aumentoEnergia(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editEnergia(aumento);
    }

    protected void aumentoDenaro(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editDenaro(aumento);
    }

    protected void perditaStress(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editStress(-perdita);
    }

    protected void aumentoConoscenza(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editConoscenza(aumento);
    }
}
