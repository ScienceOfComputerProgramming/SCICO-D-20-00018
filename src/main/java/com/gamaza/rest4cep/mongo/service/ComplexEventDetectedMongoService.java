package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;

import java.util.List;

/**
 * Complex Events Detected Service
 */
public interface ComplexEventDetectedMongoService {

    /**
     * Insert a new Complex Event Detected in database
     * @param complexEventDetectedMongoDto **complexEventDetectedMongoDto**
     * @return complexEventDetected created
     */
    ComplexEventDetectedMongoDto create(ComplexEventDetectedMongoDto complexEventDetectedMongoDto);

    /**
     * Get all Complex Events Detected in database
     * @return complexEventDetected list
     */
    List<ComplexEventDetectedMongoDto> readAll();

    /**
     * Search and return one Complex Event Detected in database by id
     * @param id **id**
     * @return complexEventDetected found
     */
    ComplexEventDetectedMongoDto readOneById(String id);

    /**
     * Get Last 5 Complex Events Detected in database
     * @return Last 5 complexEventDetected list
     */
    List<ComplexEventDetectedMongoDto> readLast5();

    /**
     * Delete all Complex Events Detected in database
     */
    void deleteAll();

    /**
     * Delete one Complex Event Detected in database
     * @param id **id**
     */
    void deleteOne(String id);

}
