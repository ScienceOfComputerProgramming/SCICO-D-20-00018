package com.gamaza.rest4cep.design.service;

import com.gamaza.rest4cep.design.dto.eventtype.EventTypeDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypePostDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypePutDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypeWithListDto;

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
     * Get all readyToDeploy/notReadyToDeploy Event Types in database
     */
    List<EventTypeDto> readAllByIsReadyToDeploy(boolean status);

    /**
     * Get all deployed/undeployed Event Types in database
     */
    List<EventTypeDto> readAllByIsDeployed(boolean status);

    /**
     * Search and return one Event Type by id
     */
    EventTypeWithListDto readOneById(Integer id);

    /**
     * Search and return one Event Type by name
     */
    EventTypeWithListDto readOneByName(String name);

    /**
     * Modify one Event Type in database
     */
    void update(Integer id, EventTypePutDto eventTypePutDto);

    /**
     * Update the status of one Event Type in database (readyToDeploy variable)
     */
    void updateStatus(Integer id, boolean status);

    /**
     * Update the status of one Event Type in database (deployed variable)
     */
    void updateDeployingStatus(Integer id, boolean status);

    /**
     * Delete one Event Type in database
     */
    void delete(Integer id);

}
