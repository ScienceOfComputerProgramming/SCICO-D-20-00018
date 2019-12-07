package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;

import java.util.List;

/**
 * Event Types (Mongo version) Service
 */
public interface EventTypeMongoService {

    /**
     * Insert a new Event Type in database
     * @param eventTypeMongoDto **eventTypeMongoDto**
     * @return eventType created
     */
    EventTypeMongoDto create(EventTypeMongoDto eventTypeMongoDto);

    /**
     * Get all Event Types in database
     * @return eventType list
     */
    List<EventTypeMongoDto> readAll();

    /**
     * Search and return one Event Type in database by id
     * @param id **id**
     * @return eventType found
     */
    EventTypeMongoDto readOneById(String id);

    /**
     * Get last 5 Event Types in database
     * @return Last 5 eventType list
     */
    List<EventTypeMongoDto> readLast5();

    /**
     * Delete all Event Types in database
     */
    void deleteAll();

    /**
     * Delete one Event Type in database
     * @param id **id**
     */
    void deleteOne(String id);

}
