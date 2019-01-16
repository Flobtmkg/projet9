package com.projet9.microserviceaventures.mapper;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.microserviceaventures.dao.CategorieDao;
import com.projet9.microserviceaventures.entities.AventureEntity;
import com.projet9.microserviceaventures.entities.CategorieEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class AventureMapper {

    @Autowired
    static CategorieDao categorieDao;

    public static Aventure toDto(AventureEntity aventureEntity) {
        return new Aventure(
                aventureEntity.getId(),
                aventureEntity.getNom(),
                aventureEntity.getPrix(),
                aventureEntity.getDateDebut(),
                aventureEntity.getDateFin(),
                aventureEntity.getDescription(),
                aventureEntity.getIdCategorie(),
                aventureEntity.getImage(),
                CategorieMapper.toDto(aventureEntity.getCategorieEntity())
        );
    }

    public static  AventureEntity toEntity(Aventure aventure) {
            return new AventureEntity(
                    aventure.getId(),
                    aventure.getNom(),
                    aventure.getPrix(),
                    aventure.getDateDebut(),
                    aventure.getDateFin(),
                    aventure.getDescription(),
                    aventure.getIdCategorie(),
                    aventure.getImage(),
                    CategorieMapper.toEntity(aventure.getCategorie())
            );
    }
}