package it.unicam.cs.mpgc.rpg130675.eccezioni;

/**
 * Eccezione lanciata quando il giocatore non possiede le risorse necessarie
 * (es. energia, denaro) per compiere una determinata azione.
 */
public class EccezioneInsufficienzaRisorse extends Exception{

    private static final String PREFISSO = "[RISORSE_INSUFFICIENTI]";

    public EccezioneInsufficienzaRisorse(String messaggio){

        super(PREFISSO + " " + messaggio);
    }
}
