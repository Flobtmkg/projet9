package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EspaceUtilisateurController {

    @GetMapping("/espaceutilisateur")
    public String toEspaceUtilisateur(HttpServletRequest request){
        // test pour checker l'affichage des reservations
        List<Reservation> lstRes = new ArrayList<>();
        Reservation a = new Reservation(1, 1, 1, 1, LocalDate.now(), null, null, new EtatReservation(1, Etats.NONPAYEE.toString()));
        a.setAventure(new Aventure(1, "première aventure", 55f, LocalDate.of(2019,3,10), LocalDate.of(2019,4,1), "c'est la première aventure", null, 1, new Categorie(1,"Aventure a la con","c'est une aventure a la con")));
        Reservation b = new Reservation(2, 2, 1, 3, LocalDate.now(), null, null, new EtatReservation(3,Etats.PAYEE.toString()));
        b.setAventure(new Aventure(2, "deuxième aventure", 75f, LocalDate.of(2019,4,1), LocalDate.of(2019,4,10), "c'est la deuxième aventure", null, 1, new Categorie(1,"Aventure a la con","c'est une aventure a la con")));
        lstRes.add(a);
        lstRes.add(b);
        request.setAttribute("reservationsUtilisateur",lstRes);
        return "espaceutilisateur";
    }
}
