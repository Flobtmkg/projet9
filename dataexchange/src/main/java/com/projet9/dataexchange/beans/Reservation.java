package com.projet9.dataexchange.beans;


import java.util.Calendar;

public class Reservation {
    // Champs de base
    private int id;
    private int idAventure;
    private int idUser;
    private Calendar dateReservation;
    private int numEtat;
    private Calendar timestampCommentaireReservation;
    private String commentaireReservation;

    // Objets composites
    //private Aventure aventure;
    private User user;
    private EtatReservation etatReservation;


    // Constructeur avec champs de base
    public Reservation(int id, int idAventure, int idUser, Calendar dateReservation, int numEtat, Calendar timestampCommentaireReservation, String commentaireReservation) {
        this.id = id;
        this.idAventure = idAventure;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.numEtat = numEtat;
        this.timestampCommentaireReservation = timestampCommentaireReservation;
        this.commentaireReservation = commentaireReservation;
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

    public Calendar getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Calendar dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNumEtat() {
        return numEtat;
    }

    public void setNumEtat(int numEtat) {
        this.numEtat = numEtat;
    }

    public Calendar getTimestampCommentaireReservation() {
        return timestampCommentaireReservation;
    }

    public void setTimestampCommentaireReservation(Calendar timestampCommentaireReservation) {
        this.timestampCommentaireReservation = timestampCommentaireReservation;
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
}
