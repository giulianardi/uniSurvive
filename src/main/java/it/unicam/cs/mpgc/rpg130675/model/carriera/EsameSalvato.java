package it.unicam.cs.mpgc.rpg130675.model.carriera;

/**
 * Rappresenta un esame superato, strutturato per facilitare la persistenza dei dati.
 * Questa classe agisce come un contenitore di informazioni (DTO) che memorizza
 * i dettagli essenziali di un esame salvato nello storico dello studente.
 */
public class EsameSalvato {
    private String nomeMateria;
    private int cfuGuadagnati;

    public EsameSalvato(String nomeMateria, int cfuGuadagnati) {
        this.nomeMateria = nomeMateria;
        this.cfuGuadagnati = cfuGuadagnati;
    }

    public String getNomeMateria() { return nomeMateria; }
    public int getCfuGuadagnati() { return cfuGuadagnati; }
}