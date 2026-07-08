package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.bonus;

import it.unicam.cs.mpgc.rpg130675.model.eventiCasuali.EventoCasualeBase;
import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;


/**
 * Livello intermedio nella gerarchia degli eventi randomici, specializzato
 * nella modellazione di occorrenze di natura benefica (bonus).
 * <p>
 * Estendendo la superclasse EventoCasualeBase, eredita l'infrastruttura
 * di generazione aleatoria e la incapsula in una suite di metodi modificatori
 * protetti. L'obiettivo architetturale è fornire alle sottoclassi concrete
 * (i singoli eventi positivi) un'interfaccia semplificata per l'alterazione
 * vantaggiosa dello stato dell'entità Studente, minimizzando la
 * duplicazione della logica di mutazione.
 * </p>
 */
public abstract class EventoCasualePositivo extends EventoCasualeBase {

    /**
     * Applica un incremento alla statistica "Energia" dello studente,
     * sfruttando il generatore aleatorio ereditato dalla superclasse.
     *
     * @param min      Il limite inferiore (incluso) del bonus di energia.
     * @param max      Il limite superiore (incluso) del bonus di energia.
     * @param studente L'entità su cui applicare il modificatore di stato.
     */
    protected void aumentoEnergia(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editEnergia(aumento);
    }

    /**
     * Applica un incremento alla statistica "Denaro" dello studente.
     *
     * @param min      Il limite inferiore (incluso) del bonus economico.
     * @param max      Il limite superiore (incluso) del bonus economico.
     * @param studente L'entità su cui applicare il modificatore di stato.
     */
    protected void aumentoDenaro(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editDenaro(aumento);
    }

    /**
     * Applica una riduzione alla statistica "Stress" dello studente.
     *
     * @param min      Il limite inferiore (incluso) della riduzione di stress.
     * @param max      Il limite superiore (incluso) della riduzione di stress.
     * @param studente L'entità da cui sottrarre il valore generato.
     */
    protected void perditaStress(int min, int max, Studente studente){
        int perdita = generaValore(min, max);
        studente.editStress(-perdita);
    }

    /**
     * Applica un incremento alla statistica "Conoscenza" dello studente.
     *
     * @param min      Il limite inferiore (incluso) del bonus di conoscenza.
     * @param max      Il limite superiore (incluso) del bonus di conoscenza.
     * @param studente L'entità bersaglio su cui applicare il modificatore di stato.
     */
    protected void aumentoConoscenza(int min, int max, Studente studente){
        int aumento = generaValore(min, max);
        studente.editConoscenza(aumento);
    }
}
