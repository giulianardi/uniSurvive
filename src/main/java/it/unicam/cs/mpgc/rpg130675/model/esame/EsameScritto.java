package it.unicam.cs.mpgc.rpg130675.model.esame;

/**
 * Rappresenta l'implementazione concreta della classe astratta EsameBase che modella
 * un esame di tipologia scritta.
 *
 * Questa classe specializza la logica di calcolo del malus psicologico.
 * A differenza di una prova orale, l'impatto dello stress in un esame scritto
 * viene modellato con un'incidenza minore, garantendo coerenza con il dominio
 * simulato senza alterare l'infrastruttura di valutazione della superclasse.
 */
public class EsameScritto extends EsameBase {

    public EsameScritto(String materia, int difficolta, int cfu) {
        super(materia, difficolta, cfu);
    }

    /**
     * Il malus è definito come un decimo (10%) del valore di stress attuale.
     */
    @Override
    protected int calcolaMalusStress(int stressAttuale) {
        return stressAttuale / 10;
    }
}
