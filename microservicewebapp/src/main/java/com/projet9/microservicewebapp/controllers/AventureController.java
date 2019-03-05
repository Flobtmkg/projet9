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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


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

        // Calcul des places restantes
        int placesRestantes = reservationManager.getNombreReservationsRestantes(reservations,aventure);

        // Si l'utilisateur est connecté : vérification d'un réservation déjà en cours
        User user = (User)request.getSession().getAttribute("userGuest");
        if(user != null){
            boolean reservationPossible = reservationManager.isReservationPossible(user, aventure);
            request.setAttribute("reservationPossible", reservationPossible);
        }

        // Récupération des réservations avec commentaires
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

        Aventure aventure = proxyAventure.getAventureById(id);
        User user = (User)request.getSession().getAttribute("userGuest");
        List<Reservation> reservations = proxyReservation.getReservationsByAventure(id);

        if (reservationManager.getNombreReservationsRestantes(reservations, aventure) > 0) {
            Reservation reservation = reservationManager.creationReservation(aventure,user);
            redirectAttributes.addFlashAttribute("nouvelleReservation", reservation);
            return new RedirectView("/aventure/" + id + "#ModalConfirmationReservation");
        } else {
            return new RedirectView("/aventure/" + id + "#ModalReservationErreur");
        }
    }
}
