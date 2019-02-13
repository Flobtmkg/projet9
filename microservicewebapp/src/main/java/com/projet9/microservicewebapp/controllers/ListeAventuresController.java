package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.proxies.ProxyAventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ListeAventuresController {

    @Autowired
    ProxyAventure proxyAventure;

    @GetMapping("/categories/{id}")
    public String toListeAventures(@PathVariable int id, HttpServletRequest request){
        List<Aventure> listAventure = proxyAventure.getAventuresByCategorie(id);
        request.setAttribute("listAventure", listAventure);
        return  "listeaventures";
    }
}
