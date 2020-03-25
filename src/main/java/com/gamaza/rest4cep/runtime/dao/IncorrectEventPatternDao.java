package com.gamaza.rest4cep.runtime.dao;

import com.gamaza.rest4cep.runtime.model.IncorrectEventPattern;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Incorrect Event Pattern Data Access Object
 */
@Repository
public interface IncorrectEventPatternDao extends MongoRepository<IncorrectEventPattern, String> {

    /**
     * Get all Incorrect Event Patterns in database ordered by date
     */
    List<IncorrectEventPattern> findAllByOrderByCreatedDateDesc();

    /**
     * Get Last 5 Incorrect Event Patterns in database
     */
    List<IncorrectEventPattern> findTop5ByOrderByCreatedDateDesc();

}
