package com.ian.blog.dao;

import com.ian.blog.domain.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String>{

//    Blog findByTitle(String id);
}
