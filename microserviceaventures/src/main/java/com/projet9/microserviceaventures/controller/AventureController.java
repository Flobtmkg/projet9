package com.projet9.microserviceaventures.controller;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microserviceaventures.dao.AventureDao;
import com.projet9.microserviceaventures.entities.AventureEntity;
import com.projet9.microserviceaventures.mapper.AventureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AventureController {

    @Autowired
    AventureDao aventureDao;

    @GetMapping("/api/Aventures/{id}")
    public Aventure GetById(@PathVariable("id") int id) throws ObjectNotFoundException{
        Optional<AventureEntity> avEtity = aventureDao.findById(id);
        return AventureMapper.toDto(avEtity.orElseThrow(() -> new ObjectNotFoundException(id,AventureEntity.class)));
    }


}
