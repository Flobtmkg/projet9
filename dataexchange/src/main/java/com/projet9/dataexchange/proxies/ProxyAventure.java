package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.beans.Aventure;
import com.projet9.dataexchange.beans.Categorie;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microserviceaventures")
@RibbonClient(name = "microserviceaventures")
public interface ProxyAventure {

    // Accès aux aventures

    @PostMapping("/api/Aventures")
    Aventure createAventure(@RequestBody Aventure aventure);

    @PutMapping("/api/Aventures")
    Aventure updateAventure(@RequestBody Aventure aventure);

    @GetMapping("/api/Aventures")
    List<Aventure> getAllAventures();

    @GetMapping("/api/Aventures/{id}")
    Aventure getAventureById(@PathVariable("id") int id);

    @DeleteMapping("/api/Aventures/{id}")
    void deleteAventure(@PathVariable("id") int id);


    // Accès aux categories

    @PostMapping("/api/Categories")
    Categorie createCategorie(@RequestBody Categorie categorie);

    @PutMapping("/api/Categories")
    Categorie updateCategorie(@RequestBody Categorie categorie);

    @GetMapping("/api/Categories")
    List<Categorie> getAllCategories();

    @GetMapping("/api/Categories/{id}")
    Categorie getCategorieById(@PathVariable("id") int id);

    @DeleteMapping("/api/Categories/{id}")
    void deleteCategorie(@PathVariable("id") int id);

}
