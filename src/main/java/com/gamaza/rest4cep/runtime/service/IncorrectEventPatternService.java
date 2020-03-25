package com.gamaza.rest4cep.runtime.service;

import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternPostDto;

import java.util.List;

/**
 * Incorrect Event Pattern Service
 */
public interface IncorrectEventPatternService {

    /**
     * Insert a new Incorrect Event Pattern in database
     */
    IncorrectEventPatternDto create(IncorrectEventPatternPostDto eventPatternPostDto);

    /**
     * Get all Incorrect Event Patterns in database
     */
    List<IncorrectEventPatternDto> readAll();

    /**
     * Search and return one Incorrect Event Pattern in database by id
     */
    IncorrectEventPatternDto readOneById(String id);

    /**
     * Get Last 5 Incorrect Event Patterns in database
     */
    List<IncorrectEventPatternDto> readLast5();

    /**
     * Delete all Incorrect Event Patterns in database
     */
    void deleteAll();

    /**
     * Delete one Incorrect Event Pattern in database
     */
    void deleteOne(String id);

}
