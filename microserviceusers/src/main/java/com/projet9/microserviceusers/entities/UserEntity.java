package com.projet9.microserviceusers.entities;

import com.projet9.dataexchange.beans.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String email;
    private String password;
    @Lob
    private byte[] image;


    public User toUserDTO(){
        return new User(this.id, this.nom, this.prenom, this.dateNaissance, this.email, this.password, this.image);
    }
}
