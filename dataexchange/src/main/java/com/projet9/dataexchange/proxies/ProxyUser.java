package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.beans.User;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "zuulapigateway")
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

}
