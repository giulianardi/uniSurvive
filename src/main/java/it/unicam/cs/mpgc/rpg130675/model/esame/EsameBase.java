package it.unicam.cs.mpgc.rpg130675.model.esame;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;
import java.util.Random;

/**
 * Rappresenta la struttura di base (lo "scheletro") per qualsiasi esame nel gioco.
 * <p>
 * Questa classe astratta gestisce in autonomia tutta la logica universale dell'esame
 * (lancio del dado, promozioni, bocciature),
 * ma delega alle classi figlie (es. EsameScritto o EsameOrale) il calcolo specifico
 * di quanto l'ansia inciderà sul voto.
 * </p>
 */
public abstract class EsameBase implements Esame {

    private String materia;
    private int difficolta;
    private int cfu;
    private Random dado;

    /**
     * Prepara l'esame impostando le sue regole fondamentali.
     * * @param materia Il nome dell'esame da mostrare al giocatore.
     * @param difficolta Il punteggio soglia da raggiungere o superare per essere promossi.
     * @param cfu I crediti che il giocatore vincerà superando questa sfida.
     */
    public EsameBase(String materia, int difficolta, int cfu) {
        this.materia = materia;
        this.difficolta = difficolta;
        this.cfu = cfu;
        this.dado = new Random();
    }

    @Override
    public String getNomeMateria() {
        return this.materia;
    }

    @Override
    public int getDifficoltaBase() {
        return this.difficolta;
    }

    @Override
    public int getCfuForniti() {
        return this.cfu;
    }

    /**
     * Valuta se lo studente è promosso o bocciato.
     * <p>
     * La formula del successo è: (Conoscenza + Lancio del Dado) - Malus dello Stress.
     * Se il totale è maggiore o uguale alla difficoltà, lo studente è promosso.
     * </p>
     * * @param studente Il malcapitato studente che tenta l'esame.
     * @return {@code true} se promosso, {@code false} se bocciato.
     */
    @Override
    public boolean sostieni(Studente studente) {

        int lancioDado = dado.nextInt(20) + 1;

        int malusStress = calcolaMalusStress(studente.getStress());

        int punteggioFinale = studente.getConoscenza() + lancioDado - malusStress;

        if (punteggioFinale >= this.difficolta) {
            applicaConseguenzePromozione(studente);
            return true;
        } else {
            applicaConseguenzeBocciatura(studente);
            return false;
        }
    }

    /**
     * Ogni tipo di esame (scritto, orale) dovrà implementare questo metodo
     * per decidere quanto lo stress attuale peserà negativamente sul punteggio finale.
     * * @param stressAttuale Il livello di stress dello studente al momento dell'esame.
     * @return I punti da sottrarre al voto finale.
     */
    protected abstract int calcolaMalusStress(int stressAttuale);

    /**
     * Applica le conseguenze della promozione.
     * Il giocatore riceve i CFU per avvicinarsi alla vittoria. La tensione svanisce
     * (lo stress si azzera) e la conoscenza studiata viene dimenticata in vista del
     * prossimo esame (si azzera).
     * * @param studente Lo studente neopromosso.
     */
    private void applicaConseguenzePromozione(Studente studente) {
        studente.addCFU(this.cfu);
        studente.editConoscenza(-studente.getConoscenza());
        studente.editStress(-studente.getStress());
    }

    /**
     * Applica le conseguenze di una bocciatura.
     * Il giocatore non prende CFU, subisce un colpo psicologico tremendo (lo stress
     * sale alle stelle) e, per la delusione, scorda metà delle cose che aveva studiato.
     * * @param studente Lo studente sfortunato.
     */
    private void applicaConseguenzeBocciatura(Studente studente) {
        studente.editStress(50);
        int conoscenzaPersa = studente.getConoscenza() / 2;
        studente.editConoscenza(-conoscenzaPersa);
    }
}
