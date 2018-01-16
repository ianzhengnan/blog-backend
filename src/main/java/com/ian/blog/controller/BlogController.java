package com.ian.blog.controller;

import com.ian.blog.dao.BlogRepository;
import com.ian.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping(path = "/blogs")
public class BlogController {

    @Autowired
    private BlogRepository br;

    @GetMapping
    public Page<Blog> getAllBlogs(){
        return br.findAll(PageRequest.of(0, 20, new Sort(Sort.Direction.DESC, "createAt")));
    }

    @PostMapping("/save")
    public Blog save(@RequestBody Blog blog){
        blog.setCreateAt(LocalDateTime.now());
        return br.insert(blog);
    }

    @PostMapping("/update")
    public Blog update(@RequestBody Blog blog) throws Exception{
        Blog origin = br.findById(blog.getId()).get();
        if (origin != null){
            origin.setCatalog(blog.getCatalog());
            origin.setContent(blog.getContent());
            origin.setTitle(blog.getTitle());
            origin.setLastModifyAt(blog.getLastModifyAt());
        }else{
            throw new Exception("没有找到源文档");
        }
        return br.save(origin);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        br.deleteById(id);
    }


}
