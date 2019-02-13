package com.projet9.microservicereservations.dao;

import com.projet9.microservicereservations.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<ReservationEntity, Integer> {
    List<ReservationEntity> findReservationEntitiesByIdAventure(int id);
}
