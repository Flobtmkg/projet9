package com.projet9.microserviceaventures.controller;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microserviceaventures.dao.AventureDao;
import com.projet9.microserviceaventures.dao.CategorieDao;
import com.projet9.microserviceaventures.entities.AventureEntity;
import com.projet9.microserviceaventures.mapper.AventureMapper;
import com.projet9.microserviceaventures.mapper.CategorieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AventureController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategorieDao categorieDao;
    @Autowired
    AventureDao aventureDao;


    @PostMapping(path = "/api/Aventures", produces = "application/json")
    public Aventure create(@RequestBody Aventure aventure){
        // Lors de la création, idCatégorie peut être renseigné sans que le bean Catégorie correspondant ne soit chargé
        if(aventure.getCategorie()==null){
            aventure.setCategorie(CategorieMapper.toDto(categorieDao.getOne(aventure.getIdCategorie())));
        }
        AventureEntity savedAventureEntity = aventureDao.save(AventureMapper.toEntity(aventure));
        // retourne l'entité avec le nouvel id crée
        return AventureMapper.toDto(savedAventureEntity);
    }

    @PutMapping(path = "/api/Aventures", produces = "application/json")
    public Aventure update(@RequestBody Aventure aventure){
        if(aventureDao.existsById(aventure.getId())){
            AventureEntity savedAventureEntity = aventureDao.save(AventureMapper.toEntity(aventure));
            return AventureMapper.toDto(savedAventureEntity);
        }
        throw new ObjectNotFoundException(aventure.getId(),AventureEntity.class);
    }

    @GetMapping(path = "/api/Aventures", produces = "application/json")
    public List<Aventure> getAll(){
        return aventureDao.findAll().stream().map(AventureMapper::toDto).collect(Collectors.toList());

    }

    @GetMapping(path = "/api/Aventures/{id}", produces = "application/json")
    public Aventure getById(@PathVariable("id") int id){
        Optional<AventureEntity> optionalAventureEntity = aventureDao.findById(id);
        return AventureMapper.toDto(optionalAventureEntity.orElseThrow(()-> new ObjectNotFoundException(id, AventureEntity.class)));
    }

    @DeleteMapping(path = "/api/Aventures/{id}", produces = "application/json")
    public void delete(@PathVariable("id") int id){
        aventureDao.deleteById(id);
    }

}
