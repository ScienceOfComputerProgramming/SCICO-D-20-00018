package com.gamaza.rest4cep.mysql.service;

import com.gamaza.rest4cep.mysql.dto.EventTypeDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePostDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePutDto;
import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;

import java.util.List;

/**
 * Event Type Service Interface
 */
public interface EventTypeService {

    /**
     * Insert a new Event Type in database
     */
    EventTypeWithListDto create(EventTypePostDto eventTypePostDto);

    /**
     * Get all Event Types in database
     */
    List<EventTypeDto> readAll();

    /**
     * Get all enabled/disabled Event Types in database
     */
    List<EventTypeDto> readAllByIsEnabled(boolean status);

    /**
     * Search and return one Event Type by id
     */
    EventTypeWithListDto readOneById(Integer id);

    /**
     * Search and return one Event Type by name
     */
    EventTypeWithListDto readOneByName(String name);

    /**
     * Search and return one Event Type by channel id
     */
    EventTypeWithListDto readOneByChannelId(Integer channelId);

    /**
     * Modify one Event Type in database
     */
    void update(Integer id, EventTypePutDto eventTypePutDto);

    /**
     * Enable/Disable one Event Type
     */
    void updateStatus(Integer id, boolean status);

    /**
     * Delete one Event Type in database
     */
    void delete(Integer id);

}
