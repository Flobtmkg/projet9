package com.projet9.microserviceaventures.controller;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microserviceaventures.dao.AventureDao;
import com.projet9.microserviceaventures.dao.CategorieDao;
import com.projet9.microserviceaventures.entities.AventureEntity;
import com.projet9.microserviceaventures.mapper.AventureMapper;
import com.projet9.microserviceaventures.mapper.CategorieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AventureController {

    @Autowired
    CategorieDao categorieDao;
    @Autowired
    AventureDao aventureDao;


    @PostMapping("/api/Aventures")
    public Aventure create(@RequestBody Aventure aventure){
        // Lors de la création, idCatégorie peut être renseigné sans que le bean Catégorie correspondant ne soit chargé
        if(aventure.getCategorie()==null){
            aventure.setCategorie(CategorieMapper.toDto(categorieDao.getOne(aventure.getIdCategorie())));
        }
        AventureEntity savedAventureEntity = aventureDao.save(AventureMapper.toEntity(aventure));
        // retourne l'entité avec le nouvel id crée
        return AventureMapper.toDto(savedAventureEntity);
    }

    @PutMapping("/api/Aventures")
    public Aventure update(@RequestBody Aventure aventure){
        if(aventureDao.existsById(aventure.getId())){
            AventureEntity savedAventureEntity = aventureDao.save(AventureMapper.toEntity(aventure));
            return AventureMapper.toDto(savedAventureEntity);
        }
        throw new ObjectNotFoundException(aventure.getId(),AventureEntity.class);
    }

    @GetMapping("/api/Aventures")
    public List<Aventure> getAll(){
        return aventureDao.findAll().stream().map(AventureMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/Aventures/{id}")
    public Aventure getById(@PathVariable("id") int id){
        Optional<AventureEntity> optionalAventureEntity = aventureDao.findById(id);
        return AventureMapper.toDto(optionalAventureEntity.orElseThrow(()-> new ObjectNotFoundException(id, AventureEntity.class)));
    }

    @DeleteMapping("/api/Aventures/{id}")
    public void delete(@PathVariable("id") int id){
        aventureDao.deleteById(id);
    }

}
