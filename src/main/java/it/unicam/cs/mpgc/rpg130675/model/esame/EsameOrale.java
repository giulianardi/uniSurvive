package it.unicam.cs.mpgc.rpg130675.model.esame;

/**
 * Rappresenta l'implementazione concreta della classe astratta EsameBase eche modella
 * un esame di tipologia orale.
 * <p>
 * Questa classe definisce la specializzazione del calcolo della penalità derivante
 * dallo stato psicofisico (stress) dello studente. Applicando il pattern Template Method,
 * fornisce l'implementazione specifica della logica di penalizzazione richiesta
 * dall'architettura della superclasse, simulando un impatto maggiore dello stress
 * a causa dell'interazione diretta richiesta da una prova orale.
 * </p>
 */
public class EsameOrale extends EsameBase {

    public EsameOrale(String materia, int difficolta, int cfu) {
        super(materia, difficolta, cfu);
    }

    /**
     * Calcola il fattore di penalizzazione da sottrarre al punteggio d'esame
     * in base al livello di stress corrente.
     * <p>
     * In questa specifica implementazione (prova orale), il malus è definito come
     * un quinto (20%) del valore di stress attuale. Tale coefficiente riflette
     * un'incidenza severa dell'ansia sulle performance di valutazione.
     * </p>
     *
     * @param stressAttuale Il livello di stress registrato dallo studente al momento
     * dell'invocazione della prova.
     * @return Il valore intero della penalità da applicare al calcolo del punteggio finale.
     */
    @Override
    protected int calcolaMalusStress(int stressAttuale) {
        return stressAttuale / 5;
    }
}
