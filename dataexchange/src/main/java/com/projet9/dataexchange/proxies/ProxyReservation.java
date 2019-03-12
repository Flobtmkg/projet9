package com.projet9.dataexchange.proxies;


import com.projet9.dataexchange.FeignConfig;
import com.projet9.dataexchange.beans.EtatReservation;
import com.projet9.dataexchange.beans.Reservation;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "zuulapigateway", configuration = FeignConfig.class)
@RibbonClient(name = "microservicereservations")
public interface ProxyReservation {

    // Accès aux reservations

    @PostMapping("microservicereservations/api/Reservations")
    Reservation createReservation(@RequestBody Reservation reservation);

    @PutMapping("microservicereservations/api/Reservations")
    Reservation updateReservation(@RequestBody Reservation reservation);

    @GetMapping("microservicereservations/api/Reservations/{id}")
    Reservation getReservationById(@PathVariable("id") int id);

    @GetMapping("microservicereservations/api/Reservations/Aventures/{id}")
    List<Reservation> getReservationsByAventure(@PathVariable("id") int id);

    @GetMapping("microservicereservations/api/Reservations/User/{idUser}")
    List<Reservation> getReservationByUserId(@PathVariable("idUser") int id);

    @GetMapping("microservicereservations/api/Reservations")
    List<Reservation> getAllReservation();

    @DeleteMapping("microservicereservations/api/Reservations/{id}")
    void deleteReservation(@PathVariable("id") int id);

    @DeleteMapping("microservicereservations/api/Reservations/User/{idUser}")
    void deleteByUserId(@PathVariable("idUser") int idUser);

    @DeleteMapping("microservicereservations/api/Reservations/Aventure/{idAventure}")
    void deleteByAventureId(@PathVariable("idAventure") int idUser);



    // Accès aux etatReservations

    @PostMapping("microservicereservations/api/EtatReservations")

    EtatReservation createEtatReservation(@RequestBody EtatReservation EtatReservation);

    @PutMapping("microservicereservations/api/EtatReservations")
    EtatReservation updateEtatReservation(@RequestBody EtatReservation EtatReservation);

    /**@GetMapping("microservicereservations/api/EtatReservations/{id}")
    EtatReservation getEtatReservationById(@PathVariable("id") int id);**/

    @GetMapping("microservicereservations/api/EtatReservations/{code}")
    EtatReservation getEtatReservationByCode(@PathVariable("code") String code);

    @GetMapping("microservicereservations/api/EtatReservations")
    List<EtatReservation> getAllEtatReservation();

    @DeleteMapping("microservicereservations/api/EtatReservations/{id}")
    void deleteEtatReservation(@PathVariable("id") int id);

}
