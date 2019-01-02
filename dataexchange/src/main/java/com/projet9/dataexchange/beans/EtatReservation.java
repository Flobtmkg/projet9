package com.projet9.dataexchange.beans;

public class EtatReservation {


private int numEtat;
private String etat;

    public EtatReservation(int numEtat, String etat) {
        this.numEtat = numEtat;
        this.etat = etat;
    }

    public int getNumEtat() {
        return numEtat;
    }

    public void setNumEtat(int numEtat) {
        this.numEtat = numEtat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
