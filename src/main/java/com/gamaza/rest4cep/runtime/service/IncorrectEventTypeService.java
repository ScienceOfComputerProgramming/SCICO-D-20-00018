package com.gamaza.rest4cep.runtime.service;

import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypeDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypePostDto;

import java.util.List;

/**
 * Incorrect Event Type Service
 */
public interface IncorrectEventTypeService {

    /**
     * Insert a new Incorrect Event Type in database
     */
    IncorrectEventTypeDto create(IncorrectEventTypePostDto incorrectEventTypePostDto);

    /**
     * Get all Incorrect Event Types in database
     */
    List<IncorrectEventTypeDto> readAll();

    /**
     * Search and return one Incorrect Event Type in database by id
     */
    IncorrectEventTypeDto readOneById(String id);

    /**
     * Get Last 5 Incorrect Event Types in database
     */
    List<IncorrectEventTypeDto> readLast5();

    /**
     * Delete all Incorrect Event Types in database
     */
    void deleteAll();

    /**
     * Delete one Incorrect Event Type in database
     */
    void deleteOne(String id);

}
