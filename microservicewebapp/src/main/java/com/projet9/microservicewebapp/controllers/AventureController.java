package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.proxies.ProxyAventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AventureController {

    @Autowired
    ProxyAventure proxyAventure;

    @GetMapping("/aventure/{id}")
    public String toAventure(HttpServletRequest request, @PathVariable("id") int id){
        Aventure aventure = proxyAventure.getAventureById(id);
        request.setAttribute("aventure",aventure);
        return "aventure";
    }
}
