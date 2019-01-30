package com.projet9.microservicewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InscriptionController {

    @GetMapping("/inscription")
    public String toInscription(HttpServletRequest request){
        return "inscription";
    }
}
