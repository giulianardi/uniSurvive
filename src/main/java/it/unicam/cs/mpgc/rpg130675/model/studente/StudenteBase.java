package it.unicam.cs.mpgc.rpg130675.model.studente;

public class StudenteBase implements Studente {

    private String nome;
    private String facolta;
    private int conoscenza;
    private int energia;
    private int stress;
    private int denaro;
    private int cfu;


    public StudenteBase(String nome, String facolta) {
        this.nome = nome;
        this.facolta = facolta;
        this.conoscenza = 0;
        this.energia = 100;
        this.stress = 0;
        this.denaro = 100;
        this.cfu = 0;
    }

    // --- GETTERS ---

    @Override
    public String getNome() { return this.nome; }

    @Override
    public String getFacolta() { return this.facolta; }

    @Override
    public int getConoscenza() { return this.conoscenza; }

    @Override
    public int getEnergia() { return this.energia; }

    @Override
    public int getStress() { return this.stress; }

    @Override
    public int getDenaro() { return this.denaro; }

    @Override
    public int getCFU() { return this.cfu; }

    // --- METODI DI MODIFICA ---

    @Override
    public void editConoscenza(int quantita) {
        // La conoscenza non può essere negativa
        this.conoscenza = Math.max(0, this.conoscenza + quantita);
    }

    @Override
    public void editEnergia(int quantita) {
        // L'energia deve rimanere tra 0 e 100
        int nuovaEnergia = this.energia + quantita;
        this.energia = Math.max(0, Math.min(100, nuovaEnergia));
    }

    @Override
    public void editStress(int quantita) {
        // Lo stress deve rimanere tra 0 e 100
        int nuovoStress = this.stress + quantita;
        this.stress = Math.max(0, Math.min(100, nuovoStress));
    }

    @Override
    public void editDenaro(int quantita) {
        this.denaro += quantita;
    }

    @Override
    public void addCFU(int quantita) {
        if (quantita > 0) {
            this.cfu += quantita;
        }
    }

    @Override
    public boolean isInBurnout() {
        // Definiamo il burnout come raggiungere il 100% di stress
        return this.stress >= 100;
    }
}
