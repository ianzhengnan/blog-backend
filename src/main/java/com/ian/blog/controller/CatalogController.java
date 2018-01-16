package com.ian.blog.controller;

import com.ian.blog.dao.CatalogRepository;
import com.ian.blog.domain.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/catalogs")
public class CatalogController {

    @Autowired
    private CatalogRepository cr;

    @GetMapping
    public List<Catalog> getAllCatalogs(){
        return cr.findAll();
    }

    @PostMapping("/add")
    public Catalog add(@RequestBody Catalog catalog){
        catalog.setCreateAt(LocalDateTime.now());
        return cr.insert(catalog);
    }

    @PostMapping("/update")
    public Catalog update(@RequestBody Catalog catalog){
        Catalog origin = cr.findById(catalog.getId()).get();
        if (catalog.getName() != null && !catalog.getName().equals("")){
            origin.setName(catalog.getName());
        }
        if (catalog.getDescription() != null && !catalog.getDescription().equals("")){
            origin.setDescription(catalog.getDescription());
        }
        return cr.save(origin);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        cr.deleteById(id);
    }
}
