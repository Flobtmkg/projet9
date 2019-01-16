package com.projet9.microservicereservation.dao;

import com.projet9.microservicereservation.entities.EtatReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatReservationDao extends JpaRepository<EtatReservationEntity, Integer> {

}
