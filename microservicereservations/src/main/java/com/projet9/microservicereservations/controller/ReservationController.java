package com.projet9.microservicereservations.controller;

import com.projet9.dataexchange.beans.Reservation;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.dataexchange.proxies.ProxyAventure;
import com.projet9.microservicereservations.dao.EtatReservationDao;
import com.projet9.microservicereservations.dao.ReservationDao;
import com.projet9.microservicereservations.entities.ReservationEntity;
import com.projet9.microservicereservations.mapper.EtatReservationMapper;
import com.projet9.microservicereservations.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    @Autowired
    ProxyAventure proxyAventure;

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    EtatReservationDao etatReservationDao;


    @PostMapping(path = "/api/Reservations", produces = "application/json")
    public Reservation create(@RequestBody Reservation reservation){
        // Lors de la création, numEtat peut être renseigné sans que le bean EtatReservation correspondant ne soit chargé
        if(reservation.getEtatReservation()==null){
            reservation.setEtatReservation(EtatReservationMapper.toDto(etatReservationDao.getOne(reservation.getNumEtat())));
        }
        ReservationEntity savedReservationEntity = reservationDao.save(ReservationMapper.toEntity(reservation));
        // retourne l'entité avec le nouvel id crée
        return ReservationMapper.toDto(savedReservationEntity);
    }


    @PutMapping(path = "/api/Reservations", produces = "application/json")
    public Reservation update(@RequestBody Reservation reservation){
        if(reservationDao.existsById(reservation.getId())){
            ReservationEntity savedReservationEntity = reservationDao.save(ReservationMapper.toEntity(reservation));
            return ReservationMapper.toDto(savedReservationEntity);
        }
        throw new ObjectNotFoundException(reservation.getId(),ReservationEntity.class);
    }


    @GetMapping(path = "/api/Reservations/{id}", produces = "application/json")
    public Reservation getById(@PathVariable("id") int id){
        Optional<ReservationEntity> optionalReservationEntity = reservationDao.findById(id);
        return ReservationMapper.toDto(optionalReservationEntity.orElseThrow(()-> new ObjectNotFoundException(id,ReservationEntity.class)));
    }


    @GetMapping(path = "/api/Reservations/User/{idUser}", produces = "application/json")
    public List<Reservation> getByUserId(@PathVariable("idUser") int id) {
        List<Reservation> listRes = reservationDao.findReservationEntitiesByIdUser(id).stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());
        listRes.forEach(reservation -> reservation.setAventure(proxyAventure.getAventureById(reservation.getIdAventure())));
        return listRes;
    }


    @GetMapping(path = "/api/Reservations/Aventures/{id}", produces = "application/json")
    public List<Reservation> getByAventure(@PathVariable("id") int id) {
        return reservationDao.findReservationEntitiesByIdAventure(id).stream().map(ReservationMapper::toDto).collect(Collectors.toList());
    }


    @GetMapping(path = "/api/Reservations", produces = "application/json")
    public List<Reservation> getAll(){
        return reservationDao.findAll().stream().map(ReservationMapper::toDto).collect(Collectors.toList());
    }


    @DeleteMapping(path = "/api/Reservations/{id}", produces = "application/json")
    public void delete(@PathVariable("id") int id){
        reservationDao.deleteById(id);
    }

    @DeleteMapping(path = "/api/Reservations/User/{idUser}", produces = "application/json")
    public void deleteByUserId(@PathVariable("idUser") int idUser){
        reservationDao.deleteReservationEntitiesByIdUser(idUser);
    }

    @DeleteMapping(path = "/api/Reservations/Aventure/{idAventure}", produces = "application/json")
    public void deleteByAventureId(@PathVariable("idAventure") int idAventure){
        reservationDao.deleteReservationEntitiesByIdAventure(idAventure);
    }
}
