package com.projet9.microservicereservation.entities;

import com.projet9.dataexchange.beans.Reservation;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="reservation")
@Table(name="reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int idAventure;
    private int idUser;
    private LocalDate dateReservation;
    private int numEtat;
    private LocalDateTime timestampCommentaireReservation;
    private String commentaireReservation;


    public Reservation toReservationDTO(){
        return new Reservation(this.id, this.idAventure, this.idUser, this.dateReservation, this.numEtat, this.timestampCommentaireReservation, this.commentaireReservation);
    }
}
