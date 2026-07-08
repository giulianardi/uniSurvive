package it.unicam.cs.mpgc.rpg130675.gui;

import it.unicam.cs.mpgc.rpg130675.model.azioni.TipoAzione;

/**
 * Interfaccia per raccogliere gli input del giocatore durante il turno.
 */
public interface MainGameListener {
    void onActionSelected(TipoAzione actionType);
}
