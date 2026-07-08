package it.unicam.cs.mpgc.rpg130675.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg130675.model.esame.Esame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonStoricoRepository implements StoricoRepository{
    private final String FILE_PATH = "storico_esami.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void salvaEsameSuperato(Esame esame) {
        LibrettoUniversitario libretto = caricaStorico();
        if (libretto == null) {
            libretto = new LibrettoUniversitario();
        }

        EsameSalvato esameDaSalvare = new EsameSalvato(esame.getNomeMateria(), esame.getCfuForniti());
        libretto.aggiungiEsame(esameDaSalvare);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(libretto, writer);
            System.out.println("Esame '" + esame.getNomeMateria() + "' salvato nello storico permanente!");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio dello storico.");
        }
    }

    @Override
    public LibrettoUniversitario caricaStorico() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, LibrettoUniversitario.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void azzeraStorico() {
        LibrettoUniversitario librettoVuoto = new LibrettoUniversitario();

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(librettoVuoto, writer);
            System.out.println("File JSON azzerato per la nuova partita!");
        } catch (IOException e) {
            System.err.println("Errore durante l'azzeramento dello storico.");
        }
    }
}
