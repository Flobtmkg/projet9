package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.proxies.ProxyAventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class RechercheController {

    @Autowired
    ProxyAventure proxyAventure;

    @PostMapping("/recherche")
    public String doRecherche(HttpServletRequest request){
        List<String> motsCles = Arrays.asList(request.getParameter("motCles").split(" "));
        List<Aventure> aventureList = proxyAventure.getByRechercheMotsCles(motsCles);
        request.setAttribute("motCles",request.getParameter("motCles")) ;
        request.setAttribute("listAventure",aventureList) ;
        return "listeaventures";
    }
}


