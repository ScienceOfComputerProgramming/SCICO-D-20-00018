package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dto.EplEventPatternMongoDto;

import java.util.List;

/**
 * EPL Event Patterns (Mongo version) Service
 */
public interface EplEventPatternMongoService {

    /**
     * Insert a new EPL Event Pattern in database
     * @param eplEventPatternMongoDto **eplEventPatternMongoDto**
     * @return eplEventPattern created
     */
    EplEventPatternMongoDto create(EplEventPatternMongoDto eplEventPatternMongoDto);

    /**
     * Get all EPL Event Patterns in database
     * @return eplEventPattern list
     */
    List<EplEventPatternMongoDto> readAll();

    /**
     * Search and return one EPL Event Pattern in database by id
     * @param id **id**
     * @return eplEventPattern found
     */
    EplEventPatternMongoDto readOneById(String id);

    /**
     * Get Last 5 EPL Event Patterns in database
     * @return Last 5 eplEventPattern list
     */
    List<EplEventPatternMongoDto> readLast5();

    /**
     * Delete all EPL Event Patterns in database
     */
    void deleteAll();

    /**
     * Delete one EPL Event Pattern in database
     * @param id **id**
     */
    void deleteOne(String id);

}
