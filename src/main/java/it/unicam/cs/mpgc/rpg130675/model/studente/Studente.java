package it.unicam.cs.mpgc.rpg130675.model.studente;

/**
 * Definisce il contratto per l'entità principale del dominio di simulazione.
 * <p>
 * L'interfaccia incapsula lo stato centrale del sistema.
 * Agisce come un contenitore di dati reattivo, esponendo metodi per l'ispezione
 * delle metriche correnti e fornendo punti di accesso controllati per la loro
 * mutazione algebrica da parte delle logiche esterne (Attività, Eventi Casuali ed Esami).
 * </p>
 */
public interface Studente {
    String getNome();

    Facolta getFacolta();

    int getEnergia();

    int getConoscenza();

    int getStress();

    int getDenaro();

    int getCFU();

    /**
     * Applica una variazione algebrica (positiva o negativa) al parametro dell'Energia.
     *
     * @param quantita Il valore delta da sommare o sottrarre al totale corrente.
     */
    void editEnergia(int quantita);

    /**
     * Applica una variazione algebrica (positiva o negativa) al parametro della Conoscenza.
     *
     * @param quantita Il valore delta da sommare o sottrarre al totale corrente.
     */
    void editConoscenza(int quantita);

    /**
     * Applica una variazione algebrica (positiva o negativa) al parametro dello Stress.
     *
     * @param quantita Il valore delta da sommare o sottrarre al totale corrente.
     */
    void editStress(int quantita);

    /**
     * Applica una variazione algebrica (positiva o negativa) al parametro del Denaro.
     *
     * @param quantita Il valore delta da sommare o sottrarre al totale corrente.
     */
    void editDenaro(int quantita);

    /**
     * Incrementa il contatore della progressione accademica (CFU) a seguito
     * del superamento di una valutazione.
     *
     * @param quantita Il numero di crediti formativi da aggiungere al totale.
     */
    void addCFU(int quantita);

    /**
     * Valuta la condizione di sconfitta (Game Over) dell'entità.
     * <p>
     * Il metodo ispeziona lo stato interno per determinare se il livello di Stress
     * ha raggiunto o superato la soglia critica di tolleranza predefinita dal sistema.
     * </p>
     *
     * @return {@code true} se lo studente si trova in uno stato di esaurimento
     * psicofisico irrecuperabile, {@code false} altrimenti.
     */
    boolean isInBurnout();
}

