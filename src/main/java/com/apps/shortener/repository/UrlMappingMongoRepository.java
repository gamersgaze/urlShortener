package com.apps.shortener.repository;

import com.apps.shortener.documents.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlMappingMongoRepository extends MongoRepository<UrlMapping,String> {

}
