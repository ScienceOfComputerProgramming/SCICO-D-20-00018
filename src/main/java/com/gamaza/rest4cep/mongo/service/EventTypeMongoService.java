package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventTypeMongoPostDto;

import java.util.List;

/**
 * Event Type Service
 */
public interface EventTypeMongoService {

    /**
     * Insert a new Event Type in database
     */
    EventTypeMongoDto create(EventTypeMongoPostDto eventTypePostDto);

    /**
     * Get all Event Types in database
     */
    List<EventTypeMongoDto> readAll();

    /**
     * Search and return one Event Type in database by id
     */
    EventTypeMongoDto readOneById(String id);

    /**
     * Get last 5 Event Types in database
     */
    List<EventTypeMongoDto> readLast5();

    /**
     * Delete all Event Types in database
     */
    void deleteAll();

    /**
     * Delete one Event Type in database
     */
    void deleteOne(String id);

}
