package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Etats;
import com.projet9.dataexchange.beans.Reservation;
import com.projet9.dataexchange.proxies.ProxyReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ValidationPaimentController {

    @Autowired
    ProxyReservation proxyReservation;


    @RequestMapping("/aventure/paiement/validation")
    public RedirectView retourPaiement(HttpServletRequest request){
        // IdReservation retourné par Paypal en cas de payement validé
        String idReservation = request.getParameter("custom");
        Reservation reservation;
        if(idReservation.isEmpty() == false){
            reservation = proxyReservation.getReservationById(Integer.valueOf(idReservation));
        }else{
            // Erreur de renvoi de variable par Paypal qui n'est pas sensée arriver
            return new RedirectView("/espaceutilisateur#ModalERREURQQCHCESTMALPASSE");
        }
        reservation.setEtatReservation(proxyReservation.getEtatReservationByCode(Etats.PAYEE.getCode()));
        reservation.setNumEtat(reservation.getEtatReservation().getNumEtat());
        // Mise a jour de la reseration
        proxyReservation.updateReservation(reservation);

        return new RedirectView("/espaceutilisateur");
    }
}
