package com.gamaza.rest4cep.mysql.service;

import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;

import java.util.List;

/**
 * Event Types Service Interface
 */
public interface EventTypeService {

    /**
     * Insert a new Event Type in database
     * @param eventTypeWithListDto **eventTypeWithListDto**
     * @return eventType created
     */
    EventTypeWithListDto create(EventTypeWithListDto eventTypeWithListDto);

    /**
     * Get all Event Types in database
     * @return eventType list
     */
    List<EventTypeWithListDto> readAll();

    /**
     * Get all enabled/disabled Event Types in database
     * @param status **status**
     * @return eventType enabled/disabled list
     */
    List<EventTypeWithListDto> readAllByIsEnabled(boolean status);

    /**
     * Search and return one Event Type by id
     * @param id **id**
     * @return eventType found
     */
    EventTypeWithListDto readOneById(Integer id);

    /**
     * Search and return one Event Type by name
     * @param name **name**
     * @return eventType found
     */
    EventTypeWithListDto readOneByName(String name);

    /**
     * Search and return one Event Type by channel id
     * @param channelId **channelId**
     * @return eventType found
     */
    EventTypeWithListDto readOneByChannelId(Integer channelId);

    /**
     * Modify one Event Type in database
     * @param id **id**
     * @param eventTypeWithListDto **eventTypeWithListDto**
     */
    void update(Integer id, EventTypeWithListDto eventTypeWithListDto);

    /**
     * Enable/Disable one Event Type
     * @param id **id**
     * @param status **status**
     */
    void updateStatus(Integer id, boolean status);

    /**
     * Delete one Event Type in database
     * @param id **id**
     */
    void delete(Integer id);

}
