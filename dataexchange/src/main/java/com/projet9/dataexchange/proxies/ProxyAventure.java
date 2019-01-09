package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.beans.Aventure;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microserviceaventures", url = "localhost:8081")
public interface ProxyAventure {

    @GetMapping("/api/Aventures/{id}")
    Aventure GetById(@PathVariable("id") int id);

}
