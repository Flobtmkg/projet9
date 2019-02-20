package com.projet9.microserviceaventures.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Aventure")
@Table(name="aventure")
public class AventureEntity {

    // On rend l'ID non insérable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String nom;
    private Float prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private int limitReservation;
    private LocalDate dateCloture;


    @Lob
    private byte[] image;


    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private CategorieEntity categorieEntity;


    public AventureEntity(int id, String nom, Float prix, LocalDate dateDebut, LocalDate dateFin, String description, int limitReservation, LocalDate dateCloture, byte[] image, CategorieEntity categorieEntity) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.image = image;
        this.categorieEntity = categorieEntity;
        this.limitReservation = limitReservation;
        this.dateCloture = dateCloture;
    }

    // Constructeur par defaut exigé par hibernate
    public AventureEntity(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getLimitReservation() {
        return limitReservation;
    }

    public void setLimitReservation(int limitReservation) {
        this.limitReservation = limitReservation;
    }



    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CategorieEntity getCategorieEntity() {
        return categorieEntity;
    }

    public void setCategorieEntity(CategorieEntity categorieEntity) {
        this.categorieEntity = categorieEntity;
    }
}
