package com.ian.blog.controller;

import com.ian.blog.dao.CatalogRepository;
import com.ian.blog.domain.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/catalogs")
public class CatalogController {

    @Autowired
    private CatalogRepository cr;

    @GetMapping("/")
    public List<Catalog> getAllCatalogs(){
        return cr.findAll();
    }

    @PostMapping("/save")
    public Catalog save(@ModelAttribute Catalog catalog){
        return cr.save(catalog);
    }
}
