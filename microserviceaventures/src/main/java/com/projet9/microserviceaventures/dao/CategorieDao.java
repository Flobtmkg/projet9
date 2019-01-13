package com.projet9.microserviceaventures.dao;

import com.projet9.microserviceaventures.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieDao extends JpaRepository<CategorieEntity, Integer> {

}
