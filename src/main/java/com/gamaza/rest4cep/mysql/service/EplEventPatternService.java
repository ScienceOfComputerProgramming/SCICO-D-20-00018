package com.gamaza.rest4cep.mysql.service;

import com.gamaza.rest4cep.mysql.dto.EplEventPatternWithListDto;

import java.util.List;

/**
 * EPL Event Patterns Service Interface
 */
public interface EplEventPatternService {

    /**
     * Insert a new EPL Event Pattern in database
     * @param eplEventPatternWithListDto **eplEventPatternWithListDto**
     * @return eplEventPattern created
     */
    EplEventPatternWithListDto create(EplEventPatternWithListDto eplEventPatternWithListDto);

    /**
     * Get all EPL Event Patterns in database
     * @return eplEventPattern list
     */
    List<EplEventPatternWithListDto> readAll();

    /**
     * Get all deployed/undeployed EPL Event Patterns in database
     * @return eplEventPattern deployed/undeployed list
     */
    List<EplEventPatternWithListDto> readAllByIsDeployed(boolean status);

    /**
     * Get all inEsper/notInEsper EPL Event Patterns in database
     * @return eplEventPattern inEsper/notInEsper list
     */
    List<EplEventPatternWithListDto> readAllByIsInEsper(boolean status);

    /**
     * Search and return one EPL Event Pattern by id
     * @param id **id**
     * @return eplEventPattern found
     */
    EplEventPatternWithListDto readOneById(Integer id);

    /**
     * Search and return one EPL Event Pattern by name
     * @param name **name**
     * @return eplEventPattern found
     */
    EplEventPatternWithListDto readOneByName(String name);

    /**
     * Modify one EPL Event Pattern in database
     * @param id **id**
     * @param eplEventPatternWithListDto **eplEventPatternWithListDto**
     */
    void update(Integer id, EplEventPatternWithListDto eplEventPatternWithListDto);

    /**
     * Update the status of one EPL Event Pattern in database
     * @param id **id**
     * @param status **status**
     */
    void updateStatus(Integer id, boolean status);

    /**
     * Link/Unlink one EPL Event Pattern with an Event Type
     * @param eplEventPatternId **eplEventPatternId**
     * @param eventTypeId **eventTypeId**
     * @param linkStatus **linkStatus**
     */
    void setPatternLink(Integer eplEventPatternId, Integer eventTypeId, boolean linkStatus);

    /**
     * Delete one EPL Event Pattern in database
     * @param id **id**
     */
    void delete(Integer id);

}
