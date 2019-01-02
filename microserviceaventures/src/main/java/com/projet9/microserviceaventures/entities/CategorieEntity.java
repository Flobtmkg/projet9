package com.projet9.microserviceaventures.entities;

import com.projet9.dataexchange.beans.Categorie;

import javax.persistence.*;

@Entity(name="categorie")
@Table(name="categorie")
public class CategorieEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;


    public Categorie toCategorieDTO(){
        return new Categorie(this.id, this.nom, this.description);
    }

}
