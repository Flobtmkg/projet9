package com.projet9.microservicewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AcceuilController {


    @GetMapping("/")
    public String goToAccueil(HttpServletRequest request){
        return "accueil";
    }

}
