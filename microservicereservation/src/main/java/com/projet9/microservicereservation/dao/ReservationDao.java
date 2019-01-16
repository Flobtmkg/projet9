package com.projet9.microservicereservation.dao;

import com.projet9.microservicereservation.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends JpaRepository<ReservationEntity, Integer> {

}
