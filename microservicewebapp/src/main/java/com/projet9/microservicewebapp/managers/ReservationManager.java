package com.projet9.microservicewebapp.managers;

import com.projet9.dataexchange.beans.*;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationManager {

    @Autowired
    ProxyReservation proxyReservation;

    @Autowired
    ProxyUser proxyUser;


    public List<Reservation> annulationAutoReservationNonPayees(List<Reservation> reservations){
        // Annulation automatique des réservation de l'aventure de plus de 24h non payées
        reservations.stream()
                .filter(reservation -> reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode())
                        && reservation.getDateReservation().plusDays(1).isBefore(LocalDate.now()))
                .forEach(reservation -> {
                    reservation.setEtatReservation(proxyReservation.getEtatReservationByCode(Etats.ANNULEEAVANTPAIEMENT.getCode()));
                    proxyReservation.updateReservation(reservation);
                });
        return reservations;
    }


    public int getNombreReservationsRestantes(List<Reservation> reservations, Aventure aventure){
        long nbReservation = reservations.stream()
                .filter(reservation -> !reservation.isReservationPrecedente() &&
                        (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode()) ||
                                reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode()))
                )
                .count();
        return (int)(aventure.getLimiteReservation() - nbReservation);
    }


    public boolean isReservationPossible(User user, Aventure aventure){
        List<Reservation> userReservations = proxyReservation.getReservationByUserId(user.getId());
        return !userReservations.stream().anyMatch(reservation ->
                !reservation.isReservationPrecedente() &&
                        (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode()) || reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode())) &&
                        reservation.getAventure().getId() == aventure.getId());
    }


    public List<Reservation> getReservationsCommentees(List<Reservation> reservations){
        List<Reservation> reservationsAvecCommentaire = new ArrayList<>();
        reservations.forEach(reservation -> {
            if (reservation.getCommentaireReservation() != null) {
                reservation.setUser(proxyUser.getById(reservation.getIdUser()));
                reservationsAvecCommentaire.add(reservation);
            }
        });
        return reservationsAvecCommentaire;
    }


    public Reservation creationReservation(Aventure aventure, User user){
        // Initialisation de la réservation à créer
        Reservation reservation = new Reservation();
        EtatReservation etatReservation = proxyReservation.getEtatReservationByCode(Etats.NONPAYEE.getCode());

        // Construction de la réservation
        reservation.setAventure(aventure);
        reservation.setIdAventure(aventure.getId());
        reservation.setUser(user);
        reservation.setIdUser(user.getId());
        reservation.setEtatReservation(etatReservation);
        reservation.setNumEtat(etatReservation.getNumEtat());
        reservation.setDateReservation(LocalDate.now());
        reservation.setReservationPrecedente(false);

        // Création de la réservation en base
        return proxyReservation.createReservation(reservation);
    }
}
