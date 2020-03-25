package com.gamaza.rest4cep.runtime.service;

import com.gamaza.rest4cep.runtime.dto.event.EventDto;
import com.gamaza.rest4cep.runtime.dto.event.EventPostDto;

import java.util.List;

/**
 * Event Service
 */
public interface EventService {

    /**
     * Insert a new Event in database
     */
    EventDto create(EventPostDto eventPostDto);

    /**
     * Get all Events in database
     */
    List<EventDto> readAll();

    /**
     * Search and return one Event in database by id
     */
    EventDto readOneById(String id);

    /**
     * Get last 5 Events in database
     */
    List<EventDto> readLast5();

    /**
     * Delete all Events in database
     */
    void deleteAll();

    /**
     * Delete one Event in database
     */
    void deleteOne(String id);

}
