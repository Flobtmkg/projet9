package com.projet9.microserviceaventures.mapper;

import com.projet9.dataexchange.beans.Categorie;
import com.projet9.microserviceaventures.entities.CategorieEntity;

public class CategorieMapper {

    public static Categorie toDto(CategorieEntity categorieEntity) {
        return new Categorie(categorieEntity.getId(), categorieEntity.getNom(), categorieEntity.getDescription());
    }

    public static  CategorieEntity toEntity(Categorie categorie) {
        return new CategorieEntity(categorie.getId(), categorie.getNom(), categorie.getDescription());
    }
}
