package com.ian.blog.controller;

import com.ian.blog.dao.BlogRepository;
import com.ian.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    public Blog save(@RequestBody Blog blog, Model model){
        if (blog.getCreateAt() != null){
            blog.setYear(blog.getCreateAt().getYear());
        }
        return br.save(blog);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Blog blog){
        br.delete(blog);
    }


}
