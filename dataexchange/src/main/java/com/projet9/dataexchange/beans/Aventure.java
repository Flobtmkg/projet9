package com.projet9.dataexchange.beans;

import java.time.LocalDate;


public class Aventure {
    private int id;
    private String nom;
    private Float prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private int idCategorie;

    private Categorie categorie;

    public Aventure(int id, String nom, Float prix, LocalDate dateDebut, LocalDate dateFin, String description, int idCategorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.idCategorie = idCategorie;
    }

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
}