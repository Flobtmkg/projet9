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

    public EtatReservation toEtatReservationDTO(){
        return new EtatReservation(this.numEtat, this.etat);
    }
}
