package com.ian.blog.dao;

import com.ian.blog.domain.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<Catalog, Long>{


}
