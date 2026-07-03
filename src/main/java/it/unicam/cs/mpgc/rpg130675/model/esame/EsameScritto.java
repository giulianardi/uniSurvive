package it.unicam.cs.mpgc.rpg130675.model.esame;

/**
 * Rappresenta l'implementazione concreta della classe astratta EsameBase che modella
 * un esame di tipologia scritta.
 * <p>
 * Questa classe specializza la logica di calcolo del malus psicologico.
 * A differenza di una prova orale, l'impatto dello stress in un esame scritto
 * viene modellato con un'incidenza minore, garantendo coerenza con il dominio
 * simulato senza alterare l'infrastruttura di valutazione della superclasse.
 * </p>
 */
public class EsameScritto extends EsameBase {

    public EsameScritto(String materia, int difficolta, int cfu) {
        super(materia, difficolta, cfu);
    }

    /**
     * Calcola il fattore di penalizzazione da sottrarre al punteggio d'esame
     * in base al livello di stress corrente.
     * <p>
     * In questa specifica implementazione (prova scritta), il malus è definito come
     * un decimo (10%) del valore di stress attuale. Tale coefficiente riflette
     * un'incidenza moderata dell'ansia sulle performance di valutazione, tipica
     * di un test non frontale rispetto all'esposizione di un colloquio orale.
     * </p>
     *
     * @param stressAttuale Il livello di stress registrato dallo studente al momento
     *                      dell'invocazione della prova.
     * @return Il valore intero della penalità da applicare al calcolo del punteggio finale.
     */
    @Override
    protected int calcolaMalusStress(int stressAttuale) {
        return stressAttuale / 10;
    }
}
