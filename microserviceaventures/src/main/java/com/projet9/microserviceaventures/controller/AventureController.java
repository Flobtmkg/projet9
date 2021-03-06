package com.projet9.microserviceaventures.controller;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.microserviceaventures.dao.AventureDao;
import com.projet9.microserviceaventures.dao.CategorieDao;
import com.projet9.microserviceaventures.entities.AventureEntity;
import com.projet9.microserviceaventures.mapper.AventureMapper;
import com.projet9.microserviceaventures.mapper.CategorieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class AventureController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategorieDao categorieDao;
    @Autowired
    AventureDao aventureDao;
    @Autowired
    ProxyReservation proxyReservation;


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
            // L'image peut être nulle en entrée dans ce cas on ne modifie pas la valeur en base
            if(aventure.getImage()==null){
                // L'image déja existante doit être sétée sur le bean
                aventure.setImage(aventureDao.getOne(aventure.getId()).getImage());
            }
            AventureEntity savedAventureEntity = aventureDao.save(AventureMapper.toEntity(aventure));
            return AventureMapper.toDto(savedAventureEntity);
        }
        throw new ObjectNotFoundException(aventure.getId(),AventureEntity.class);
    }


    @GetMapping(path = "/api/Aventures/Image/{id}", produces = "application/json")
    public byte[] getImageById(@PathVariable("id") int id)  {
        Optional<AventureEntity> aventureEntity = aventureDao.findById(id);
        return aventureEntity.orElseThrow(() -> new ObjectNotFoundException(id, AventureEntity.class)).getImage();
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

    @GetMapping(path = "/api/Aventures/Categories/{id}", produces = "application/json")
    public List<Aventure> getByCategorie(@PathVariable("id") int id) {
        return aventureDao.findAventureEntitiesByCategorieEntity(categorieDao.findById(id).get()).stream().map(AventureMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping(path = "/api/Aventures/Recherche/", produces = "application/json")
    public List<Aventure> getByRechercheMotsCles(@RequestBody List<String> motsCles) {
        return rechercheByMotsCles(motsCles).stream().map(AventureMapper::toDto).collect(Collectors.toList());
    }


    // Méthode private de requète critéria en spring data pour rechercher par mots clés
    private List<AventureEntity> rechercheByMotsCles(List<String> motsCles){
        return aventureDao.findAll(new Specification<AventureEntity>() {
            @Override
            public Predicate toPredicate(Root<AventureEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(motsCles!=null) {
                    for (String mot:motsCles) {
                        predicates.add(criteriaBuilder.or(
                                criteriaBuilder.like(root.get("nom"), "%" + mot + "%"),
                                criteriaBuilder.like(root.get("description"), "%" + mot + "%")
                        ));
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }



    @DeleteMapping(path = "/api/Aventures/{id}", produces = "application/json")
    public void delete(@PathVariable("id") int id){
        proxyReservation.deleteByAventureId(id);
        aventureDao.deleteById(id);
    }

}
