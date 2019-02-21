package com.projet9.microserviceaventures.dao;


import com.projet9.microserviceaventures.entities.AventureEntity;
import com.projet9.microserviceaventures.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AventureDao extends JpaRepository<AventureEntity, Integer>, JpaSpecificationExecutor<AventureEntity> {

    List<AventureEntity> findAventureEntitiesByCategorieEntity(CategorieEntity categorie);

}
