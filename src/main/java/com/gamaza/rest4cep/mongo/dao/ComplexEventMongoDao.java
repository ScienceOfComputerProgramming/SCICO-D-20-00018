package com.gamaza.rest4cep.mongo.dao;

import com.gamaza.rest4cep.mongo.model.ComplexEventDetectedMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Complex Events Detected Data Access Object
 */
@Repository
public interface ComplexEventMongoDao extends MongoRepository<ComplexEventDetectedMongo, String> {

    /**
     * Get all Complex Events Detected in database ordered by date
     */
    List<ComplexEventDetectedMongo> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Complex Events Detected in database
     */
    List<ComplexEventDetectedMongo> findTop5ByOrderByCreatedDateDesc();

}
