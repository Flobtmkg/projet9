package com.projet9.dataexchange.beans;

import java.time.LocalDate;


public class Aventure {
    private int id;
    private String nom;
    private Float prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private int limiteReservation;
    private LocalDate dateCloture;
    private int idCategorie;
    private byte[] image;
    private Categorie categorie;

    // Constructeur par champ
    public Aventure(int id, String nom, Float prix, LocalDate dateDebut, LocalDate dateFin, String description, int limiteReservation, LocalDate dateCloture, int idCategorie, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.limiteReservation = limiteReservation;
        this.dateCloture = dateCloture;
        this.idCategorie = idCategorie;
        this.categorie = categorie;
    }

    // Constructeur par defaut
    public Aventure(){}

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getLimiteReservation() {
        return limiteReservation;
    }

    public void setLimiteReservation(int limiteReservation) {
        this.limiteReservation = limiteReservation;
    }


    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }


    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
