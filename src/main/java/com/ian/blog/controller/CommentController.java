package com.ian.blog.controller;

import com.ian.blog.dao.CommentRepository;
import com.ian.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private CommentRepository cmr;

    @GetMapping("/{blogId}")
    public List<Comment> list(@PathVariable String blogId){
        List<Comment> comments = cmr.findByBlogId(blogId);
        // find related blog
        /*Blog blog = br.findById(blogId).get();
        if (blog != null){
            blog.setComments(comments.getContent());
            br.save(blog);
        }*/
        return comments;
    }

    @PostMapping("/add")
    public Comment add(@RequestBody Comment comment){
        comment.setCreateAt(LocalDateTime.now());
        return cmr.insert(comment);
    }

    @PostMapping("/update")
    public Comment update(@RequestBody Comment comment){
        Comment origin = cmr.findById(comment.getId()).get();
        if (comment.getContent() != null && !comment.getContent().equals("")){
            origin.setContent(comment.getContent());
        }
        if (comment.getUsername() != null && !comment.getUsername().equals("")){
            origin.setUsername(comment.getUsername());
        }
        if (comment.getSite() != null && !comment.getSite().equals("")){
            origin.setSite(comment.getSite());
        }
        if (comment.getEmail() != null && !comment.getEmail().equals("")){
            origin.setEmail(comment.getEmail());
        }
        return cmr.save(origin);
    }
}
