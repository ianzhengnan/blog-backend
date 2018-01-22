package com.ian.blog.dao;

import com.ian.blog.domain.Blog;
import com.ian.blog.domain.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String>{

    @Query("{'catalog': { 'id': ?0 }}")
    List<Blog> findByCatalogId(String catalogId);

}
