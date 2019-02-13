package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.beans.Categorie;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuulapigateway")
@RibbonClient(name = "microserviceaventures")
public interface ProxyAventure {

    // Accès aux aventures

    @PostMapping("microserviceaventures/api/Aventures")
    Aventure createAventure(@RequestBody Aventure aventure);

    @PutMapping("microserviceaventures/api/Aventures")
    Aventure updateAventure(@RequestBody Aventure aventure);

    @GetMapping("microserviceaventures/api/Aventures")
    List<Aventure> getAllAventures();

    @GetMapping("microserviceaventures/api/Aventures/{id}")
    Aventure getAventureById(@PathVariable("id") int id);

    @GetMapping("microserviceaventures/api/Aventures/Categories/{id}")
    List<Aventure> getAventuresByCategorie(@PathVariable("id") int id);

    @DeleteMapping("microserviceaventures/api/Aventures/{id}")
    void deleteAventure(@PathVariable("id") int id);


    // Accès aux categories

    @PostMapping("microserviceaventures/api/Categories")
    Categorie createCategorie(@RequestBody Categorie categorie);

    @PutMapping("microserviceaventures/api/Categories")
    Categorie updateCategorie(@RequestBody Categorie categorie);

    @GetMapping("microserviceaventures/api/Categories")
    List<Categorie> getAllCategories();

    @GetMapping("microserviceaventures/api/Categories/{id}")
    Categorie getCategorieById(@PathVariable("id") int id);

    @DeleteMapping("microserviceaventures/api/Categories/{id}")
    void deleteCategorie(@PathVariable("id") int id);

}
