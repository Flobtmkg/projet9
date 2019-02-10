package com.projet9.microservicereservations.mapper;

import com.projet9.dataexchange.beans.Reservation;
import com.projet9.microservicereservations.entities.ReservationEntity;

public class ReservationMapper {

    public static Reservation toDto(ReservationEntity reservationEntity) {
        return new Reservation(
                reservationEntity.getId(),
                reservationEntity.getIdAventure(),
                reservationEntity.getIdUser(),
                reservationEntity.getEtatReservationEntity().getNumEtat(),
                reservationEntity.getDateReservation(),
                reservationEntity.getTimestampCommentaireReservation(),
                reservationEntity.getCommentaireReservation(),
                reservationEntity.isReservationPrecedente(),
                EtatReservationMapper.toDto(reservationEntity.getEtatReservationEntity())
        );
    }

    public static ReservationEntity toEntity(Reservation reservation) {
        return new ReservationEntity(
                reservation.getId(),
                reservation.getIdAventure(),
                reservation.getIdUser(),
                reservation.getDateReservation(),
                reservation.getTimestampCommentaireReservation(),
                reservation.getCommentaireReservation(),
                reservation.isReservationPrecedente(),
                EtatReservationMapper.toEntity(reservation.getEtatReservation())
        );
    }
}
