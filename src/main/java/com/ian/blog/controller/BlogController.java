package com.ian.blog.controller;

import com.ian.blog.dao.BlogRepository;
import com.ian.blog.dao.CatalogRepository;
import com.ian.blog.domain.Blog;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(path = "/blogs")
public class BlogController {

    @Autowired
    private BlogRepository br;

//    @Autowired
//    private CatalogRepository catalogRepository;

    @GetMapping
    public Page<Blog> getAllBlogs(@RequestParam("catalogId") String catalogId){
        if (catalogId != null && !catalogId.equals("")) {
            return br.findAllByCatalogId(new ObjectId(catalogId),
                    PageRequest.of(0, 10000, new Sort(Sort.Direction.DESC, "lastModifyAt")));
        }
        return br.findAll(PageRequest.of(0, 20, new Sort(Sort.Direction.DESC, "lastModifyAt")));
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable String id){
        return br.findById(id).get();
    }

    @PostMapping("/save")
    public Blog save(@RequestBody Blog blog){
        blog.setCreateAt(LocalDateTime.now());
        return br.insert(blog);
    }

    @PostMapping("/update")
    public Blog update(@RequestBody Blog blog) throws Exception{
        Blog origin = br.findById(blog.getId()).get();
        if (blog.getContent() != null && !blog.getContent().equals("")){
            origin.setContent(blog.getContent());
        }
        if (blog.getCatalog() != null && blog.getCatalog().getId() != null && !blog.getCatalog().getId().equals("")){
            origin.setCatalog(blog.getCatalog());
        }
        if (blog.getTitle() != null && !blog.getTitle().equals("")){
            origin.setTitle(blog.getTitle());
        }
        if (blog.getUri() != null && !blog.getUri().equals("")){
            origin.setUri(blog.getUri());
        }
        if (blog.getVisitCount() != null && blog.getVisitCount() != 0){
            origin.setVisitCount(blog.getVisitCount());
        }
        if (blog.getSummary() != null && !blog.getSummary().equals("")){
            origin.setSummary(blog.getSummary());
        }
        origin.setLastModifyAt(LocalDateTime.now());
        return br.save(origin);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        br.deleteById(id);
    }


}
