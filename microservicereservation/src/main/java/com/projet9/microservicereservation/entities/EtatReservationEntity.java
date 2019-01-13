package com.projet9.microservicereservation.entities;


import javax.persistence.*;

@Entity(name="etat_reservation")
@Table(name="etat_reservation")
public class EtatReservationEntity {

    // On rend l'ID non insérable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numEtat", updatable = false, nullable = false)
    private int numEtat;
    private String etat;

    public EtatReservationEntity(int numEtat, String etat) {
        this.numEtat = numEtat;
        this.etat = etat;
    }

    // Constructeur par defaut exigé par hibernate
    public EtatReservationEntity(){

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
