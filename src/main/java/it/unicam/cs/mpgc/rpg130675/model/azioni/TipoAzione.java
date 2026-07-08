package it.unicam.cs.mpgc.rpg130675.model.azioni;

public enum TipoAzione {
    STUDIA("Studia", "-20 Energia | +10 Con | +15 Stress"),
    LAVORA("Lavora", "-30 Energia | +40 Den | + Stress Random"),
    FESTA("Festa", "-20 Energia | -30 Denaro | -40 Stress"),
    DORMI("Riposa", "+50 Energia"),
    LIBRETTO("Libretto Universitario", "Consulta i tuoi CFU");

    private final String nomeDescrittivo;
    private final String impattoStatistiche;

    TipoAzione(String nomeDescrittivo, String impattoStatistiche) {
        this.nomeDescrittivo = nomeDescrittivo;
        this.impattoStatistiche = impattoStatistiche;
    }

    public String getNomeDescrittivo() {
        return nomeDescrittivo;
    }

    public String getImpattoStatistiche() {
        return impattoStatistiche;
    }
}
