package it.unicam.cs.mpgc.rpg130675.model.azioni;

import it.unicam.cs.mpgc.rpg130675.eccezioni.EccezioneInsufficienzaRisorse;
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

    @Override
    public boolean isEseguibile(Studente studente) {
        return studente.getEnergia() >= Math.abs(COSTO_ENERGIA);
    }

    @Override
    public void esegui(Studente studente) throws EccezioneInsufficienzaRisorse {
        if (isEseguibile(studente)) {
            studente.editEnergia(COSTO_ENERGIA);
            studente.editStress(AUMENTO_STRESS);
            studente.editConoscenza(AUMENTO_CONOSCENZA);
        } else {
            throw new EccezioneInsufficienzaRisorse("L'energia non è sufficiente per studiare!");
        }
    }
    }
