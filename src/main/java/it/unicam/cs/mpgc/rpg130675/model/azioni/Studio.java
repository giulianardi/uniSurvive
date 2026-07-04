package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Rappresenta l'attività di "Studio" all'interno del simulatore.
 * <p>
 * Questa è l'azione fondamentale per progredire nel gioco e superare la sfida finale (l'Esame).
 * Mettersi sui libri aumenta la preparazione dello studente (conoscenza), ma richiede
 * un inevitabile sacrificio in termini di stanchezza fisica (energia) e pressione psicologica (stress).
 * </p>
 */
public class Studio implements Attivita {

    private static final int COSTO_ENERGIA = -20;
    private static final int AUMENTO_STRESS = 15;
    private static final int AUMENTO_CONOSCENZA = 10;

    @Override
    public String getNome() {
        return "Studiare";
    }

    @Override
    public String getDescrizione() {
        return "Sui libri a preparare il prossimo esame. Costa energia e genera stress, ma la conoscenza aumenta.";
    }

    /**
     * Controlla se lo studente è fisicamente pronto per una sessione di studio.
     * Per studiare non servono soldi, ma è strettamente necessario avere abbastanza energie fisiche.
     *
     * @param studente Lo studente che vuole mettersi sui libri.
     * @return {@code true} se ha l'energia necessaria, {@code false} se è troppo stanco e deve prima riposare.
     */
    @Override
    public boolean isEseguibile(Studente studente) {
        return studente.getEnergia() >= Math.abs(COSTO_ENERGIA);
    }

    /**
     * Applica le conseguenze della sessione di studio alle statistiche dello studente.
     * Consuma energia, fa salire lo stress da prestazione, ma aumenta il punteggio di conoscenza vitale per l'esame.
     *
     * @param studente Lo studente che ha appena finito di studiare.
     * @throws IllegalStateException se il gioco tenta di far studiare lo studente quando
     * non ha l'energia minima richiesta per farlo.
     */
    @Override
    public void esegui(Studente studente) {
        if (isEseguibile(studente)) {
            studente.editEnergia(COSTO_ENERGIA);
            studente.editStress(AUMENTO_STRESS);
            studente.editConoscenza(AUMENTO_CONOSCENZA);
        } else {
            throw new IllegalStateException("L'energia non è sufficiente per studiare!");
        }
    }
    }
