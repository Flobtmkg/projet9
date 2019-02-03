package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.proxies.ProxyAventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AcceuilController {

    // Test proxies
    //@Autowired
    //ProxyAventure proxyAventure;

    @GetMapping("/")
    public String accueil(HttpServletRequest request){

        // Test proxies
        //List<Aventure> avs = proxyAventure.getAllAventures();
        //request.setAttribute("aventuresList",avs);

        User us = new User(1, "mon nom", "mon prenom", LocalDate.of(1990,10,18), "manewadress@gmail.com", "password", null );
        request.getSession().setAttribute("userGuest",us);
        return "accueil";
    }

}
