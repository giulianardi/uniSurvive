package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.malus;

import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasualeBase;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

public abstract class EventoCasualeNegativo extends EventoCasualeBase{


    protected void perditaEnergia(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editEnergia(-perdita);
    }

    protected void perditaDenaro(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editDenaro(-perdita);
    }

    protected void aumentoStress(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editStress(aumento);
    }


}

