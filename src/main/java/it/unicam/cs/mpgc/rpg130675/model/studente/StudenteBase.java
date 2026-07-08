package it.unicam.cs.mpgc.rpg130675.model.studente;

/**
 * Implementazione concreta e standard dell'interfaccia Studente.
 * <p>
 * Questa classe agisce come contenitore reattivo per lo stato del giocatore.
 * I metodi di mutazione implementano rigidi meccanismi di
 * "clamping" (vincolo dei valori entro range predefiniti), assicurando che le
 * statistiche non assumano mai valori semanticamente non validi per il dominio di gioco.
 * </p>
 */
public class StudenteBase implements Studente {

    private String nome;
    private Facolta facolta;
    private int conoscenza;
    private int energia;
    private int stress;
    private int denaro;
    private int cfu;


    /**
     * Inizializza una nuova entità studente, configurando i dati anagrafici
     * e impostando lo stato di base iniziale del simulatore.
     * <p>
     * Valori di default applicati all'istanza:
     * - Energia: 100 (massimo)
     * - Denaro: 100 (budget di partenza)
     * - Conoscenza: 0
     * - Stress: 0
     * - CFU: 0
     * </p>
     *
     * @param nome    L'identificativo anagrafico dell'utente.
     * @param facolta La disciplina di studi scelta.
     */
    public StudenteBase(String nome, Facolta facolta) {
        this.nome = nome;
        this.facolta = facolta;
        this.conoscenza = 0;
        this.energia = 100;
        this.stress = 0;
        this.denaro = 0;
        this.cfu = 0;
    }

    @Override
    public String getNome() { return this.nome; }

    @Override
    public Facolta getFacolta() { return this.facolta; }

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

    /**
     * Applica un lower-bound algebrico.
     * Il livello di conoscenza non può regredire al di sotto dello zero (0).
     */
    @Override
    public void editConoscenza(int quantita) {
        this.conoscenza = Math.max(0, this.conoscenza + quantita);
    }

    /**
     * Applica un'operazione di clamping [0, 100].
     * Garantisce che l'energia non risulti mai negativa o superiore al limite fisiologico.
     */
    @Override
    public void editEnergia(int quantita) {
        int nuovaEnergia = this.energia + quantita;
        this.energia = Math.max(0, Math.min(100, nuovaEnergia));
    }

    /**
     * Applica un'operazione di clamping [0, 100].
     * Mantiene il parametro di affaticamento psicologico entro la scala percentuale di tolleranza.
     */
    @Override
    public void editStress(int quantita) {
        int nuovoStress = this.stress + quantita;
        this.stress = Math.max(0, Math.min(100, nuovoStress));
    }

    /**
     * Applica una variazione algebrica (positiva o negativa) al patrimonio dello studente.
     * <p>
     * In questa implementazione base, il portafoglio consente di andare in negativo (debiti).
     * </p>
     *
     * @param quantita Il valore delta da sommare o sottrarre al totale corrente.
     */
    @Override
    public void editDenaro(int quantita) {
        this.denaro += quantita;
    }

    /**
     * Incrementa il contatore della progressione accademica.
     *
     * @param quantita Il numero di crediti formativi da aggiungere al totale.
     * @throws IllegalArgumentException se la quantità passata non è strettamente positiva.
     */
    @Override
    public void addCFU(int quantita) {
        if (quantita > 0) {
            this.cfu += quantita;
        }else{
            throw new IllegalArgumentException("I valore dei Cfu passati non può essere negativo.");
        }
    }

    /**
     * In questa specifica simulazione, la condizione di instabilità fatale (Game Over)
     * si verifica quando il parametro stress raggiunge o supera quota 100.
     * </p>
     */
    @Override
    public boolean isInBurnout() {
        return this.stress >= 100;
    }
}
