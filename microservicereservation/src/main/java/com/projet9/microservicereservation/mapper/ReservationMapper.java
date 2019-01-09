package com.projet9.microservicereservation.mapper;

import com.projet9.dataexchange.beans.Reservation;
import com.projet9.microservicereservation.entities.ReservationEntity;

public class ReservationMapper {

    public static Reservation toDto(ReservationEntity reservationEntity) {
        return new Reservation(
                reservationEntity.getId(),
                reservationEntity.getIdAventure(),
                reservationEntity.getIdUser(),
                reservationEntity.getNumEtat(),
                reservationEntity.getDateReservation(),
                reservationEntity.getTimestampCommentaireReservation(),
                reservationEntity.getCommentaireReservation(),
                EtatReservationMapper.toDto(reservationEntity.getEtatReservationEntity())
        );
    }

    public static ReservationEntity toEntity(Reservation reservation) {
        return new ReservationEntity(
                reservation.getId(),
                reservation.getIdAventure(),
                reservation.getIdUser(),
                reservation.getNumEtat(),
                reservation.getDateReservation(),
                reservation.getTimestampCommentaireReservation(),
                reservation.getCommentaireReservation(),
                EtatReservationMapper.toEntity(reservation.getEtatReservation())
        );
    }
}
