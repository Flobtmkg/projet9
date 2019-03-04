package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.*;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.dataexchange.proxies.ProxyPaiement;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.dataexchange.proxies.ProxyUser;
import com.projet9.microservicewebapp.managers.ReservationManager;
import com.projet9.microservicewebapp.managers.UtilitaireManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    ReservationManager reservationManager;
    @Autowired
    UtilitaireManager utilitaireManager;

    @GetMapping("/aventure/{id}")
    public String goToAventure(HttpServletRequest request, @PathVariable("id") int id){

        Aventure aventure = proxyAventure.getAventureById(id);

        List<Reservation> reservations = proxyReservation.getReservationsByAventure(id);
        // Annulation automatique des réservation de l'aventure de plus de 24h non payées
        reservations = reservationManager.annulationAutoReservationNonPayees(reservations);
        /*
        reservations.stream()
                .filter(reservation -> reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode())
                        && reservation.getDateReservation().plusDays(1).isBefore(LocalDate.now()))
                .forEach(reservation -> {
                    reservation.setEtatReservation(proxyReservation.getEtatReservationByCode(Etats.ANNULEEAVANTPAIEMENT.getCode()));
                    proxyReservation.updateReservation(reservation);
                });*/


        // Calcul des places restantes
        /*long nbReservation = reservations.stream()
                .filter(reservation -> !reservation.isReservationPrecedente() &&
                        (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode()) ||
                        reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode()))
                    )
                .count();
        long placesRestantes = aventure.getLimiteReservation() - nbReservation;*/

        // Calcul des places restantes
        int placesRestantes = reservationManager.getNombreReservationsRestantes(reservations,aventure);

        // Si l'utilisateur est connecté : vérification d'un réservation déjà en cours
        User user = (User)request.getSession().getAttribute("userGuest");
        if(user != null){
            /*List<Reservation> userReservations = proxyReservation.getReservationByUserId(user.getId());
            boolean reservationPossible = !userReservations.stream().anyMatch(reservation ->
                    !reservation.isReservationPrecedente() &&
                    (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode()) || reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode())) &&
                    reservation.getAventure().getId() == aventure.getId());*/
            boolean reservationPossible = reservationManager.isReservationPossible(user, aventure);
            request.setAttribute("reservationPossible", reservationPossible);
        }

        // Récupération des commentaires
        /*List<Reservation> reservationsAvecCommentaire = new ArrayList<>();
        reservations.forEach(reservation -> {
            if (reservation.getCommentaireReservation() != null) {
                reservation.setUser(proxyUser.getById(reservation.getIdUser()));
                reservationsAvecCommentaire.add(reservation);
            }
        });*/
        List<Reservation> reservationsAvecCommentaire = reservationManager.getReservationsCommentees(reservations);


        // On propage la nouvelle réservation si celle-ci vient d'être crée
        Map flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null && flashMap.get("nouvelleReservation")!= null){
            request.setAttribute("nouvelleReservation", flashMap.get("nouvelleReservation"));
        }
        request.setAttribute("aventure", aventure);
        request.setAttribute("placesRestantes", placesRestantes);
        request.setAttribute("reservations", reservationsAvecCommentaire);
        request.setAttribute("currentIP", utilitaireManager.getCurrentIPAddress());
        return "aventure";
    }

    @GetMapping("/aventure/{id}/reserver")
    public RedirectView reserverAventure(HttpServletRequest request, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        // Récupération de l'aventure et de l'utilisateur
        Aventure aventure = proxyAventure.getAventureById(id);
        User user = (User)request.getSession().getAttribute("userGuest");

        /*// Initialisation de la réservation à créer
        Reservation reservation = new Reservation();
        EtatReservation etatReservation = proxyReservation.getEtatReservationByCode(Etats.NONPAYEE.getCode());

        // Création de la réservation en base
        reservation = proxyReservation.createReservation(reservation);*/

        Reservation reservation = reservationManager.creationReservation(aventure,user);

        redirectAttributes.addFlashAttribute("nouvelleReservation", reservation);

        return new RedirectView("/aventure/" + id + "#ModalConfirmationReservation");
    }


    /*// Astuce pour récupérer l'adresse IP de la machine (serveur)
    private String getCurrentIPAddress(){
        String ip;
        try{
            final DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        }catch(Exception e){
            ip = "";
        }
        return ip;
    }*/
}
