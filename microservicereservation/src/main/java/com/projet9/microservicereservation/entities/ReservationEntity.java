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
    private int numEtat;
    private LocalDate dateReservation;
    private LocalDateTime timestampCommentaireReservation;
    private String commentaireReservation;
    @ManyToOne
    private EtatReservationEntity etatReservationEntity;

    public ReservationEntity(int id, int idAventure, int idUser, int numEtat, LocalDate dateReservation, LocalDateTime timestampCommentaireReservation, String commentaireReservation, EtatReservationEntity etatReservationEntity) {
        this.id = id;
        this.idAventure = idAventure;
        this.idUser = idUser;
        this.numEtat = numEtat;
        this.dateReservation = dateReservation;
        this.timestampCommentaireReservation = timestampCommentaireReservation;
        this.commentaireReservation = commentaireReservation;
        this.etatReservationEntity = etatReservationEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAventure() {
        return idAventure;
    }

    public void setIdAventure(int idAventure) {
        this.idAventure = idAventure;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNumEtat() {
        return numEtat;
    }

    public void setNumEtat(int numEtat) {
        this.numEtat = numEtat;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalDateTime getTimestampCommentaireReservation() {
        return timestampCommentaireReservation;
    }

    public void setTimestampCommentaireReservation(LocalDateTime timestampCommentaireReservation) {
        this.timestampCommentaireReservation = timestampCommentaireReservation;
    }

    public String getCommentaireReservation() {
        return commentaireReservation;
    }

    public void setCommentaireReservation(String commentaireReservation) {
        this.commentaireReservation = commentaireReservation;
    }

    public EtatReservationEntity getEtatReservationEntity() {
        return etatReservationEntity;
    }

    public void setEtatReservationEntity(EtatReservationEntity etatReservationEntity) {
        this.etatReservationEntity = etatReservationEntity;
    }
}
