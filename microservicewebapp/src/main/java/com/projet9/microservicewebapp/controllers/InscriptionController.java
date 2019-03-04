package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.proxies.ProxyUser;
import com.projet9.microservicewebapp.managers.UserManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class InscriptionController {

    @Autowired
    ProxyUser proxyUser;

    @Autowired
    UserManager userManager;

    @GetMapping("/inscription")
    public String goToInscription(HttpServletRequest request){

        // Vérification que l'utilisateur n'est pas déjà connecté
        User user = (User)request.getSession().getAttribute("userGuest");
        if(user==null){
            return "inscription";
        }else {
            return "accueil";
        }
    }


    @PostMapping("/inscription")
    public RedirectView Inscription(HttpServletRequest request){
        // Récupération des données et création d'un utilisateur
        User user = new User();
        try{
            userManager.creationUtilisateur(user, request);
            /*user.setPrenom(request.getParameter("prenom"));
            user.setNom(request.getParameter("nom"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(DigestUtils.sha256Hex(request.getParameter("motDePasse")));
            user.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            // Création de l'utilisateur
            user = proxyUser.create(user);*/
        }catch(Exception e){
            return new RedirectView("/inscription#ModalerreurInscription");
        }
        request.getSession().setAttribute("userGuest",user);
        return new RedirectView("/");
    }
}
