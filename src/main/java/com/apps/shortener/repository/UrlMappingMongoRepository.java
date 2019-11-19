package com.apps.shortener.repository;

import com.apps.shortener.documents.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * because this repository uses spring mongo repository, it does not require to implement the method.
 */
@Repository
public interface UrlMappingMongoRepository extends MongoRepository<UrlMapping,String> {

}
