package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConnexionController {

    @Autowired
    ProxyUser proxyUser;

    @PostMapping("/connexionUtilisateur")
    public RedirectView connexion(HttpServletRequest request){

        // Récupération de l'utilisateur associé
        User user = proxyUser.autentification(request.getParameter("idco"), DigestUtils.sha256Hex(request.getParameter("mdp")));

        if(user==null){
            // Erreur
            return new RedirectView("/#ModalErreur");
        }
        request.getSession().setAttribute("userGuest",user);
        return new RedirectView("/");
    }


    @GetMapping("/deconnexion")
    public RedirectView deconnexion(HttpServletRequest request){
        request.getSession().invalidate();
        return new RedirectView("/");
    }
}
