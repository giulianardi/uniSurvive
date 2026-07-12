package it.unicam.cs.mpgc.rpg130675.model.eventiCasuali;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Gestisce la logica probabilistica e l'estrazione degli eventi casuali.
 */
public class MotoreEventi {
    private final Random generatoreCasuale;
    private static final int PROBABILITA_EVENTO = 15; // 15%

    public MotoreEventi() {
        this.generatoreCasuale = new Random();
    }

    /**
     * Tenta di estrarre un evento in base alle probabilità di gioco.
     * Restituisce un Optional vuoto se l'evento non si innesca in questo turno.
     */
    public Optional<EventoCasuale> provaEstrazione(List<EventoCasuale> mazzoEventi) {
        if (mazzoEventi == null || mazzoEventi.isEmpty()) {
            return Optional.empty();
        }

        int possibilita = generatoreCasuale.nextInt(100) + 1;
        if (possibilita <= PROBABILITA_EVENTO) {
            int indicePescato = generatoreCasuale.nextInt(mazzoEventi.size());
            return Optional.of(mazzoEventi.get(indicePescato));
        }

        return Optional.empty();
    }
}