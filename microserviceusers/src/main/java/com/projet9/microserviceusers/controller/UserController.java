package com.projet9.microserviceusers.controller;

import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.microserviceusers.dao.UserDao;
import com.projet9.microserviceusers.entities.UserEntity;
import com.projet9.microserviceusers.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;


    @PostMapping(value = "/api/Users")
    public User create(@RequestBody User user) {
        UserEntity savedUserEntity =  userDao.save(UserMapper.toEntity(user));
        return UserMapper.toDto(savedUserEntity);
    }

    @PutMapping(value = "/api/Users")
    public User update(@RequestBody User user) {
        if (userDao.existsById(user.getId())) {
            userDao.save(UserMapper.toEntity(user));
            return user;
        }
        throw new ObjectNotFoundException(user.getId(),UserEntity.class);
    }

    @GetMapping(value = "/api/Users")
    public List<User> getAll() {
        return userDao.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/Users/{id}")
    public User getById(@PathVariable("id") int id)  {
        Optional<UserEntity> userEntity = userDao.findById(id);
        return UserMapper.toDto(userEntity.orElseThrow(() -> new ObjectNotFoundException(id, UserEntity.class)));
    }

    @DeleteMapping(value = "/api/Users/{id}")
    public void delete(@PathVariable("id") int id) {
        userDao.deleteById(id);
    }
}
