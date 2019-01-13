package com.projet9.microserviceaventures.controller;

import com.projet9.dataexchange.beans.Categorie;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microserviceaventures.dao.CategorieDao;
import com.projet9.microserviceaventures.entities.CategorieEntity;
import com.projet9.microserviceaventures.mapper.CategorieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CategorieController {

    @Autowired
    CategorieDao categorieDao;


    @PostMapping("/api/Categories")
    public Categorie create(@RequestBody Categorie categorie){
        CategorieEntity savedCategorieEntity = categorieDao.save(CategorieMapper.toEntity(categorie));
        // retourne l'entité avec le nouvel id crée
        return CategorieMapper.toDto(savedCategorieEntity);
    }


    @PutMapping("/api/Categories")
    public Categorie update(@RequestBody Categorie categorie){
        if(categorieDao.existsById(categorie.getId())){
            CategorieEntity savedCategorieEntity = categorieDao.save(CategorieMapper.toEntity(categorie));
            return CategorieMapper.toDto(savedCategorieEntity);
        }
        throw new ObjectNotFoundException(categorie.getId(),CategorieEntity.class);
    }


    @GetMapping("/api/Categories/{id}")
    public Categorie getById(@PathVariable("id") int id){
        Optional<CategorieEntity> optionalCategorieEntity = categorieDao.findById(id);
        return CategorieMapper.toDto(optionalCategorieEntity.orElseThrow(()-> new ObjectNotFoundException(id,CategorieEntity.class)));
    }


    @GetMapping("/api/Categories")
    public List<Categorie> getAll(){
        return categorieDao.findAll().stream().map(CategorieMapper::toDto).collect(Collectors.toList());
    }


    @DeleteMapping("/api/Categories/{id}")
    public void delete(@PathVariable("id") int id){
        categorieDao.deleteById(id);
    }
}
