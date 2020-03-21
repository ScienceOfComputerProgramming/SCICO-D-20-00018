package com.gamaza.rest4cep.mysql.service;

import com.gamaza.rest4cep.mysql.dto.EventPatternDto;
import com.gamaza.rest4cep.mysql.dto.EventPatternPostDto;
import com.gamaza.rest4cep.mysql.dto.EventPatternPutDto;
import com.gamaza.rest4cep.mysql.dto.EventPatternWithListDto;

import java.util.List;

/**
 * Event Pattern Service Interface
 */
public interface EventPatternService {

    /**
     * Insert a new Event Pattern in database
     */
    EventPatternWithListDto create(EventPatternPostDto eventPatternPostDto);

    /**
     * Get all Event Patterns in database
     */
    List<EventPatternDto> readAll();

    /**
     * Get all readyToDeploy/notReadyToDeploy Event Patterns in database
     */
    List<EventPatternDto> readAllByIsReadyToDeploy(boolean status);

    /**
     * Get all deployed/undeployed Event Patterns in database
     */
    List<EventPatternDto> readAllByIsDeployed(boolean status);

    /**
     * Search and return one Event Pattern by id
     */
    EventPatternWithListDto readOneById(Integer id);

    /**
     * Search and return one Event Pattern by name
     */
    EventPatternWithListDto readOneByName(String name);

    /**
     * Modify one Event Pattern in database
     */
    void update(Integer id, EventPatternPutDto eventPatternPutDto);

    /**
     * Update the status of one Event Pattern in database (readyToDeploy variable)
     */
    void updateStatus(Integer id, boolean status);

    /**
     * Update the status of one Event Pattern in database (deployed variable)
     */
    void updateDeployingStatus(Integer id, boolean status);

    /**
     * Link/Unlink one Event Pattern with an Event Type
     */
    void setPatternLink(Integer eventPatternId, Integer eventTypeId, boolean linkStatus);

    /**
     * Delete one Event Pattern in database
     */
    void delete(Integer id);

}
