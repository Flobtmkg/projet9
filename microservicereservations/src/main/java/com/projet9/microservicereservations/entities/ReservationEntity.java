package com.projet9.microservicereservations.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="Reservation")
@Table(name="reservation")
public class ReservationEntity {

    // On rend l'ID non insérable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private LocalDate dateReservation;
    private LocalDateTime timestampCommentaireReservation;
    private String commentaireReservation;
    private boolean isReservationPrecedente;

    private int idAventure;
    private int idUser;

    @ManyToOne
    @JoinColumn(name = "num_etat")
    private EtatReservationEntity etatReservationEntity;


    public ReservationEntity(int id, int idAventure, int idUser, LocalDate dateReservation, LocalDateTime timestampCommentaireReservation, String commentaireReservation, boolean isReservationPrecedente, EtatReservationEntity etatReservationEntity) {
        this.id = id;
        this.idAventure = idAventure;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.timestampCommentaireReservation = timestampCommentaireReservation;
        this.commentaireReservation = commentaireReservation;
        this.etatReservationEntity = etatReservationEntity;
        this.isReservationPrecedente = isReservationPrecedente;
    }

    // Constructeur par defaut exigé par hibernate
    public ReservationEntity(){
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

    public boolean isReservationPrecedente() {
        return isReservationPrecedente;
    }

    public void setReservationPrecedente(boolean reservationPrecedente) {
        isReservationPrecedente = reservationPrecedente;
    }


    public EtatReservationEntity getEtatReservationEntity() {
        return etatReservationEntity;
    }

    public void setEtatReservationEntity(EtatReservationEntity etatReservationEntity) {
        this.etatReservationEntity = etatReservationEntity;
    }
}
