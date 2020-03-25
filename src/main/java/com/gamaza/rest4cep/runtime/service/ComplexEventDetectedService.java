package com.gamaza.rest4cep.runtime.service;

import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedDto;
import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedPostDto;

import java.util.List;

/**
 * Complex Events Detected Service
 */
public interface ComplexEventDetectedService {

    /**
     * Insert a new Complex Event Detected in database
     */
    ComplexEventDetectedDto create(ComplexEventDetectedPostDto complexEventDetectedPostDto);

    /**
     * Get all Complex Events Detected in database
     */
    List<ComplexEventDetectedDto> readAll();

    /**
     * Search and return one Complex Event Detected in database by id
     */
    ComplexEventDetectedDto readOneById(String id);

    /**
     * Get Last 5 Complex Events Detected in database
     */
    List<ComplexEventDetectedDto> readLast5();

    /**
     * Delete all Complex Events Detected in database
     */
    void deleteAll();

    /**
     * Delete one Complex Event Detected in database
     */
    void deleteOne(String id);

}
