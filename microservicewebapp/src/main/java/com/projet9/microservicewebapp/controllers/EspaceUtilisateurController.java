package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.*;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public String goToEspaceUtilisateur(HttpServletRequest request){
        // Vérification que l'utilisateur est connecté
        User user = (User)request.getSession().getAttribute("userGuest");
        if(user==null){
            return "accueil";
        }

        // Récupération des réservations
        List<Reservation> lstRes = proxyReservation.getByUserId(user.getId());


        request.setAttribute("reservationsUtilisateur",lstRes);
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
}
