package com.projet9.microservicereservation.controller;

import com.projet9.dataexchange.beans.Reservation;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microservicereservation.dao.EtatReservationDao;
import com.projet9.microservicereservation.dao.ReservationDao;
import com.projet9.microservicereservation.entities.ReservationEntity;
import com.projet9.microservicereservation.mapper.EtatReservationMapper;
import com.projet9.microservicereservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    EtatReservationDao etatReservationDao;

    @PostMapping("/api/Reservations")
    public Reservation create(@RequestBody Reservation reservation){
        // Lors de la création, numEtat peut être renseigné sans que le bean EtatReservation correspondant ne soit chargé
        if(reservation.getEtatReservation()==null){
            reservation.setEtatReservation(EtatReservationMapper.toDto(etatReservationDao.getOne(reservation.getNumEtat())));
        }
        ReservationEntity savedReservationEntity = reservationDao.save(ReservationMapper.toEntity(reservation));
        // retourne l'entité avec le nouvel id crée
        return ReservationMapper.toDto(savedReservationEntity);
    }


    @PutMapping("/api/Reservations")
    public Reservation update(@RequestBody Reservation reservation){
        if(reservationDao.existsById(reservation.getId())){
            ReservationEntity savedReservationEntity = reservationDao.save(ReservationMapper.toEntity(reservation));
            return ReservationMapper.toDto(savedReservationEntity);
        }
        throw new ObjectNotFoundException(reservation.getId(),ReservationEntity.class);
    }


    @GetMapping("/api/Reservations/{id}")
    public Reservation getById(@PathVariable("id") int id){
        Optional<ReservationEntity> optionalReservationEntity = reservationDao.findById(id);
        return ReservationMapper.toDto(optionalReservationEntity.orElseThrow(()-> new ObjectNotFoundException(id,ReservationEntity.class)));
    }


    @GetMapping("/api/Reservations")
    public List<Reservation> getAll(){
        return reservationDao.findAll().stream().map(ReservationMapper::toDto).collect(Collectors.toList());
    }


    @DeleteMapping("/api/Reservations/{id}")
    public void delete(@PathVariable("id") int id){
        reservationDao.deleteById(id);
    }

}
