package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.beans.Reservation;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.dataexchange.proxies.ProxyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class AventureController {

    @Autowired
    ProxyAventure proxyAventure;
    @Autowired
    ProxyReservation proxyReservation;
    @Autowired
    ProxyUser proxyUser;

    @GetMapping("/aventure/{id}")
    public String goToAventure(HttpServletRequest request, @PathVariable("id") int id){
        Aventure aventure = proxyAventure.getAventureById(id);
        List<Reservation> reservations = proxyReservation.getReservationsByAventure(id).stream()
                .filter(reservation -> reservation.getCommentaireReservation() != null)
                .collect(Collectors.toList());
        reservations.forEach(reservation -> reservation.setUser(proxyUser.getById(reservation.getIdUser())));
        request.setAttribute("reservations", reservations);
        request.setAttribute("aventure", aventure);
        return "aventure";
    }
}
