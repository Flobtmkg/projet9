package com.projet9.microserviceaventures.entities;

import com.projet9.dataexchange.beans.Aventure;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="aventure")
@Table(name="aventure")
public class AventureEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nom;
    private Float prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private int idCategorie;
    @Lob
    private byte[] image;

    public Aventure toAventureDTO(){
        return new Aventure(this.id, this.nom, this.prix, this.dateDebut, this.dateFin, this.description, this.idCategorie, this.image);
    }

}
