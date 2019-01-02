package com.projet9.dataexchange.beans;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    // Champs de base
    private int id;
    private int idAventure;
    private int idUser;
    private LocalDate dateReservation;
    private int numEtat;
    private LocalDateTime timestampCommentaireReservation;
    private String commentaireReservation;

    // Objets composites
    private Aventure aventure;
    private User user;
    private EtatReservation etatReservation;


    // Constructeur avec champs de base
    public Reservation(int id, int idAventure, int idUser, LocalDate dateReservation, int numEtat, LocalDateTime timestampCommentaireReservation, String commentaireReservation) {
        this.id = id;
        this.idAventure = idAventure;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.numEtat = numEtat;
        this.timestampCommentaireReservation = timestampCommentaireReservation;
        this.commentaireReservation = commentaireReservation;
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

    public String getCommentaireReservation() {
        return commentaireReservation;
    }

    public void setCommentaireReservation(String commentaireReservation) {
        this.commentaireReservation = commentaireReservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EtatReservation getEtatReservation() {
        return etatReservation;
    }

    public void setEtatReservation(EtatReservation etatReservation) {
        this.etatReservation = etatReservation;
    }

    public Aventure getAventure() {
        return aventure;
    }

    public void setAventure(Aventure aventure) {
        this.aventure = aventure;
    }
}
