package it.unicam.cs.mpgc.rpg130675.model.esame;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

import java.util.Random;

public abstract class EsameBase implements Esame {

    private String materia;
    private int difficolta;
    private int cfu;
    private Random dado;

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

    protected abstract int calcolaMalusStress(int stressAttuale);

    private void applicaConseguenzePromozione(Studente studente) {
        studente.addCFU(this.cfu);
        studente.editConoscenza(-studente.getConoscenza());
        studente.editStress(-studente.getStress());
    }

    private void applicaConseguenzeBocciatura(Studente studente) {
        studente.editStress(50);
        int conoscenzaPersa = studente.getConoscenza() / 2;
        studente.editConoscenza(-conoscenzaPersa);
    }
}
