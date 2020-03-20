package com.gamaza.rest4cep.mongo.dao;

import com.gamaza.rest4cep.mongo.model.EventTypeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Event Type Data Access Object
 */
@Repository
public interface EventTypeMongoDao extends MongoRepository<EventTypeMongo, String> {

    /**
     * Get all Event Types in database ordered by date
     */
    List<EventTypeMongo> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Event Types in database
     */
    List<EventTypeMongo> findTop5ByOrderByCreatedDateDesc();

}
