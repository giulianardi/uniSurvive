package it.unicam.cs.mpgc.rpg130675.model.attività;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

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
