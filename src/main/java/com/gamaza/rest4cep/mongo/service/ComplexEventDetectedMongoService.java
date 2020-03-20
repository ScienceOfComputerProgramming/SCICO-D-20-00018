package com.gamaza.rest4cep.mongo.service;

import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;
import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedPostDto;

import java.util.List;

/**
 * Complex Events Detected Service
 */
public interface ComplexEventDetectedMongoService {

    /**
     * Insert a new Complex Event Detected in database
     */
    ComplexEventDetectedMongoDto create(ComplexEventDetectedPostDto complexEventDetectedPostDto);

    /**
     * Get all Complex Events Detected in database
     */
    List<ComplexEventDetectedMongoDto> readAll();

    /**
     * Search and return one Complex Event Detected in database by id
     */
    ComplexEventDetectedMongoDto readOneById(String id);

    /**
     * Get Last 5 Complex Events Detected in database
     */
    List<ComplexEventDetectedMongoDto> readLast5();

    /**
     * Delete all Complex Events Detected in database
     */
    void deleteAll();

    /**
     * Delete one Complex Event Detected in database
     */
    void deleteOne(String id);

}
