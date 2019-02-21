package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.*;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class EspaceUtilisateurController {

    @Autowired
    ProxyAventure proxyAventure;
    @Autowired
    ProxyUser proxyUser;
    @Autowired
    ProxyReservation proxyReservation;


    @GetMapping("/espaceutilisateur")
    public String goToEspaceUtilisateur(HttpServletRequest request, @RequestParam(required = false) Integer idReservation){

        // Vérification que l'utilisateur est connecté
        User user = (User)request.getSession().getAttribute("userGuest");
        if(user==null){
            return "accueil";
        }

        // Récupération des réservations
        List<Reservation> lstRes = proxyReservation.getByUserId(user.getId());

        request.setAttribute("reservationsUtilisateur",lstRes);

        // Dans le cas d'une action sur une réservation (annulation, commentaire, paiement)
        if (idReservation != null) {
            request.setAttribute("idReservation", idReservation);
            Reservation reservation = proxyReservation.getReservationById(Integer.parseInt(request.getParameter("idReservation")));

            // Pour l'ajout d'un commentaire déjà existant
            if (reservation.getCommentaireReservation() != null) {
                request.setAttribute("commentaire", reservation.getCommentaireReservation());
            }
        }

        return "espaceutilisateur";
    }


    @PostMapping("/modifInfosGenerales")
    public RedirectView modifInfoPerso(HttpServletRequest request){

        // Récupération de l'utilisateur en session
        User actualUser = (User)request.getSession().getAttribute("userGuest");
        // Recupération et affectation des données
        actualUser.setPrenom(request.getParameter("prenom"));
        actualUser.setNom(request.getParameter("nom"));
        actualUser.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance"),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        actualUser.setEmail(request.getParameter("email"));
        // Mise a jour en base
        proxyUser.update(actualUser);
        // Renvoi de l'utilisateur en session
        request.getSession().setAttribute("userGuest",actualUser);
        // Redirection avec affichage de la pop-up de validation
        return new RedirectView("/espaceutilisateur#ModalValidation");
    }


    @PostMapping("/modifInfosSupp")
    public RedirectView modifMdp(HttpServletRequest request){

        // Récupération de l'utilisateur en session
        User actualUser = (User)request.getSession().getAttribute("userGuest");
        // Recupération des données
        String motDePasseA = request.getParameter("motDePasseA");
        String motDePasseN = request.getParameter("motDePasseN");
        String motDePasseN2 = request.getParameter("motDePasseN2");

        // Comparaison avec le mdp en base
        if(motDePasseN.equals(motDePasseN2) && proxyUser.isAutentificationCorrectById(actualUser.getId(), DigestUtils.sha256Hex(motDePasseA))){
            actualUser.setPassword(DigestUtils.sha256Hex(motDePasseN));
            // Mise a jour en base
            proxyUser.update(actualUser);
        }else{
            // Erreur
            return new RedirectView("/espaceutilisateur#Modalerreur");
        }

        // Renvoi de l'utilisateur en session
        request.getSession().setAttribute("userGuest",actualUser);
        // Redirection avec affichage de la pop-up de validation
        return new RedirectView("/espaceutilisateur#ModalValidation");
    }

    @GetMapping("/espaceutilisateur/annulerReservation/{id}")
    public RedirectView annulerReservation(@PathVariable("id") int idReservation){

        //Récupération de la réservation
        Reservation reservation = proxyReservation.getReservationById(idReservation);

        //Si état à "Non payée" on passe à l'état "Annulée avant paiment"
        //Si état à "Payée" on passe à l'état "Annulée après paiment"
        if (reservation.getEtatReservation().getCode().equals(Etats.NONPAYEE.getCode())) {
            reservation.setEtatReservation(proxyReservation.getEtatReservationByCode(Etats.ANNULEEAVANTPAIEMENT.getCode()));
        } else if (reservation.getEtatReservation().getCode().equals(Etats.PAYEE.getCode())) {
            reservation.setEtatReservation(proxyReservation.getEtatReservationByCode(Etats.ANNUlEEAPRESPAIEMENT.getCode()));
        }

        // Mise a jour en base de la réservation
        proxyReservation.updateReservation(reservation);

        // Redirection avec affichage de la pop-up de validation
        return new RedirectView("/espaceutilisateur#ModalConfirmationAnnulation");
    }

    @PostMapping("/espaceutilisateur/ajouterCommentaireReservation")
    public RedirectView ajouterCommentaire(@RequestParam String idReservation, @RequestParam String commentaire){

        //Récupération de la réservation
        Reservation reservation = proxyReservation.getReservationById(Integer.parseInt(idReservation));

        // Affectation du commentaire et du timestamps à la réservation
        reservation.setCommentaireReservation(commentaire);
        reservation.setTimestampCommentaireReservation(LocalDateTime.now());

        // Mise a jour en base
        proxyReservation.updateReservation(reservation);

        // Redirection avec affichage de la pop-up de confirmation
        return new RedirectView("/espaceutilisateur#ModalConfirmationCommentaire");
    }
}
