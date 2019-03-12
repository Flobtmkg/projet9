package com.projet9.microserviceusers.controller;

import com.projet9.dataexchange.beans.User;
import com.projet9.dataexchange.exceptions.ObjectNotFoundException;
import com.projet9.dataexchange.proxies.ProxyReservation;
import com.projet9.microserviceusers.dao.UserDao;
import com.projet9.microserviceusers.entities.UserEntity;
import com.projet9.microserviceusers.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProxyReservation proxyReservation;


    @PostMapping(path = "/api/Users", produces = "application/json")
    public User create(@RequestBody User user) {
        List<UserEntity> sameEmailUsers = userDao.findByEmail(user.getEmail());
        if(sameEmailUsers.size()>0){
            // Un utilisateur possède utilise déjà cet email
            throw new ObjectNotFoundException(user.getId(),UserEntity.class);
        }else{
            UserEntity savedUserEntity =  userDao.save(UserMapper.toEntity(user));
            return UserMapper.toDto(savedUserEntity);
        }
    }

    @PutMapping(path = "/api/Users", produces = "application/json")
    public User update(@RequestBody User user) {
        if (userDao.existsById(user.getId())) {
            // L'image peut être nulle en entrée dans ce cas on ne modifie pas la valeur en base
            if(user.getImage()==null){
                // L'image déja existante doit être sétée sur le bean
                user.setImage(userDao.getOne(user.getId()).getImage());
            }
            userDao.save(UserMapper.toEntity(user));
            return user;
        }
        throw new ObjectNotFoundException(user.getId(),UserEntity.class);
    }

    @GetMapping(path = "/api/Users", produces = "application/json")
    public List<User> getAll() {
        return userDao.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/api/Users/{id}", produces = "application/json")
    public User getById(@PathVariable("id") int id)  {
        Optional<UserEntity> userEntity = userDao.findById(id);
        return UserMapper.toDto(userEntity.orElseThrow(() -> new ObjectNotFoundException(id, UserEntity.class)));
    }

    @GetMapping(path = "/api/Users/Image/{id}", produces = "application/json")
    public byte[] getImageById(@PathVariable("id") int id)  {
        Optional<UserEntity> userEntity = userDao.findById(id);
        return userEntity.orElseThrow(() -> new ObjectNotFoundException(id, UserEntity.class)).getImage();
    }

    @GetMapping(path = "/api/Users/autentificationById/{id}/{mdp}", produces = "application/json")
    public boolean isAutentificationCorrectById(@PathVariable("id") int id, @PathVariable("mdp") String hashMdp){
        User user;
        try{
            user = getById(id);
        }catch(ObjectNotFoundException e){
            return false;
        }

        if(user.getPassword().equals(hashMdp)){
            return true;
        }else{
            return false;
        }
    }

    @GetMapping(path = "/api/Users/autentification/{email}/{mdp}", produces = "application/json")
    public User autentification(@PathVariable("email") String email, @PathVariable("mdp") String hashMdp){
        Optional<UserEntity> OptionalUserEntity = userDao.findByEmailAndPassword(email,hashMdp);
        if(OptionalUserEntity.isPresent()){
            return UserMapper.toDto(OptionalUserEntity.get());
        }else{
            return null;
        }
    }

    @DeleteMapping(path = "/api/Users/{id}", produces = "application/json")
    public void delete(@PathVariable("id") int id) {
        proxyReservation.deleteByUserId(id);
        userDao.deleteById(id);
    }
}
