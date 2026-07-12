package it.unicam.cs.mpgc.rpg130675.model.esame;

/**
 * Rappresenta l'implementazione concreta della classe astratta EsameBase che modella
 * un esame di tipologia orale.
 *
 * Questa classe definisce la specializzazione del calcolo della penalità derivante
 * dallo stato psicofisico (stress) dello studente. Applicando il pattern Template Method,
 * fornisce l'implementazione specifica della logica di penalizzazione richiesta
 * dall'architettura della superclasse, simulando un impatto maggiore dello stress
 * a causa dell'interazione diretta richiesta da una prova orale.
 */
public class EsameOrale extends EsameBase {

    public EsameOrale(String materia, int difficolta, int cfu) {
        super(materia, difficolta, cfu);
    }

    /**
     * Il malus è definito come un quinto (20%) del valore di stress attuale.
     */
    @Override
    protected int calcolaMalusStress(int stressAttuale) {
        return stressAttuale / 5;
    }
}
