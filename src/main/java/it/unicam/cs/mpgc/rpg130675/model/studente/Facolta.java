package it.unicam.cs.mpgc.rpg130675.model.studente;

/**
 * Rappresenta i percorsi di studio disponibili nel simulatore.
 * Ogni facoltà incapsula il proprio nome descrittivo per la visualizzazione.
 */
public enum Facolta {
    INFORMATICA_COMUNICAZIONE("Informatica per la comunicazione digitale"),
    CHIMICA("Chimica");

    private final String nomeDescrittivo;

    Facolta(String nomeDescrittivo) {
        this.nomeDescrittivo = nomeDescrittivo;
    }

    public String getNomeDescrittivo() {
        return nomeDescrittivo;
    }

    /**
     * Sovrascriviamo il toString() affinché componenti UI come la JComboBox
     * mostrino direttamente il nome descrittivo al posto dell'identificatore dell'Enum.
     */
    @Override
    public String toString() {
        return this.nomeDescrittivo;
    }
}