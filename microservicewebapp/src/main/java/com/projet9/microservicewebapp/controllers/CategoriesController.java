package com.projet9.microservicewebapp.controllers;

import com.projet9.dataexchange.beans.Categorie;
import com.projet9.dataexchange.proxies.ProxyAventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    ProxyAventure proxyAventure;

    @GetMapping("/categories")
    public String toCategories(HttpServletRequest request) {
        List<Categorie> listCategorie = proxyAventure.getAllCategories();
        request.setAttribute("listCategorie", listCategorie);
        return "categories";
    }
}
