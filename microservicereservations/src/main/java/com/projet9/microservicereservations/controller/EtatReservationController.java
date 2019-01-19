package com.projet9.microservicereservations.controller;

import com.projet9.dataexchange.beans.EtatReservation;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microservicereservations.dao.EtatReservationDao;
import com.projet9.microservicereservations.entities.EtatReservationEntity;
import com.projet9.microservicereservations.mapper.EtatReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EtatReservationController {

    @Autowired
    EtatReservationDao etatReservationDao;


    @PostMapping("/api/EtatReservations")
    public EtatReservation create(@RequestBody EtatReservation etatReservation){
        EtatReservationEntity savedReservationEntity = etatReservationDao.save(EtatReservationMapper.toEntity(etatReservation));
        // retourne l'entité avec le nouvel id crée
        return EtatReservationMapper.toDto(savedReservationEntity);
    }


    @PutMapping("/api/EtatReservations")
    public EtatReservation update(@RequestBody EtatReservation etatReservation){
        if(etatReservationDao.existsById(etatReservation.getNumEtat())){
            EtatReservationEntity savedEtatReservationEntity = etatReservationDao.save(EtatReservationMapper.toEntity(etatReservation));
            return EtatReservationMapper.toDto(savedEtatReservationEntity);
        }
        throw new ObjectNotFoundException(etatReservation.getNumEtat(),EtatReservation.class);
    }


    @GetMapping("/api/EtatReservations/{id}")
    public EtatReservation getById(@PathVariable("id") int id){
        Optional<EtatReservationEntity> optionalEtatReservationEntity = etatReservationDao.findById(id);
        return EtatReservationMapper.toDto(optionalEtatReservationEntity.orElseThrow(()-> new ObjectNotFoundException(id,EtatReservationEntity.class)));
    }


    @GetMapping("/api/EtatReservations")
    public List<EtatReservation> getAll(){
        return etatReservationDao.findAll().stream().map(EtatReservationMapper::toDto).collect(Collectors.toList());
    }


    @DeleteMapping("/api/EtatReservations/{id}")
    public void delete(@PathVariable("id") int id){
        etatReservationDao.deleteById(id);
    }
}
