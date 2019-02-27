package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.*;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.dataexchange.proxies.ProxyPaiement;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class AventureController {

    @Autowired
    ProxyAventure proxyAventure;
    @Autowired
    ProxyReservation proxyReservation;
    @Autowired
    ProxyUser proxyUser;
    @Autowired
    ProxyPaiement proxyPaiement;

    @GetMapping("/aventure/{id}")
    public String goToAventure(HttpServletRequest request, @PathVariable("id") int id){

        Aventure aventure = proxyAventure.getAventureById(id);

        List<Reservation> reservations = proxyReservation.getReservationsByAventure(id);

        // Annulation automatique des réservation de l'aventure de plus de 24h non payées
        reservations.stream()
                .filter(reservation -> reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode())
                        && reservation.getDateReservation().plusDays(1).isBefore(LocalDate.now()))
                .forEach(reservation -> {
                    reservation.setEtatReservation(proxyReservation.getEtatReservationByCode(Etats.ANNULEEAVANTPAIEMENT.getCode()));
                    proxyReservation.updateReservation(reservation);
                });

        // Calcul des places restantes
        long nbReservation = reservations.stream()
                .filter(reservation -> !reservation.isReservationPrecedente() &&
                        (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode()) ||
                        reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode()))
                    )
                .count();
        long placesRestantes = aventure.getLimiteReservation() - nbReservation;

        // Si l'utilisateur est connecté : vérification d'un réservation déjà en cours
        User user = (User)request.getSession().getAttribute("userGuest");
        if(user != null){
            List<Reservation> userReservations = proxyReservation.getReservationByUserId(user.getId());
            boolean reservationPossible = !userReservations.stream().anyMatch(reservation ->
                    !reservation.isReservationPrecedente() &&
                    (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode()) || reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode())) &&
                    reservation.getAventure().getId() == aventure.getId());
            request.setAttribute("reservationPossible", reservationPossible);
        }

        // Récupération des commentaires
        List<Reservation> reservationsAvecCommentaire = new ArrayList<>();
        reservations.forEach(reservation -> {
            if (reservation.getCommentaireReservation() != null) {
                reservation.setUser(proxyUser.getById(reservation.getIdUser()));
                reservationsAvecCommentaire.add(reservation);
            }
        });

        request.setAttribute("aventure", aventure);
        request.setAttribute("placesRestantes", placesRestantes);
        request.setAttribute("reservations", reservationsAvecCommentaire);

        return "aventure";
    }

    @GetMapping("/aventure/{id}/reserver")
    public RedirectView reserverAventure(HttpServletRequest request, @PathVariable("id") int id) {

        // Récupération de l'aventure et de l'utilisateur
        Aventure aventure = proxyAventure.getAventureById(id);
        User user = (User)request.getSession().getAttribute("userGuest");

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
        proxyReservation.createReservation(reservation);

        request.setAttribute("nouvelleReservation", reservation);

        // Redirection avec affichage de la pop-up de validation
        return new RedirectView("/aventure/" + id + "#ModalConfirmationAnnulation");
    }
}
