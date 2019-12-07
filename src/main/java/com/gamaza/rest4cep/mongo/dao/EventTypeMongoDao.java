package com.gamaza.rest4cep.mongo.dao;

import com.gamaza.rest4cep.mongo.model.EventTypeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Event Types (Mongo version) DAO
 */
@Repository
public interface EventTypeMongoDao extends MongoRepository<EventTypeMongo, String> {

    /**
     * Get all Event Types in database ordered by date
     * @return eventTypes list
     */
    List<EventTypeMongo> findAllByOrderByInsertionDateDesc();

    /**
     * Get Last 5 Event Types in database
     * @return Last 5 eventTypes list
     */
    List<EventTypeMongo> findTop5ByOrderByInsertionDateDesc();

}
