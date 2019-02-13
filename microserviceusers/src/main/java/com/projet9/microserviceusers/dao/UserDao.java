package com.projet9.microserviceusers.dao;

import com.projet9.microserviceusers.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    List<UserEntity> findByEmail(String email);

}
