package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.proxies.ProxyAventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AcceuilController {

    @Autowired
    ProxyAventure proxyAventure;

    @GetMapping("/")
    public String accueil(HttpServletRequest request){
        List<Aventure> avs = proxyAventure.getAllAventures();
        request.setAttribute("aventuresList",avs);
        return "accueil";
    }
}
