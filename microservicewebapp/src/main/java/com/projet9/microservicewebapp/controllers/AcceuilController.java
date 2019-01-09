package com.projet9.microservicewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcceuilController {

    @GetMapping("/")
    public String accueil(){
        return "accueil";
    }
}
