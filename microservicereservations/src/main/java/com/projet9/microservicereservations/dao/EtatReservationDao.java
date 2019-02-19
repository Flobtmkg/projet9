package com.projet9.microservicereservations.dao;

import com.projet9.microservicereservations.entities.EtatReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtatReservationDao extends JpaRepository<EtatReservationEntity, Integer> {

    Optional<EtatReservationEntity> findByCode(String code);
}
