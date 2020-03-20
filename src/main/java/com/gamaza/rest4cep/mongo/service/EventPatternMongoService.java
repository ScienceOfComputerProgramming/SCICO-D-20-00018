package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dto.EventPatternMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventPatternMongoPostDto;

import java.util.List;

/**
 * Event Pattern Service
 */
public interface EventPatternMongoService {

    /**
     * Insert a new Event Pattern in database
     */
    EventPatternMongoDto create(EventPatternMongoPostDto eventPatternPostDto);

    /**
     * Get all Event Patterns in database
     */
    List<EventPatternMongoDto> readAll();

    /**
     * Search and return one Event Pattern in database by id
     */
    EventPatternMongoDto readOneById(String id);

    /**
     * Get Last 5 Event Patterns in database
     */
    List<EventPatternMongoDto> readLast5();

    /**
     * Delete all Event Patterns in database
     */
    void deleteAll();

    /**
     * Delete one Event Pattern in database
     */
    void deleteOne(String id);

}
