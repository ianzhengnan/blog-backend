package com.ian.blog.controller;

import com.ian.blog.dao.BlogRepository;
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

    @Autowired
    private BlogRepository br;

    @GetMapping
    public List<Catalog> getAllCatalogs(){
        return getAllCats();
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

    private List<Catalog> getAllCats(){

        List<Catalog> catalogs = cr.findAll();
        for (Catalog catalog :
                catalogs) {
            catalog.setArticleCount(br.findByCatalogId(catalog.getId()).size());
        }
        return catalogs;
    }
}
