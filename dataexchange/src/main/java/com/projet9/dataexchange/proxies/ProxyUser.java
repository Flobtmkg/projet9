package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.FeignConfig;
import com.projet9.dataexchange.beans.User;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "zuulapigateway", configuration = FeignConfig.class)
@RibbonClient(name = "microserviceusers")
public interface ProxyUser {

    @PostMapping(value = "microserviceusers/api/Users")
    User create(@RequestBody User user);

    @PutMapping(value = "microserviceusers/api/Users")
    User update(@RequestBody User user);

    @GetMapping(value = "microserviceusers/api/Users")
    List<User> getAll();

    @GetMapping(value = "microserviceusers/api/Users/{id}")
    User getById(@PathVariable("id") int id);

    @DeleteMapping(value = "microserviceusers/api/Users/{id}")
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "microserviceusers/api/Users/autentificationById/{id}/{mdp}")
    boolean isAutentificationCorrectById(@PathVariable("id") int id, @PathVariable("mdp") String mdp);

    @GetMapping(value = "microserviceusers/api/Users/autentification/{email}/{mdp}")
    User autentification(@PathVariable("email") String email, @PathVariable("mdp") String hashMdp);

    @GetMapping(path = "microserviceusers/api/Users/Image/{id}")
    byte[] getImageById(@PathVariable("id") int id);
}
