package com.ian.blog.dao;

import com.ian.blog.domain.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface BlogRepository extends MongoRepository<Blog, String>{

//    @Query("{'catalog._id': ?0 }")
    @CountQuery("{ 'catalog._id' : ?0 }")
    int countBlogsByCatalogId(ObjectId objectId);

    @Query("{ 'catalog._id' : ?0 }")
    Page<Blog> findAllByCatalogId(ObjectId objectId, Pageable pageable);
}
