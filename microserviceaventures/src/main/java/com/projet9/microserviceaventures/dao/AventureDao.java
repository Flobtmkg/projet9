package com.projet9.microserviceaventures.dao;


import com.projet9.microserviceaventures.entities.AventureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureDao extends JpaRepository<AventureEntity, Integer>{


}
