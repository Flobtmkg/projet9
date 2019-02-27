package com.projet9.dataexchange.beans;

public class EtatReservation {

    private int numEtat;
    private String code;
    private String libelle;

    public EtatReservation(int numEtat, String code, String libelle) {
        this.numEtat = numEtat;
        this.code = code;
        this.libelle = libelle;
    }

    // Constructeur par defaut
    public EtatReservation(){}

    public int getNumEtat() {
        return numEtat;
    }

    public void setNumEtat(int numEtat) {
        this.numEtat = numEtat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
