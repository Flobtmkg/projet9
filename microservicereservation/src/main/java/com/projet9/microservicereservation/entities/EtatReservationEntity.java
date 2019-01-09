package com.projet9.microservicereservation.entities;

import com.projet9.dataexchange.beans.EtatReservation;

import javax.persistence.*;

@Entity(name="etat_reservation")
@Table(name="etat_reservation")
public class EtatReservationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int numEtat;
    private String etat;

    public EtatReservationEntity(int numEtat, String etat) {
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
