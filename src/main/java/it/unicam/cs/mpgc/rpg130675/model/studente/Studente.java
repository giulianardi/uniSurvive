package it.unicam.cs.mpgc.rpg130675.model.studente;

public interface Studente {
        String getNome();
        String getFacolta();

        int getEnergia();
        int getConoscenza();
        int getStress();
        int getDenaro();
        int getCFU();

        void editEnergia(int quantita);
        void editConoscenza(int quantita);
        void editStress(int quantita);
        void editDenaro(int quantita);
        void addCFU(int quantita);


    boolean isInBurnout();
    }

