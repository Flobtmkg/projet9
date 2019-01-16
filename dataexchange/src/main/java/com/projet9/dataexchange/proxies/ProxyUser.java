package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.beans.User;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "microserviceusers", url = "localhost:8084")
public interface ProxyUser {

    @PostMapping(value = "/api/Users")
    User create(@RequestBody User user);

    @PutMapping(value = "/api/Users")
    User update(@RequestBody User user);

    @GetMapping(value = "/api/Users")
    List<User> getAll();

    @GetMapping(value = "api/Users/{id}")
    User getById(@PathVariable("id") int id);

    @DeleteMapping(value = "/api/Users/{id}")
    void delete(@PathVariable("id") int id);

}
