package com.gamaza.rest4cep.mongo.dao;

import com.gamaza.rest4cep.mongo.model.EventPatternMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Event Pattern Data Access Object
 */
@Repository
public interface EventPatternMongoDao extends MongoRepository<EventPatternMongo, String> {

    /**
     * Get all Event Patterns in database ordered by date
     */
    List<EventPatternMongo> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Event Patterns in database
     */
    List<EventPatternMongo> findTop5ByOrderByCreatedDateDesc();

}
