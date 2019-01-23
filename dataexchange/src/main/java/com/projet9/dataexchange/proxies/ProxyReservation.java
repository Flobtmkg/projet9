package com.projet9.dataexchange.proxies;


import com.projet9.dataexchange.beans.EtatReservation;
import com.projet9.dataexchange.beans.Reservation;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuulapigateway")
@RibbonClient(name = "microservicereservations")
public interface ProxyReservation {

    // Accès aux reservations

    @PostMapping("/api/Reservations")
    Reservation createReservation(@RequestBody Reservation aventure);

    @PutMapping("/api/Reservations")
    Reservation updateReservation(@RequestBody Reservation aventure);

    @GetMapping("/api/Reservations/{id}")
    Reservation getReservationById(@PathVariable("id") int id);

    @GetMapping("/api/Reservations")
    List<Reservation> getAllReservation();

    @DeleteMapping("/api/Reservations/{id}")
    void deleteReservation(@PathVariable("id") int id);


    // Accès aux etatReservations

    @PostMapping("/api/EtatReservations")
    EtatReservation createCategorie(@RequestBody EtatReservation EtatReservation);

    @PutMapping("/api/EtatReservations")
    EtatReservation updateCategorie(@RequestBody EtatReservation EtatReservation);

    @GetMapping("/api/EtatReservations/{id}")
    EtatReservation getCategorieById(@PathVariable("id") int id);

    @GetMapping("/api/EtatReservations")
    List<EtatReservation> getAllCategories();

    @DeleteMapping("/api/EtatReservations/{id}")
    void deleteCategorie(@PathVariable("id") int id);

}
