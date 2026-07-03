package it.unicam.cs.mpgc.rpg130675.model.esame;

import it.unicam.cs.mpgc.rpg130675.model.studente.Studente;

/**
 * Rappresenta la sfida finale del gioco: l'Esame Universitario.
 * <p>
 * Questa interfaccia definisce il contratto base per qualsiasi tipo di esame.
 * </p>
 */
public interface Esame {

    String getNomeMateria();

    int getDifficoltaBase();

    int getCfuForniti();

    /**
     * Il momento della verità: lo studente tenta di superare l'esame.
     * <p>
     * Questo metodo contiene la logica principale di valutazione. Oltre a restituire
     * l'esito, ha la responsabilità di applicare le conseguenze allo studente
     * (es. azzerare lo stress e aggiungere i CFU in caso di promozione, oppure
     * far schizzare alle stelle lo stress in caso di bocciatura).
     * </p>
     *
     * * @param studente Lo studente che affronta la prova.
     * @return {@code true} se l'esame è stato superato, {@code false} se lo studente è stato bocciato.
     */
    boolean sostieni(Studente studente);

}
