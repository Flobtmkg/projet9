package com.projet9.microserviceaventures.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="aventure")
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

    // On rend la FK non insérable pour ne pas faire doublon avec l'ID de l'entité Catégorie ramenée par hibernate ??
    @Column(insertable = false, updatable = false)
    private int idCategorie;

    @Lob
    private byte[] image;

    // On définie la logique de jointure et l'ID de l'entité ramenée fait office de FK ??
    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private CategorieEntity categorieEntity;


    public AventureEntity(int id, String nom, Float prix, LocalDate dateDebut, LocalDate dateFin, String description, int idCategorie, byte[] image, CategorieEntity categorieEntity) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.idCategorie = idCategorie;
        this.image = image;
        this.categorieEntity = categorieEntity;
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

   public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
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
