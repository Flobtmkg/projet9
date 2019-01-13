package com.projet9.microservicereservation.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="reservation")
@Table(name="reservation")
public class ReservationEntity {

    // On rend l'ID non insérable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private int idAventure;
    private int idUser;

    // On rend la FK non insérable pour ne pas faire doublon avec l'ID de l'entité EtatReservation ramenée par hibernate ??
    @Column(insertable = false, updatable = false)
    private int numEtat;

    private LocalDate dateReservation;
    private LocalDateTime timestampCommentaireReservation;
    private String commentaireReservation;


    // On définie la logique de jointure et l'ID de l'entité ramenée fait office de FK ??
    @ManyToOne
    @JoinColumn(name = "numEtat")
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
