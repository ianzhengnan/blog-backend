package com.ian.blog.dao;

import com.ian.blog.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBlogId(String blogId, Sort sort);
}
