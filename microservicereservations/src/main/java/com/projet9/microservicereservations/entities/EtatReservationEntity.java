package com.projet9.microservicereservations.entities;


import javax.persistence.*;

@Entity(name="etat_reservation")
@Table(name="etat_reservation")
public class EtatReservationEntity {

    // On rend l'ID non insérable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numEtat", updatable = false, nullable = false)
    private int numEtat;
    private String code;
    private String libelle;

    public EtatReservationEntity(int numEtat, String code, String libelle) {
        this.numEtat = numEtat;
        this.code = code;
        this.libelle = libelle;
    }

    // Constructeur par defaut exigé par hibernate
    public EtatReservationEntity() {}

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
