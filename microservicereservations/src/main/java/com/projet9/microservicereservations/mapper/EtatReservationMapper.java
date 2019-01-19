package com.projet9.microservicereservations.mapper;

import com.projet9.dataexchange.beans.EtatReservation;
import com.projet9.microservicereservations.entities.EtatReservationEntity;

public class EtatReservationMapper {

    public static EtatReservation toDto(EtatReservationEntity etatReservationEntity) {
        return new EtatReservation(etatReservationEntity.getNumEtat(), etatReservationEntity.getEtat());
    }

    public static EtatReservationEntity toEntity(EtatReservation etatReservation) {
        return new EtatReservationEntity(etatReservation.getNumEtat(), etatReservation.getEtat());
    }
}
