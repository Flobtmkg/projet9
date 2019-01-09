package com.projet9.microserviceusers.controller;

import com.projet9.dataexchange.beans.User;
import com.projet9.microserviceusers.dao.UserDao;
import com.projet9.microserviceusers.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/Users")
    public List<User> listeUsers() {
        return userDao.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
