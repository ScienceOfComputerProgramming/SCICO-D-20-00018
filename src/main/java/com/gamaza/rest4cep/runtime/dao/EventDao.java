package com.gamaza.rest4cep.runtime.dao;

import com.gamaza.rest4cep.runtime.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Event Data Access Object
 */
@Repository
public interface EventDao extends MongoRepository<Event, String> {

    /**
     * Get all Events in database ordered by date
     */
    List<Event> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Events in database
     */
    List<Event> findTop5ByOrderByCreatedDateDesc();

}
