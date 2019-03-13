package com.projet9.microservicereservations.dao;

import com.projet9.microservicereservations.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<ReservationEntity, Integer> {

    List<ReservationEntity> findReservationEntitiesByIdUser(int idUser);

    List<ReservationEntity> findReservationEntitiesByIdAventure(int id);

    @Transactional
    void deleteReservationEntitiesByIdUser(int idUser);

    @Transactional
    void deleteReservationEntitiesByIdAventure(int idAventure);
}
