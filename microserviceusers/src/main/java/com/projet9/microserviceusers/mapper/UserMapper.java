package com.projet9.microserviceusers.mapper;

import com.projet9.dataexchange.beans.User;
import com.projet9.microserviceusers.entities.UserEntity;

public class UserMapper {

    public static User toDto(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getNom(), userEntity.getPrenom(), userEntity.getDateNaissance(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getImage());
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(user.getId(), user.getNom(), user.getPrenom(), user.getDateNaissance(), user.getEmail(), user.getPassword(), user.getImage());
    }
}
