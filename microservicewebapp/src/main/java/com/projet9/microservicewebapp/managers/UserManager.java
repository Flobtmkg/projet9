package com.projet9.microservicewebapp.managers;

import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserManager {

    @Autowired
    ProxyUser proxyUser;

    public User changementInfosPerso(User actualUser, HttpServletRequest request){
        // Recupération et affectation des données
        actualUser.setPrenom(request.getParameter("prenom"));
        actualUser.setNom(request.getParameter("nom"));
        actualUser.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        actualUser.setEmail(request.getParameter("email"));
        // Mise a jour en base
        return proxyUser.update(actualUser);
    }


    public User creationUtilisateur(User user, HttpServletRequest request){
        user.setPrenom(request.getParameter("prenom"));
        user.setNom(request.getParameter("nom"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(DigestUtils.sha256Hex(request.getParameter("motDePasse")));
        user.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        // Création de l'utilisateur
        return proxyUser.create(user);
    }

}
